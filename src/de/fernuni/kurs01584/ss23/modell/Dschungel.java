package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class Dschungel {
	private int zeilen;
	private int spalten;
	private String zeichenmenge;
	private Feld[][] felder;
	
	/***
	 * Erzeugt eine Instanz der Klasse 'Dschungel' mit den angegebenen Parametern
	 * @param zeichenmenge
	 * @param zeilen
	 * @param spalten
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
	 * Gibt die Zeilen, die der Dschungel umfässt zurück
	 * @return Zeilen des Dschungels
	 */
	public int getZeilen() {
		return zeilen;
	}

	/***
	 * Gibt die Spalten, die der Dschungel umfässt zurück
	 * @return Spalten des Dschungels
	 */
	public int getSpalten() {
		return spalten;
	}
	
	/***
	 * Gibt die Zeichenmenge, welche der Dschungel zulässt zurück
	 * @return Zeichenmenge des Dschungels
	 */
	public String getZeichenmenge() {
		return zeichenmenge;
	}
	
	/***
	 * Gibt alle Felder des Dschungels als zweidimensionales Array zurück
	 * @return Felder des Dschungels
	 */
	public Feld[][] getFelder() {
		return felder;
	}
	
	/***
	 * Gibt das Feld an der angegebenen Position im Dschungel zurück
	 * @param zeile
	 * @param spalte
	 * @return Feld an angegebener Position
	 */
	public Feld getFeld(int zeile, int spalte) {
		return felder[zeile][spalte];
	}
	
	/***
	 * Gibt das Feld mit der gegebenen ID zurück
	 * @param id
	 * @return Feld mit gegebener ID
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
	 * Setzt das Feld an der angegebenen Position mit den entsprechenden Parametern in den Dschungel
	 * @param id
	 * @param zeichen
	 * @param zeile
	 * @param spalte
	 * @param punkte
	 * @param verwendbarkeit
	 */
	public void setFeld(String id, String zeichen, int zeile, int spalte, int punkte, int verwendbarkeit) throws IllegalArgumentException {
		felder[zeile][spalte] = new Feld(id, zeichen, zeile, spalte, punkte, verwendbarkeit);
		
	}
	
	/***
	 * Setzt das Feld an der angegebenen Position mit den entsprechenden Parametern und den
	 * Standardwerten 1 für Punkte und Verwendbarkeit in den Dschungel
	 * @param id
	 * @param zeichen
	 * @param zeile
	 * @param spalte
	 */
	public void setFeld(String id, String zeichen, int zeile, int spalte) throws IllegalArgumentException {
		setFeld(id, zeichen, zeile, spalte, 1, 1);
	}
	
	/***
	 * Setzt das Feld an der angegebenen Position mit den Standardwerten 
	 * 0 für Punkte und Verwendbarkeit in den Dschungel
	 * @param zeile
	 * @param spalte
	 */
	public void setFeld(String zeichen, int zeile, int spalte) throws IllegalArgumentException {
		// Unvollstaendiges Feld, berechne dementsprechend die ID
		String id = "F" + (zeile*spalten+spalte);
		setFeld(id, zeichen, zeile, spalte, 1, 1);
	}
	
	/***
	 * Setzt das Feld an der angegebenen Position mit den Standardwerten 
	 * 0 für Punkte und Verwendbarkeit in den Dschungel
	 * @param zeile
	 * @param spalte
	 */
	public void setFeld(int zeile, int spalte) throws IllegalArgumentException {
		// Unvollstaendiges Feld, berechne dementsprechend die ID
		String id = "F" + (zeile*spalten+spalte);
		felder[zeile][spalte] = new Feld(id, zeile, spalte);
	}
	
	/***
	 * Setzt ein bereits vordefiniertes Feld an die angegebene Position im Dschungel
	 * @param feld
	 * @param zeile
	 * @param spalte
	 */
	public void setFeld(Feld feld) {
		felder[feld.getZeile()][feld.getSpalte()] = feld;
	}
	
	/***
	 * Setzt die Felder des Dschungel zurück
	 */
	public void loescheFelder() {
		felder = new Feld[zeilen][spalten];
	}
	
	public void befuelleLeereFelder() {
		for (int zeile = 0; zeile < zeilen; zeile++) {
			for (int spalte = 0; spalte < spalten; spalte++) {
				if (felder[zeile][spalte] == null) {
					setFeld(zeile, spalte);
				}
			}
		}
	}
	
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
