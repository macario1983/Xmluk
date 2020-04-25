package com.nfehost.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailUtil {

	private static final String RESOURCE_EMAIL = "email.properties";	

	public static Session getEmailSession() {
		
		Properties properties = getEmailProperties();
		
		return Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xmluka@gmail.com", "lukinhask8");
			}
		});
	}
	
	public static Properties getEmailProperties() {
		
		Properties properties = new Properties();
		
		try (InputStream inputStream = EmailUtil.class.getClassLoader().getResourceAsStream(RESOURCE_EMAIL)) {

			properties.load(inputStream);
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
		
	}
	
	public static int separteMessagesWithoutAttachment(Message[] messages, Message[] messagesToDelete) throws MessagingException {

		int countToDelete = 0;
		
		for (int i = 0; i < messages.length; i++) {

			if (hasNoAttachment(messages[i].getContentType())) {
				messagesToDelete[countToDelete++] = messages[i];
				messages[i] = null;
			}
		}
		
		return countToDelete;
	}
	
	public static boolean hasAttachment(String content) {
		return content.contains("multipart/MIXED");
	}

	public static boolean hasNoAttachment(String content) {
		return !content.contains("multipart/MIXED");
	}
	
	public static String getSender(Message message) throws MessagingException {

		Address[] address = message.getFrom();

		return address[0].toString().replaceAll(".*<([^<]+)>", "$1");
	}

}
