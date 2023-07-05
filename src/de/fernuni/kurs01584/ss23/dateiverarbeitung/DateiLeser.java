package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class DateiLeser {
	private SAXBuilder builder;
	
	public DateiLeser() {
		builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
	}
	
	public Document lese(String fileName) throws JDOMException, IOException {
		try {
			Document document = builder.build(fileName);
			return document;
		} catch (IOException e) {
			throw e;
		} catch (JDOMException e) {
			throw e;
		}
	}
}
