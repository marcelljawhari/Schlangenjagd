package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class DateiLeser {
	private SAXBuilder builder;
	
	/***
	 * Erzeuge einen DateiLeser.
	 */
	public DateiLeser() {
		builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
	}
	
	/***
	 * Lese die Eingabedatei und gebe diese als Document aus.
	 * @param fileName 			Pfad der Eingabedatei.
	 * @return					Document aus der eingelesenen XML Datei.
	 * @throws JDOMException	JDOMException Fehler wird erzeugt, wenn die eingelesene Datei nicht der DTD entspricht.
	 * @throws IOException		IOException wird erzeugt, wenn die angegebene Datei nicht gelesen werden kann.
	 */
	public Document lese(String fileName) throws JDOMException, IOException {
		Document document = builder.build(fileName);
		return document;
	}
}
