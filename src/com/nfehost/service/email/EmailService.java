package com.nfehost.service.email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

import lombok.Data;

import com.nfehost.service.email.thread.ThreadCreateFolder;
import com.nfehost.service.email.thread.ThreadGenerateFile;
import com.nfehost.service.email.thread.ThreadMoveMessages;
import com.nfehost.util.EmailUtil;
import com.nfehost.util.FileUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.ThreadUtil;

@Data
public class EmailService {

	private final int threadQuantity = ThreadUtil.quantityOfThreads();

	public Map<String, String> getAttachmentList() {
		Map<String, String> map = new HashMap<>();
		return this.emailCore(map);
	}
		
	private Map<String, String> emailCore(Map<String, String> map) {

		try {
			
			Properties properties = EmailUtil.getEmailProperties();
			
			Store store = openConnection(properties);
			
			Folder inbox = store.getDefaultFolder();
			inbox = inbox.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			
			FetchProfile fetchProfile = new FetchProfile();
			fetchProfile.add(FetchProfile.Item.CONTENT_INFO);
			
			Message[] messages = inbox.getMessages();
			Message[] messagesToDelete = new Message[messages.length];
			int countToDelete = 0;
			
			inbox.fetch(messages, fetchProfile);

			countToDelete += EmailUtil.separteMessagesWithoutAttachment(messages, messagesToDelete);
			countToDelete += this.separteMessagesWithoutXmlAttachment(messages, messagesToDelete, map);

			if (this.hasMessagesWithoutXmlAttachment(countToDelete)) {
				this.deleteMessagesWithoutXmlAttachment(messagesToDelete, countToDelete, inbox);
				messages = this.separateMessagesWithAttachment(messages, countToDelete);
			}
			
			Set<String> setSender = getMessagesSender(messages);
			Set<String> setFolderName = getFoldersName(store);
			
			if (this.foldersNotExist(setFolderName, setSender)) {
				this.createFolder(store, setSender);
			}
			
			this.moveMessagesToFolders(messages, store, setSender);
			this.deleteMessages(messages, inbox);
			
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}

	private Store openConnection(Properties properties) throws NoSuchProviderException, MessagingException {
		Session session = Session.getDefaultInstance(properties, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", "xmluka", "lukinhask8");
		return store;
	}

	private int separteMessagesWithoutXmlAttachment(Message[] messages, Message[] messagesToDelete, Map<String, String> map) throws IOException, MessagingException {
	
		List<MimeBodyPart> listMimeBodyPart = new ArrayList<>();
		int countToDelete = 0;

		for (int i = 0; i < messages.length; i++) {
			
			if (!NullUtil.isNull(messages[i])){
				if (hasNoXmlAttachment(messages[i], listMimeBodyPart)) {
					messagesToDelete[countToDelete++] = messages[i];
					messages[i] = null;
				}				
			}
		}

		this.processAttachmentXml(listMimeBodyPart, map);
		
		return countToDelete;
	}

	private boolean hasNoXmlAttachment(Message message, List<MimeBodyPart> listMimeBodyPart) throws IOException, MessagingException {

		Multipart multipart = (Multipart) message.getContent();
		boolean hasNoXmlAttachment = true;
		
		for (int i = 0; i < multipart.getCount(); i++) {
			
			MimeBodyPart mimeBodyPart = (MimeBodyPart) multipart.getBodyPart(i);
			
			if (Part.ATTACHMENT.equalsIgnoreCase(mimeBodyPart.getDisposition())) {
				
				if (FileUtil.isXmlFile(mimeBodyPart.getFileName())) {
					hasNoXmlAttachment = false;
					listMimeBodyPart.add(mimeBodyPart);
					
				}
			}
		}
		
		return hasNoXmlAttachment;
	}

	private void processAttachmentXml(List<MimeBodyPart> listMimeBodyPart, Map<String, String> map) throws MessagingException, IOException {
		
		MimeBodyPart[] mimeBodyPartsArray = listMimeBodyPart.toArray(new MimeBodyPart[listMimeBodyPart.size()]);
		List<Thread> listThread = ThreadUtil.creatPoolThread();
		int[] indexThread = ThreadUtil.generateThreadsIndex(mimeBodyPartsArray);
		
		int indexStart = 0;
		int indexEnd = 0;
		
		for (int i = 0; i < this.getThreadQuantity(); i++) {
			indexEnd += indexThread[i];
			MimeBodyPart[] mimeBodyPartsArrayCopy = (MimeBodyPart[]) ThreadUtil.sliceArray(mimeBodyPartsArray, indexStart, indexEnd);
			indexStart = indexEnd;
			listThread.add(new ThreadGenerateFile(mimeBodyPartsArrayCopy, map));
		}
		
		ThreadUtil.startThreads(listThread);
	}

	private boolean hasMessagesWithoutXmlAttachment(int countToDelete) {
		return countToDelete > 0;
	}

	private void deleteMessagesWithoutXmlAttachment(Message[] messagesToDelete, int countToDelete, Folder inbox) throws MessagingException {

		messagesToDelete = Arrays.copyOf(messagesToDelete, countToDelete);
		Flags flagToDelete = new Flags(Flag.DELETED);
		
		inbox.setFlags(messagesToDelete, flagToDelete, true);
		inbox.expunge();
	}

	private Message[] separateMessagesWithAttachment(Message[] messages, int countToDelete) {
		
		List<Message> listMessage = new ArrayList<>(Arrays.asList(messages));
		listMessage.removeAll(Collections.singleton(null));
		return listMessage.toArray(new Message[listMessage.size()]);
	}

	private Set<String> getMessagesSender(Message[] messages) throws MessagingException {
		
		Set<String> setSender = new HashSet<>();
		
		for (Message message : messages) {
			if(!NullUtil.isNull(message)){
				setSender.add(EmailUtil.getSender(message));
			}
		}
		
		return setSender;
	}

	private Set<String> getFoldersName(Store store) throws MessagingException {

		Set<Folder> setFolder = new HashSet<>(Arrays.asList(store.getDefaultFolder().list()));
		Set<String> setFolderName = new HashSet<>();

		for (Folder folder : setFolder) {
			setFolderName.add(folder.getName());
		}

		return setFolderName;
	}

	private void createFolder(Store store, Set<String> setSenders) throws MessagingException {

		String[] senderArray = setSenders.toArray(new String[setSenders.size()]);

		List<Thread> listThread = ThreadUtil.creatPoolThread();
		int[] indexThread = ThreadUtil.generateThreadsIndex(senderArray);

		int indexStart = 0;
		int indexEnd = 0;
		
		for (int i = 0; i < this.getThreadQuantity(); i++) {
			indexEnd += indexThread[i];
			String[] senderArrayCopy = (String[]) ThreadUtil.sliceArray(senderArray, indexStart, indexEnd);
			indexStart = indexEnd;
			listThread.add(new ThreadCreateFolder(senderArrayCopy, store));
		}
		
		ThreadUtil.startThreads(listThread);
	}

	private boolean foldersNotExist(Set<String> setFolderName, Set<String> setSenders) throws MessagingException {
		return !(setFolderName.containsAll(setSenders));
	}

	private void moveMessagesToFolders(Message[] messages, Store store, Set<String> setSender) throws MessagingException {

		HashMap<String, List<Message>> mapMessages = separeteMessagesBySender(messages, setSender);
		
		for (Entry<String, List<Message>> mapMessage : mapMessages.entrySet()) {

			Message[] messageArray = mapMessage.getValue().toArray(new Message[mapMessage.getValue().size()]);
			
			List<Thread> listThread = ThreadUtil.creatPoolThread();
			int[] indexThread = ThreadUtil.generateThreadsIndex(messageArray);

			int indexStart = 0;
			int indexEnd = 0;
			
			for (int i = 0; i < this.getThreadQuantity(); i++) {
				indexEnd += indexThread[i];
				Message[] messageArrayCopy = (Message[]) ThreadUtil.sliceArray(messageArray, indexStart, indexEnd);
				indexStart = indexEnd;
				listThread.add(new ThreadMoveMessages(messageArrayCopy, mapMessage.getKey(), store));
			}
			
			ThreadUtil.startThreads(listThread);
		}
	}

	private HashMap<String, List<Message>> separeteMessagesBySender(Message[] messages, Set<String> setSender) throws MessagingException {

		HashMap<String, List<Message>> mapMessages = new HashMap<>();
		List<Message> listMessage = null;

		for (String sender : setSender) {
			listMessage = new ArrayList<>();
			mapMessages.put(sender, listMessage);
		}

		for (Message message : messages) {
			if (!NullUtil.isNull(message)) {
				listMessage = mapMessages.get(EmailUtil.getSender(message));
				listMessage.add(message);				
			}
		}

		return mapMessages;
	}
	
	private void deleteMessages(Message[] messages, Folder inbox) throws MessagingException {
		inbox.setFlags(messages, new Flags(Flag.DELETED), true);
		inbox.expunge();
	}

}
