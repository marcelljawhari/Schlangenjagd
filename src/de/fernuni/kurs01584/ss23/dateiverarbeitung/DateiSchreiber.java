package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.DocType;

import de.fernuni.kurs01584.ss23.modell.Nachbarschaftsstruktur;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Zeiteinheit;

public class DateiSchreiber {
	private FileWriter fileWriter;
	private XMLOutputter xmlOutputter;
	
	/***
	 * Erzeuge einen DateiSchreiber.
	 */
	public DateiSchreiber() {
		String indent = "    ";
		xmlOutputter = new XMLOutputter(Format.getPrettyFormat().setIndent(indent));
	}
	
	/***
	 * Wandle das eingelesene SchlangenjagdModell in ein Document um und schreibe dieses
	 * in die Ausgabedatei.
	 * @param schlangenjagdModell SchlangenjagdModell welches in der Ausgabedatei gespeichert werden soll.
	 * @param fileName FileName der Ausgabedatei.
	 * @throws IOException Erzeugt eine IOException falls die Ausgabedatei nicht geschrieben werden konnte.
	 */
	public void schreibe(SchlangenjagdModell schlangenjagdModell, String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		fileWriter = new FileWriter(fileName);
		DocumentBuilder builder = new DocumentBuilder();
		Document document = builder.build(schlangenjagdModell);
		xmlOutputter.output(document, fileWriter);
		fileWriter.close();
	}
}
