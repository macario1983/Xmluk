package com.nfehost.service.email.thread;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

public class ThreadMoveMessages extends Thread {

	private String sender;
	private Store store;
	private Message[] messages;

	public ThreadMoveMessages(Message[] messages, String sender, Store store) {
		this.messages = messages;
		this.sender = sender;
		this.store = store;
	}

	@Override
	public void run() {
		try {
			Folder folder = this.store.getDefaultFolder().getFolder(this.sender);
			folder.open(Folder.READ_WRITE);
			folder.appendMessages(this.messages);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
