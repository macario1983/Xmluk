package com.nfehost.service.email.thread;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.io.IOUtils;

public class ThreadGenerateFile extends Thread {

	private MimeBodyPart[] mimeBodyPartArray;
	private Map<String, String> map;

	public ThreadGenerateFile(MimeBodyPart[] mimeBodyPartArray, Map<String, String> map) {
		this.mimeBodyPartArray = mimeBodyPartArray;
		this.map = map;
	}
	
	@Override
	public void run() {
		
		for (MimeBodyPart mimeBodyPart : mimeBodyPartArray) {			
			try {
				String source = IOUtils.toString(mimeBodyPart.getInputStream());
				this.map.put(mimeBodyPart.getFileName(), source);
			} catch (MessagingException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
