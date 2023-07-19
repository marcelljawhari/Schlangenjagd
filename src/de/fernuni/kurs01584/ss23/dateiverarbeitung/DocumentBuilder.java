package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.util.List;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Nachbarschaftsstruktur;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Zeiteinheit;

public class DocumentBuilder {
	
	/***
	 * Erkennt ob das SchlangenjagdModell eine Loesung beinhaelt und baut
	 * anschließend dementsprechend ein JDOM Document auf, welches der Schlangenjagd DTD entspricht.
	 * @param schlangenjagdModell SchlangenjagdModell welches als Document gespeichert werden soll.
	 * @return JDOM Document basierend auf dem SchlangenjagdModell und der Schlangenjagd DTD.
	 */
	public Document build(SchlangenjagdModell schlangenjagdModell) {
		Document document = new Document();
		addDocType(document);
		addRoot(document);
		addZeit(schlangenjagdModell, document);
		addDschungel(schlangenjagdModell, document);
		addSchlangenarten(schlangenjagdModell, document);
		if(schlangenjagdModell.getSchlangen() != null) {
			addSchlangen(schlangenjagdModell, document);
		}
		return document;
	}
	
	/***
	 * Fuegt dem Document einen DocType hinzu.
	 * @param document Document welches angepasst werden soll.
	 */
	private void addDocType(Document document) {
		DocType docType = new DocType("Schlangenjagd", "schlangenjagd.dtd");
		document.setDocType(docType);
	}
	
	/***
	 * Fuegt dem Document ein RootElement "Schlangenjagd" hinzu.
	 * @param document Document welches angepasst werden soll.
	 */
	private void addRoot(Document document) {
		Element schlangenjagd = new Element("Schlangenjagd");
		document.setRootElement(schlangenjagd);
	}
	
	/***
	 * Fuegt dem Document ein Element "Zeit" als Child des RootElements hinzu.
	 * @param schlangenjagdModell SchlangenjagdModell welches die Zeitangaben beinhaelt.
	 * @param document Document welches angepasst werden soll.
	 */
	private void addZeit(SchlangenjagdModell schlangenjagdModell, Document document) {
		Element root = document.getRootElement();
		Element zeitElement = new Element("Zeit");
		zeitElement.setAttribute("einheit", "s");
		Element vorgabeElement = new Element("Vorgabe");
		zeitElement.addContent(vorgabeElement);
		root.addContent(zeitElement);
		
		long divisor = Zeiteinheit.s.getMultiplicator();
		vorgabeElement.setText("" + ((double)schlangenjagdModell.getVorgabeZeit() / divisor));
		if(schlangenjagdModell.getAbgabeZeit() > 0) {
			Element abgabeElement = new Element("Abgabe");
			Double abgabeZeit = ((double)schlangenjagdModell.getAbgabeZeit()) / divisor;
			abgabeElement.setText("" + abgabeZeit);
			zeitElement.addContent(abgabeElement);
		}
	}
	
	/***
	 * Fuegt dem Document ein Element "Dschungel" hinzu und befuellt diese anschließend
	 * mit den entsprechenden Feldern mithilfe der Methode addFelder().
	 * @param schlangenjagdModell SchlangenjagdModell welches den Dschungel beinhaelt.
	 * @param document Document welches angepasst werden soll.
	 */
	private void addDschungel(SchlangenjagdModell schlangenjagdModell, Document document) {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		Element root = document.getRootElement();
		
		Element dschungelElement = new Element("Dschungel");
		dschungelElement.setAttribute("zeilen", "" + dschungel.getZeilen());
		dschungelElement.setAttribute("spalten", "" + dschungel.getSpalten());
		dschungelElement.setAttribute("zeichen", dschungel.getZeichenmenge());
		addFelder(dschungelElement, dschungel);
		root.addContent(dschungelElement);
	}
	
	/***
	 * Fuegt dem Document "Feld" Elemente hinzu als Children des Elements "Dschungel".
	 * @param dschungelElement SchlangenjagdModell welches die Felder beinhaelt.
	 * @param dschungel Document welches angepasst werden soll.
	 */
	private void addFelder(Element dschungelElement, Dschungel dschungel) {
		Feld[][] felder = dschungel.getFelder();
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
	
	/***
	 * Fuegt dem Document ein "Schlangenarten" Element hinzu mit allen Schlangenarten als Children.
	 * @param schlangenjagdModell SchlangenjagdModell welches die Schlangenarten beinhaelt.
	 * @param document Document welches angepasst werden soll.
	 */
	private void addSchlangenarten(SchlangenjagdModell schlangenjagdModell, Document document) {
		Schlangenart[] schlangenarten = schlangenjagdModell.getSchlangenarten();
		Element root = document.getRootElement();
		Element schlangenartenElement = new Element("Schlangenarten");
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
		root.addContent(schlangenartenElement);
	}
	
	/***
	 * Fuegt dem Document ein "Schlangen" Element hinzu mit allen Schlangen als Children.
	 * @param schlangenjagdModell SchlangenjagdModell welches die Schlangen beinhaelt.
	 * @param document Document welches angepasst werden soll.
	 */
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
