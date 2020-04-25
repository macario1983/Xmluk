package com.nfehost.service.email.thread;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

public class ThreadCreateFolder extends Thread {

	private String[] senders;
	private Store store;

	public ThreadCreateFolder(String[] senders, Store store) {
		this.senders = senders;
		this.store = store;
	}

	@Override
	public void run() {
		for (String sender : senders) {
			try {
				store.getFolder(sender).create(Folder.HOLDS_MESSAGES);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}
