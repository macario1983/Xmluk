package com.nfehost.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;

public class FileUtil {

	private static final String XSDPATH = "resources/xsd/";

	public static Path getPathJavaHome() {
		return Paths.get(System.getProperty("java.home")).getRoot();
	}
	
	public static String getTempFolder() {
		return System.getProperty("java.io.tmpdir");
	}
	
	public static boolean directoryNotExist(Path path) {
		File file = new File(path.toString());
		return !file.exists();
	}
	
	public static void moveFile(File file, Path path) {
		
		try {
			FileUtils.moveFileToDirectory(file, path.toFile(), true);				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static  boolean isDirectory(File file) {
		return file.isDirectory();
	}
	
	public static boolean isEmpty(File file) {
		return new File(file.getAbsolutePath()).length() == 0;
	}
	
	public static void createDirectory(Path directoryPath) {

		try {
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean hasExtension(File file) {
		return file.getName().contains(".");
	}
	
	public static boolean isXmlContent(String source, String rootElement, String versionElement) {

		Map<String, Map<String, String>> mapNodes = XmlUtil.loadXmlElements();

		if ((xmlFileIsNotEmpty(source) && (XmlUtil.xmlRootElementIsNotEmpty(rootElement)) && 
			(XmlUtil.xmlVersionElementIsNotEmpty(versionElement)) && (XmlUtil.containsElement(mapNodes, rootElement, versionElement)))) {

			return true;
		}

		return false;
	}

	public static boolean isXmlFile(String fileName) {
		return fileExtesion(fileName).equalsIgnoreCase("xml");
	}

	private static boolean xmlFileIsNotEmpty(String source) {
		return !StringUtil.isStringNullOrEmpty(source);
	}
	
	public static String fileExtesion(String fileName) {
		return fileName.replaceAll("^.*\\.([^.]+)$", "$1");
	}
		
	
	public static String fileName(String fileName) {
		return fileName.substring(0, fileName.indexOf("."));
	}

	public static String takeOffBOM(InputStream inputStream) throws IOException {
		BOMInputStream bomInputStream = new BOMInputStream(inputStream);
		return IOUtils.toString(bomInputStream, "UTF-8");
	}
	
	public static void deleteTemporaryFile(File file) {
		file.deleteOnExit();
	}
	
	public static Path createFile(String source, String fileName, Path path) {
		
		try {
			return Files.write(Paths.get(path.toString(), fileName), source.getBytes(), StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Path getXmlDirectory(String xmlFolderValidation, String xmlVersion, String xmlElement, String cnpj){

		String rootPath = Paths.get(System.getProperty("java.home")).getRoot().toString();
        return Paths.get(rootPath, "NfeHost", "Xml", xmlFolderValidation, xmlVersion, xmlElement, cnpj);

	}

	public static String xsdFile(String rootElement, String versionElement) {

		String realPath = "";

		try {
			realPath = getHTTPpath();
		} catch (Exception e) {
			return getMachinePath(versionElement, rootElement).concat(".xsd");
		}

		return realPath.concat("\\").concat(versionElement).concat("\\").concat(rootElement).concat(".xsd");
	}

	private static String getHTTPpath() throws Exception {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(XSDPATH);
	}

	private static String getMachinePath(String rootVersion, String rootElement) {

		String realPath = XmlUtil.class.getClassLoader().getResource("").getPath();

		try {
			realPath = URLDecoder.decode(realPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		realPath = realPath.substring(1).replace("/WEB-INF/classes", "");
		return realPath.concat("resources/xsd/" + rootVersion + "/" + rootElement);
	}
}
