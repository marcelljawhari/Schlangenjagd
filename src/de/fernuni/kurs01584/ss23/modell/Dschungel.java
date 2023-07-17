package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class Dschungel {
	private int zeilen;
	private int spalten;
	private String zeichenmenge;
	private Feld[][] felder;
	
	/***
	 * Erzeugt einen Dschungel.
	 * @param zeichenmenge	Zeichen die im Dschungel vorkommen neben den Zeichen der Schlangenarten.
	 * @param zeilen		Zeilen die der Dschungel umfassen soll.
	 * @param spalten		Spalten die der Dschungel umfassen soll.
	 */
	public Dschungel(String zeichenmenge, int zeilen, int spalten) throws IllegalArgumentException {
		if(zeilen <= 0 || spalten <= 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Dschungel' duerfen die Attribute 'zeilen' und 'spalten' keine Werte kleiner oder gleich 0 annehmen.");
		}
		this.zeichenmenge = zeichenmenge;
		this.zeilen = zeilen;
		this.spalten = spalten;
		felder = new Feld[zeilen][spalten];
	}
	
	/***
	 * Gibt die Zeilen, die der Dschungel umfässt zurück.
	 * @return Zeilen des Dschungels.
	 */
	public int getZeilen() {
		return zeilen;
	}

	/***
	 * Gibt die Spalten, die der Dschungel umfässt zurück.
	 * @return Spalten des Dschungels.
	 */
	public int getSpalten() {
		return spalten;
	}
	
	/***
	 * Gibt die Zeichenmenge, welche der Dschungel, neben den Zeichen der Schlangenarten, zulässt zurück.
	 * @return Zeichenmenge des Dschungels.
	 */
	public String getZeichenmenge() {
		return zeichenmenge;
	}
	
	/***
	 * Gibt alle Felder des Dschungels als zweidimensionales Array zurück.
	 * @return Zweidimensionales Felder-Array des Dschungels.
	 */
	public Feld[][] getFelder() {
		return felder;
	}
	
	/***
	 * Gibt das Feld an der angegebenen Position im Dschungel zurück
	 * @param zeile		Zeile des Feldes im Dschungel.
	 * @param spalte	Spalte des Feldes im Dschungel.
	 * @return Feld an angegebener Position.		
	 */
	public Feld getFeld(int zeile, int spalte) {
		return felder[zeile][spalte];
	}
	
	/***
	 * Gibt das Feld mit der gegebenen ID zurück.
	 * @param id ID des gesuchten Feldes.
	 * @return Feld mit gegebener ID.
	 */
	public Feld getFeldById(String id) {
		for (Feld[] zeile : felder) {
			for (Feld feld : zeile) {
				if (feld.getId().equals(id)) {
					return feld;
				}
			}
		}
		return null;
	}

	/***
	 * Gibt alle Felder zurück, welche das angegebene Zeichen enthalten.
	 * @param zeichen	Zeichen der gesuchten Felder.
	 * @return Eine Liste von Feldern, welche das angegebene Zeichen enthalten.
	 */
	public List<Feld> getFelderByZeichen(String zeichen) {
		List<Feld> felderByZeichen = new ArrayList<Feld>();
		for (Feld[] zeile : felder) {
			for (Feld feld : zeile) {
				if (feld.getZeichen().equals(zeichen)) {
					felderByZeichen.add(feld);
				}
			}
		}
		return felderByZeichen;
	}
	
	/***
	 * Gibt die Verwendbarkeiten aller Felder als zweidimensionales Integer-Array zurück.
	 * @return Zweidimensionales Integer-Array der Verwendbarkeiten aller Felder.
	 */
	public int[][] getVerwendbarkeiten() {
		int[][] verwendbarkeiten = new int[zeilen][spalten];
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				verwendbarkeiten[zeile][spalte] = felder[zeile][spalte].getVerwendbarkeit();
			}
		}
		return verwendbarkeiten;
	}
	
	/***
	 * Erzeugt ein Feld und setzt es anschließend in den Dschungel.
	 * @param id				ID des Feldes.
	 * @param zeichen			Zeichen des Feldes.
	 * @param zeile				Zeile des Feldes im Dschungel.
	 * @param spalte			Spalte des Feldes im Dschungel.
	 * @param punkte			Punkte die durch belegen des Feldes der Lösung gutgeschrieben werden.
	 * @param verwendbarkeit	Wir oft das Feld belegt werden kann.
	 */
	public void setFeld(String id, String zeichen, int zeile, int spalte, int punkte, int verwendbarkeit) throws IllegalArgumentException {
		felder[zeile][spalte] = new Feld(id, zeichen, zeile, spalte, punkte, verwendbarkeit);
		
	}
	
	/***
	 * Erzeugt ein Feld, mit den Standardwerten 1 Punkt und 1 Verwendbarkeit, 
	 * und setzt es anschließend in den Dschungel.
	 * @param id		ID des Feldes.
	 * @param zeichen	Zeichen des Feldes.
	 * @param zeile		Zeile des Feldes im Dschungel.
	 * @param spalte	Spalte des Feldes im Dschungel.
	 */
	public void setFeld(String id, String zeichen, int zeile, int spalte) throws IllegalArgumentException {
		setFeld(id, zeichen, zeile, spalte);
	}
	
	/***
	 * Erzeugt ein Feld, mit Standardwerten 1 Punkt und 1 Verwendbarkeit 
	 * und platziert es im Dschungel. Berechnet außerdem die ID.
	 * @param zeile		Zeile des Feldes im Dschungel.
	 * @param spalte	Spalte des Feldes im Dschungel.
	 */
	public void setFeld(String zeichen, int zeile, int spalte) throws IllegalArgumentException {
		// Unvollstaendiges Feld, berechne die ID
		String id = "F" + (zeile*spalten+spalte);
		setFeld(id, zeichen, zeile, spalte);
	}
	
	/***
	 * Erzeugt ein leeres Feld, berechnet die ID und platziert es im Dschungel.
	 * @param zeile		Zeile des Feldes im Dschungel.
	 * @param spalte	Spalte des Feldes im Dschungel.
	 */
	public void setFeld(int zeile, int spalte) throws IllegalArgumentException {
		// Unvollstaendiges Feld, berechne dementsprechend die ID
		String id = "F" + (zeile*spalten+spalte);
		felder[zeile][spalte] = new Feld(id, zeile, spalte);
	}
	
	/***
	 * Platziert ein bereits vorhandenes Feld im Dschungel.
	 * @param feld		Feld welches platziert werden soll.
	 * @param zeile		Zeile des Feldes im Dschungel.
	 * @param spalte	Spalte des Feldes im Dschungel.
	 */
	public void setFeld(Feld feld) {
		felder[feld.getZeile()][feld.getSpalte()] = feld;
	}
	
	/***
	 * Loescht alle im Dschungel platzierten Felder.
	 */
	public void loescheFelder() {
		felder = new Feld[zeilen][spalten];
	}
	
	/***
	 * Befuellt alle Felder, die im Dschungel noch nicht platziert wurden, mit leeren Feldern.
	 */
	public void befuelleLeereFelder() {
		for (int zeile = 0; zeile < zeilen; zeile++) {
			for (int spalte = 0; spalte < spalten; spalte++) {
				if (felder[zeile][spalte] == null) {
					setFeld(zeile, spalte);
				}
			}
		}
	}
	
	/***
	 * Befuellt alle Felder, die im Dschungel noch nicht platziert wurden, mit neuen Feldern.
	 * Hierbei wird das Zeichen zufaellig aus der Zeichenmenge gewaehlt, 1 Punkt und 1 Verwendbarkeit zugeteilt. 
	 */
	public void befuelleRestlicheFelder() {
		for (int zeile = 0; zeile < zeilen; zeile++) {
			for (int spalte = 0; spalte < spalten; spalte++) {
				if (felder[zeile][spalte] == null) {
					int index = (int) (Math.random() * (zeichenmenge.length() - 1));
					setFeld("" + zeichenmenge.charAt(index), zeile, spalte);
				}
			}
		}
	}
}
