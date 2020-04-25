package com.nfehost.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;

public class NfeHostException extends Exception {

	private static final long serialVersionUID = 9054650454221226136L;
	private Map<String, Severity> messages ;
	private Exception exception;
	
	public NfeHostException() {
		this.messages = new HashMap<>();
	}
	
	public NfeHostException(String...messages) {
		this();
		for(String msg : messages) {
			this.addMessageError(msg);
		}
	}
	
	public NfeHostException(Exception e, String...messages) {
		this(messages);
		this.setException(e);		
	}

	public void addMessageError(String message) {
		this.messages.put(message, FacesMessage.SEVERITY_ERROR);
	}
	
	public Map<String, Severity> getMessages() {
		return this.messages;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public List<String> messages() {
		if (!NullUtil.isEmptyMap(this.getMessages())) {
			return new ArrayList<String>(this.getMessages().keySet());
		}
		return null;
	}
	
	@Override
	public String getMessage() {
		if (!StringUtil.isStringNullOrEmpty(this.messages().get(0))) {
			return this.messages().get(0);
		}
		return super.getMessage();
	}

	public int qtdeErros() {
		return NullUtil.integerNotNull(this.getMessages().size());
	}
	
	public static String printStackTrace(Throwable exception) {
	    StringWriter stringWriter = new StringWriter();
	    exception.printStackTrace(new PrintWriter(stringWriter, true));
	    return stringWriter.toString();
	}

}
