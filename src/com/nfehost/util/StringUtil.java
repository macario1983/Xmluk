package com.nfehost.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.nfehost.service.processor.extraction.nfe.EnderecoInterface;

public class StringUtil {

	private static final String RESOURCE_ELEMENTS_1_00 = "elements1.00.properties";	
	private static final String RESOURCE_ELEMENTS_1_10 = "elements1.10.properties";	
	private static final String RESOURCE_ELEMENTS_2_00 = "elements2.00.properties";
	private static final String RESOURCE_VALIDATION_MESSAGES = "validation_messages.properties";
	private static Map<String, String> mapNames;
	private static Properties properties;

	public static String stringNotNull(String str) {
		return NullUtil.isNull(str) ? "" : str;
	}

	public static String aplicarTrim(String str) {
		if (str != null) {
			return str.trim();
		}
		return str;
	}

	public static boolean isStringNullOrEmpty(String str) {
		return (NullUtil.isNull(str)) || (aplicarTrim(str).isEmpty());
	}
	
	public static String toLower(String source) {
		if (!NullUtil.isNull(source)) {
			return source.toLowerCase();			
		}
		return null;
	}
	
	public static String formatarEndereco(EnderecoInterface endereco) {

		StringBuilder stringBuilder = new StringBuilder();

		if (!StringUtil.isStringNullOrEmpty(endereco.getLogradouro())) {
			stringBuilder.append(endereco.getLogradouro());
		}

		if (!StringUtil.isStringNullOrEmpty(endereco.getNumero())) {
			stringBuilder.append(" , ").append(endereco.getNumero());
		}

		if (!StringUtil.isStringNullOrEmpty(endereco.getComplemento())) {
			stringBuilder.append(" - ").append(endereco.getComplemento());
		}

		return stringBuilder.toString();
	}

	public static String aplicarTrimNull(String str) {
		str = aplicarTrim(str);
		if(isStringNullOrEmpty(str)) {
			return null;
		}
		return str;
	}

	public static String toUpper(String source) {
		if (!NullUtil.isNull(source)) {
			return source.toUpperCase();			
		}
		return null;
	}
	
	public static String translateMessageError(String message) {

		String messageTranslate = "";
		
		try (InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(RESOURCE_VALIDATION_MESSAGES)) {
			properties = new Properties();
			properties.load(inputStream);
			messageTranslate = properties.getProperty(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return messageTranslate;
	}

	public static Map<String, String> loadElements1_10() {

		try (InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(RESOURCE_ELEMENTS_1_10)) {
			properties = new Properties();
			properties.load(inputStream);
			mapNames = new HashMap<>();

			for (String key : properties.stringPropertyNames()) {
	            mapNames.put(key, properties.getProperty(key));
	        }			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapNames;
	}

	public static Map<String, String> loadElements2_00() {
		
		try (InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(RESOURCE_ELEMENTS_2_00)) {
			properties = new Properties();
			properties.load(inputStream);
			mapNames = new HashMap<>();
			
			for (String key : properties.stringPropertyNames()) {
	            mapNames.put(key, properties.getProperty(key));
	        }			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapNames;
	}

	public static Map<String, String> loadElements1_00() {

		try (InputStream inputStream = XmlUtil.class.getClassLoader().getResourceAsStream(RESOURCE_ELEMENTS_1_00)) {
			properties = new Properties();
			properties.load(inputStream);
			mapNames = new HashMap<>();
			
			for (String key : properties.stringPropertyNames()) {
	            mapNames.put(key, properties.getProperty(key));
	        }			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapNames;

	}

	public static String normalize(String source) {
		return source.replaceAll("\\s+([<> ]|/>)", "$1").replaceAll("\\r|\\n", "");
	}

}
