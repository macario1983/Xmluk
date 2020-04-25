package com.nfehost.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public final class ReportUtil {
	
	public static synchronized void downloadJasperReportPdf(byte[] fileBytes, String nomeArq, FacesContext facesContext) {  

		ExternalContext context = facesContext.getExternalContext(); // Context  
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		String fileName = nomeArq.concat(".pdf");
		
		// Limpa o buffer do response
		response.reset();
		
		response.setHeader("Content-disposition", "inline; filename=\"" + fileName + "\""); //aki eu seto o header e o nome q vai aparecer na hr do donwload  
		response.setContentLength(fileBytes.length); // O tamanho do arquivo  
		response.setContentType("application/pdf"); // e obviamente o tipo  
		
		try (OutputStream out = response.getOutputStream()) {    

			out.write(fileBytes);
			out.flush();  
			facesContext.responseComplete();			

		} catch (IOException ex) {  
			System.out.println("Erro ao baixar arquivo: " + ex.getMessage());  
			ex.printStackTrace();  
		}
	}

}
