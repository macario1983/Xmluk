package com.nfehost.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lombok.Data;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.nfehost.model.NotaFiscal;
import com.nfehost.service.processor.IntegracaoProcessadoraXmlService;
import com.nfehost.service.processor.extraction.NFeProc;

@Data
public class XmlUtil {

	private static DocumentBuilderFactory documentBuilderFactory;
	private static DocumentBuilder documentBuilder;
	private static Document document;
	private static Element root;
	private static InputSource inputSource;
	private static List<String> listValidationErrors;
	
	public static String getRootElement(String source) {

		documentBuilderFactory = DocumentBuilderFactory.newInstance();

		try {
			
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			inputSource = new InputSource(); 
			inputSource.setCharacterStream(new StringReader(source));
			document = documentBuilder.parse(inputSource);
			root = (Element) document.getDocumentElement();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}

		return root.getNodeName();
	}

	public static String getVersionElement(String source) {

		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Attr atribute = null;

		try {

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			inputSource = new InputSource(); 
			inputSource.setCharacterStream(new StringReader(source));
			document = documentBuilder.parse(inputSource);
			root = (Element) document.getDocumentElement();
			atribute = root.getAttributeNode("versao");

			if (NullUtil.isNull(atribute)) {
				return null;
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}

		return atribute.getNodeValue();
	}

	public static NFeProc parseToString(String source) {

		JAXBContext jaxbContext = null;

		try {

			jaxbContext = JAXBContext.newInstance(new Class[] { NFeProc.class });
			return (NFeProc) jaxbContext.createUnmarshaller().unmarshal(new StringReader(source));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean isRootElementNfeProc(String rootElement) {
		return getRootElement(rootElement).equals("nfeProc");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static NotaFiscal createNotaFiscal(Entry<String, String> map) {

		NFeProc processedNotaFiscal = XmlUtil.parseToString(map.getValue());
		return new IntegracaoProcessadoraXmlService().createNotaFiscal(processedNotaFiscal, map);

	}

	protected static boolean xmlRootElementIsNotEmpty(String rootElement) {
		return !StringUtil.isStringNullOrEmpty(rootElement);
	}

	protected static boolean xmlVersionElementIsNotEmpty(String versionElement) {
		return !StringUtil.isStringNullOrEmpty(versionElement);
	}

	protected static boolean containsElement(Map<String, Map<String, String>> mapNodes, String rootElement, String versionAtribute) {
		
		Map<String, String> map = mapNodes.get(versionAtribute);	
		return map.containsKey(rootElement);	
	}

	public static boolean xmlIsValidates(String source, String filename, String versionElement) {
		
		listValidationErrors = new ArrayList<>();

		ErrorHandler errorHandler = new ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {
				listValidationErrors.add(StringUtil.translateMessageError(exception.getMessage()));
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				listValidationErrors.add(StringUtil.translateMessageError(exception.getMessage()));
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				listValidationErrors.add(StringUtil.translateMessageError(exception.getMessage()));
			}
		};
		
		System.out.println(filename);
		String xsdPath = FileUtil.xsdFile(filename, versionElement);
		
		DocumentBuilderFactory documentBuilderFactory = null;
		DocumentBuilder documentBuilder = null;

		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		documentBuilderFactory.setValidating(true);
		documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsdPath);

		try {

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(errorHandler);
			InputSource inputSource = new InputSource(); 
			inputSource.setCharacterStream(new StringReader(source));
			documentBuilder.parse(inputSource);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static String getXsdFilename(String rootElement, String versionElement) {
		Map<String, Map<String, String>> mapElements = XmlUtil.loadXmlElements();
		
		if (!mapElements.containsKey(versionElement)) {
			return null;
		}
		
		Map<String, String> map =  mapElements.get(versionElement);
		
		if (!map.containsKey(rootElement)) {
			return null;
		}
		
		return map.get(rootElement);
	}

	protected static Map<String, Map<String, String>> loadXmlElements() {

		Map<String, Map<String, String>> mapNodes = new HashMap<>();
		mapNodes.put("1.00", StringUtil.loadElements1_00());
		mapNodes.put("1.10", StringUtil.loadElements1_10());
		mapNodes.put("2.00", StringUtil.loadElements2_00());
		return mapNodes;

	}
	
}
