package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.fernuni.kurs01584.ss23.modell.Nachbarschaftsstruktur;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Zeiteinheit;

public class DateiSchreiber {
	private DateiLeser leser;
	private FileWriter fileWriter;
	private XMLOutputter xmlOutputter;
	
	public DateiSchreiber() {
		leser = new DateiLeser();
		xmlOutputter = new XMLOutputter(Format.getPrettyFormat().setIndent("    "));
	}
	
	public void schreibe(SchlangenjagdModell schlangenjagdModell, String fileName) throws JDOMException, IOException {
		Document document = leser.lese(fileName);
		fileWriter = new FileWriter(fileName);
		buildDocument(schlangenjagdModell, document);
		xmlOutputter.output(document, fileWriter);
	}
	
	private void buildDocument(SchlangenjagdModell schlangenjagdModell, Document document) {
		addZeit(schlangenjagdModell, document);
		addDschungel(schlangenjagdModell, document);
		addSchlangenarten(schlangenjagdModell, document);
		removeSchlangen(document);
		if(schlangenjagdModell.getSchlangen() != null) {
			addSchlangen(schlangenjagdModell, document);
		}
	}
	
	private void addZeit(SchlangenjagdModell schlangenjagdModell, Document document) {
		Element root = document.getRootElement();
		Element zeitElement = root.getChild("Zeit");
		Element vorgabeElement;
		if(zeitElement == null) {
			zeitElement = new Element("Zeit");
			zeitElement.setAttribute("einheit", "s");
			vorgabeElement = new Element("Vorgabe");
			zeitElement.addContent(vorgabeElement);
			root.addContent(0, zeitElement);
		} else {
			vorgabeElement = zeitElement.getChild("Vorgabe");
		}
		long divisor = Zeiteinheit.valueOf(zeitElement.getAttributeValue("einheit")).getMultiplicator();
		vorgabeElement.setText("" + (schlangenjagdModell.getVorgabeZeit() / divisor));
		if(schlangenjagdModell.getAbgabeZeit() > 0) {
			Element abgabeElement = zeitElement.getChild("Abgabe");
			if(abgabeElement == null) {
				abgabeElement = new Element("Abgabe");
				zeitElement.addContent(abgabeElement);
			}
			Double abgabeZeit = ((double)schlangenjagdModell.getAbgabeZeit()) / divisor;
			abgabeElement.setText("" + abgabeZeit);
		} else {
			zeitElement.removeChild("Abgabe");
		}
	}
	
	private void addDschungel(SchlangenjagdModell schlangenjagdModell, Document document) {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		Element root = document.getRootElement();
		Element dschungelElement = root.getChild("Dschungel");
		dschungelElement.setAttribute("zeilen", "" + dschungel.getZeilen());
		dschungelElement.setAttribute("spalten", "" + dschungel.getSpalten());
		dschungelElement.setAttribute("zeichen", dschungel.getZeichenmenge());
		addFelder(dschungelElement, dschungel);
	}
	
	private void addSchlangenarten(SchlangenjagdModell schlangenjagdModell, Document document) {
		Schlangenart[] schlangenarten = schlangenjagdModell.getSchlangenarten();
		Element root = document.getRootElement();
		Element schlangenartenElement = root.getChild("Schlangenarten");
		schlangenartenElement.removeChildren("Schlangenart");
		for(Schlangenart schlangenart : schlangenarten) {
			Element schlangenartElement = new Element("Schlangenart");
			schlangenartElement.setAttribute("id", schlangenart.getId());
			schlangenartElement.setAttribute("punkte", "" + schlangenart.getPunkte());
			schlangenartElement.setAttribute("anzahl", "" + schlangenart.getAnzahl());
			Element zeichenketteElement = new Element("Zeichenkette");
			zeichenketteElement.setText(schlangenart.getZeichenkette());
			schlangenartElement.addContent(zeichenketteElement);
			Nachbarschaftsstruktur nachbarschaftsstruktur = schlangenart.getNachbarschaftsstruktur();
			Element nachbarschaftsstrukturElement = new Element("Nachbarschaftsstruktur");
			nachbarschaftsstrukturElement.setAttribute("typ", nachbarschaftsstruktur.getTyp());
			for(int parameter : nachbarschaftsstruktur.getParameter()) {
				Element parameterElement = new Element("Parameter");
				parameterElement.setAttribute("wert", "" + parameter);
				nachbarschaftsstrukturElement.addContent(parameterElement);
			}
			schlangenartElement.addContent(nachbarschaftsstrukturElement);
			schlangenartenElement.addContent(schlangenartElement);
		}
	}
	
	private void addFelder(Element dschungelElement, Dschungel dschungel) {
		Feld[][] felder = dschungel.getFelder();
		dschungelElement.removeChildren("Feld");
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				Element feldElement = new Element("Feld");
				feldElement.setText(feld.getZeichen());
				feldElement.setAttribute("id", feld.getId());
				feldElement.setAttribute("zeile", "" + feld.getZeile());
				feldElement.setAttribute("spalte", "" + feld.getSpalte());
				feldElement.setAttribute("verwendbarkeit", "" + feld.getVerwendbarkeit());
				feldElement.setAttribute("punkte", "" + feld.getPunkte());
				dschungelElement.addContent(feldElement);
			}
		}
	}
	
	private void removeSchlangen(Document document) {
		Element root = document.getRootElement();
		root.removeChild("Schlangen");
	}
	
	private void addSchlangen(SchlangenjagdModell schlangenjagdModell, Document document) {
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		Element root = document.getRootElement();
		Element schlangenElement = new Element("Schlangen");
		for(Schlange schlange : schlangen) {
			Element schlangeElement = new Element("Schlange");
			String art = schlange.getSchlangenart().getId();
			schlangeElement.setAttribute("art", art);
			for(Schlangenglied schlangenglied : schlange.getSchlangenglieder()) {
				Element schlangengliedElement = new Element("Schlangenglied");
				schlangengliedElement.setAttribute("feld", schlangenglied.getFeld().getId());
				schlangeElement.addContent(schlangengliedElement);
			}
			schlangenElement.addContent(schlangeElement);
		}
		root.addContent(schlangenElement);
	}
}
