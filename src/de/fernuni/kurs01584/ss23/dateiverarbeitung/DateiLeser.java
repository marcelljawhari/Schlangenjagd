package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class DateiLeser {
	private Document document;
	
	public DateiLeser(final String fileName) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
		try {
			document = builder.build(fileName);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Document getDocument() {
		return document;
	}
	
}
