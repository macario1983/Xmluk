package com.nfehost.view;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;

import org.apache.commons.io.IOUtils;

import com.nfehost.service.AnexoService;
import com.nfehost.service.email.EmailService;
import com.nfehost.util.FileUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;

@Data
public class AnexoBean {
	
	private AnexoService service;
	private EmailService emailService;

	public void process() {
		
		Map<String, String> mapAttachment = this.getEmailService().getAttachmentList();
		
		if (!NullUtil.isEmptyMap(mapAttachment)) {
			
			for (Entry<String, String> attachment : mapAttachment.entrySet()) {
				
				String source;
				try {
					source = FileUtil.takeOffBOM(IOUtils.toInputStream(attachment.getValue()));
					source = StringUtil.normalize(source);
					attachment.setValue(source);
					this.getService().validateXmlFile(attachment);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
