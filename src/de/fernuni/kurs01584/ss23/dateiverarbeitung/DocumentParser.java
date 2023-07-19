package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import java.util.List;
import java.util.ArrayList;

import org.jdom2.Document;

import org.jdom2.Element;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Zeiteinheit;

public class DocumentParser {
	private Document document;
	
	/***
	 * Erzeuge einen neuen XMLParser.
	 * @param document Document worauf der XMLParser operieren soll.
	 */
	public DocumentParser(Document document) {
		this.document = document;
	}
	
	/***
	 * Prueft ob es sich bei der Eingabedatei um eine Probleminstanz oder Loesung handelt. 
	 * Parse dann dementsprechend die Eingabedatei in ein SchlangenjagdModell.
	 * @return Ein SchlangenjagdModell welches aus der Eingabedatei erzeugt wurde.
	 * @throws IllegalArgumentException Gibt eine eventuell angefallene IllegalArgumentException weiter.
	 */
	public SchlangenjagdModell parseSchlangenjagd() throws IllegalArgumentException {
		SchlangenjagdModell schlangenjagdModell;
		Element root = document.getRootElement();
		Dschungel dschungel = parseDschungel();
		Schlangenart[] schlangenarten = parseSchlangenarten();
		
		long vorgabeZeit;
		if(root.getChild("Zeit") != null) {
			vorgabeZeit = parseVorgabeZeit();
		} else {
			vorgabeZeit = 60000;
		}
		
		if(root.getChild("Schlangen") != null) {
			if(root.getChild("Schlangen").getChildren().size() > 0) {
				List<Schlange> schlangen = parseSchlangen(schlangenarten, dschungel);
				long abgabeZeit = parseAbgabeZeit();
				schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, vorgabeZeit, abgabeZeit);
			} else {
				schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, vorgabeZeit);
			}
		} else {
			schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, vorgabeZeit);
		}
		
		return schlangenjagdModell;
	}
	
	/***
	 * Parse die Zeitvorgabe aus der Eingabedatei mithilfe der Methode parseZeitInMs.
	 * @return Zeitvorgabe in ms.
	 */
	private long parseVorgabeZeit() {
		return parseZeitInMs("Vorgabe");
	}

	/***
	 * Parse die Abgabezeit aus der Eingabedatei mithilfe der Methode parseZeitInMs.
	 * @return Abgabezeit in ms.
	 */
	private long parseAbgabeZeit() {
		return parseZeitInMs("Abgabe");
	}

	/***
	 * Parse Zeit des gegebenen Typs (Vorgabe oder Abgabe) aus der Eingabedatei.
	 * @return Zeit in ms.
	 */
	private long parseZeitInMs(String typ) {
		// Parse Zeit und Multiplicator (d, h, m, s, ms) aus XML
		Element zeitNode = document.getRootElement().getChild("Zeit");
		long multiplicator = Zeiteinheit.valueOf(zeitNode.getAttributeValue("einheit")).getMultiplicator();
		double zeitWert = Double.parseDouble(zeitNode.getChildText(typ));
		// Berechne daraus die Zeit in ms
		long zeitInMs = (long)(zeitWert * multiplicator);
		
		return zeitInMs;
	}
	
	/***
	 * Parse den gesamten Dschungel aus der Eingabedatei.
	 * @return Dschungel welcher aus der Eingabedatei erzeugt wurde.
	 */
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

	/***
	 * Parse alle Felder des Dschungels aus der Eingabedatei und schreibe sie in den Dschungel.
	 */
	private void parseFelder(Dschungel dschungel, List<Element> feldNodes) {
		// Parse jedes Feld aus der XML und platziere es im Dschungel
		for (Element feldNode : feldNodes) {
			String zeichen = feldNode.getText();
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
			dschungel.befuelleLeereFelder();
		}
	}

	/***
	 * Parse alle Schlangenarten aus der Eingabedatei und fasse sie in einem Array zusammen.
	 * @return Schlangenart-Array aus den Schlangenarten der Eingabedatei.
	 */
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

	/***
	 * Parse alle Schlangen aus der Eingabedatei und fasse sie in einer Liste zusammen.
	 * @return Schlangen-Liste aus den Schlangen der Eingabedatei.
	 */
	private List<Schlange> parseSchlangen(Schlangenart[] schlangenarten, Dschungel dschungel) {
		List<Schlange> schlangen = new ArrayList<Schlange>();
		Element schlangenNode = document.getRootElement().getChild("Schlangen");
		for (Element schlangeNode : schlangenNode.getChildren()) {
			for (Schlangenart schlangenart : schlangenarten) {
				if (schlangenart.getId().equals(schlangeNode.getAttributeValue("art"))) {
					Schlange schlange = new Schlange(schlangenart);
					List <Element> schlangengliedNodes = schlangeNode.getChildren();
					for (int index = 0; index < schlangengliedNodes.size(); index++) {
						Element schlangengliedNode = schlangengliedNodes.get(index);
						Feld feld = dschungel.getFeldById(schlangengliedNode.getAttributeValue("feld"));
						schlange.addSchlangenglied(feld);
					}
					schlangen.add(schlange);
					break;
				}
			}
		}
		return schlangen;
	}
	
}
