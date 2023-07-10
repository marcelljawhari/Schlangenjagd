package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
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
	
	private void addSchlangen(SchlangenjagdModell schlangenjagdModell, Document document) {
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		Element root = document.getRootElement();
		Element schlangenElement = root.getChild("Schlangen");
		if(schlangenElement == null) {
			schlangenElement = new Element("Schlangen");
			root.addContent(schlangenElement);
		}
		schlangenElement.removeChildren("Schlange");
		if(schlangen == null) {
			root.removeChild("Schlangen");
		} else {
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
		}
	}
}
