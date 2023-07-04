package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.util.List;

import org.jdom2.Document;

import org.jdom2.Element;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Zeiteinheit;

public class XMLParser {
	private Document document;
	private SchlangenjagdModell schlangenjagdModell;
	
	public XMLParser(Document document) {
		this.document = document;
		long zeitVorgabe = parseZeitVorgabe();
		Dschungel dschungel = parseDschungel();
		Schlangenart[] schlangenarten = parseSchlangenarten();
		schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, zeitVorgabe);
	}
	
	private long parseZeitVorgabe() {
		// Parse Zeit und Multiplicator (d, h, m, s, ms) aus XML
		Element zeitNode = document.getRootElement().getChild("Zeit");
		long multiplicator = Zeiteinheit.valueOf(zeitNode.getAttributeValue("einheit")).getMultiplicator();
		double zeitWert = Double.parseDouble(zeitNode.getChildText("Vorgabe"));
		// Berechne daraus die Zeit Vorgabe in ms
		long zeitVorgabe = (long)(zeitWert * multiplicator);
		
		return zeitVorgabe;
	}
	
	private Dschungel parseDschungel() {
		// Parse Dschungelparameter (Zeichenmenge, Zeilen, Spalten) aus XML
		Element dschungelNode = document.getRootElement().getChild("Dschungel");
		String zeichenmenge = dschungelNode.getAttributeValue("zeichen");
		int zeilen = Integer.parseInt(dschungelNode.getAttributeValue("zeilen"));
		int spalten = Integer.parseInt(dschungelNode.getAttributeValue("spalten"));
		// Initialisiere Dschungel
		Dschungel dschungel = new Dschungel(zeichenmenge, zeilen, spalten);
		// Befuelle Dschungel
		parseFelder(dschungel, dschungelNode.getChildren());
		
		return dschungel;
	}
	
	private void parseFelder(Dschungel dschungel, List<Element> feldNodes) {
		// Parse jedes Feld aus der XML und platziere es im Dschungel
		for (Element feldNode : feldNodes) {
			char zeichen = feldNode.getText().charAt(0);
			String id = feldNode.getAttributeValue("id");
			int zeile = Integer.parseInt(feldNode.getAttributeValue("zeile"));
			int spalte = Integer.parseInt(feldNode.getAttributeValue("spalte"));
			int punkte = Integer.parseInt(feldNode.getAttributeValue("punkte"));
			int verwendbarkeit = Integer.parseInt(feldNode.getAttributeValue("verwendbarkeit"));
			
			dschungel.setFeld(id, zeichen, zeile, spalte, punkte, verwendbarkeit);
		}
		
		// Wenn das feldNodes Array kleiner ist als spalten * zeilen, 
		// dann ist die Probleminstanz unvollstaendig und fehlende Felder muessen
		// ergänzt werden.
		if (feldNodes.size() < (dschungel.getSpalten() * dschungel.getZeilen())) {
			befuelleUeberbleibendeFelder(dschungel);
		}
	}
	
	private void befuelleUeberbleibendeFelder(Dschungel dschungel) {
		for (int zeile = 0; zeile < dschungel.getZeilen(); zeile++) {
			for (int spalte = 0; spalte < dschungel.getSpalten(); spalte++) {
				if (dschungel.getFeld(zeile, spalte) == null) {
					dschungel.setFeld(zeile, spalte);
				}
			}
		}
	}
	
	private Schlangenart[] parseSchlangenarten() {
		Element schlangenartenNode = document.getRootElement().getChild("Schlangenarten");
		List<Element> schlangenartNodes = schlangenartenNode.getChildren();
		Schlangenart[] schlangenarten = new Schlangenart[schlangenartNodes.size()];
		// Jede Schlangenart im XML fuegen wir nun dem schlangenarten Array hinzu
		for (int i = 0; i < schlangenartNodes.size(); i++) {
			String id = schlangenartNodes.get(i).getAttributeValue("id");
			String zeichenkette = schlangenartNodes.get(i).getChildText("Zeichenkette");
			int punkte = Integer.parseInt(schlangenartNodes.get(i).getAttributeValue("punkte"));
			int anzahl = Integer.parseInt(schlangenartNodes.get(i).getAttributeValue("anzahl"));
			Element nachbarschaftsstrukturNode = schlangenartNodes.get(i).getChild("Nachbarschaftsstruktur");
			
			// Wir differenzieren bei der Nachbarschaftsstruktur zwischen Distanz und Sprung
			// Fuer jeden dieser Faelle brauchen wir einen unterschiedlichen Konstruktor
			if (nachbarschaftsstrukturNode.getAttributeValue("typ").equals("Distanz")) {
				int distanz = Integer.parseInt(nachbarschaftsstrukturNode.getChild("Parameter").getAttributeValue("wert"));
				schlangenarten[i] = new Schlangenart(id, zeichenkette, punkte, anzahl, distanz);
			} else {
				List<Element> parameterNodes = nachbarschaftsstrukturNode.getChildren("Parameter");
				int x = Integer.parseInt(parameterNodes.get(0).getAttributeValue("wert"));
				int y = Integer.parseInt(parameterNodes.get(1).getAttributeValue("wert"));
				schlangenarten[i] = new Schlangenart(id, zeichenkette, punkte, anzahl, x, y);
			}
		}
		return schlangenarten;
	}

	public SchlangenjagdModell getSchlangenjagdModell() {
		return schlangenjagdModell;
	}
	
}
