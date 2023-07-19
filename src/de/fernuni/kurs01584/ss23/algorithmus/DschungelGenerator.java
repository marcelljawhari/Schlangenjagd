package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import de.fernuni.kurs01584.ss23.modell.*;

public class DschungelGenerator {
	private SchlangenjagdModell schlangenjagdModell;
	private Dschungel dschungel;
	private int zeilen, spalten;
	private Schlangenart[] schlangenarten;
	private List<Schlange> schlangen;
	private long vorgabeZeit, startZeit;
	private int schlangenAnzahl;
	private Feld[][] felder;
	
	/***
	 * Erzeugt einen DschungelGenerator fuer das gegebene SchlangenjagdModell.
	 * @param schlangenjagdModell SchlangenjagdModell auf welchen der DschungelGenerator operieren soll.
	 */
	public DschungelGenerator(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	/***
	 * Generiert den Dschungel. Fuer jede Schlangenart wird jede Schlange zufaellig im Dschungel platziert.
	 * Die restlichen Felder werden mit zufaelligen Zeichen der Zeichenmenge befuellt.
	 * @throws IllegalArgumentException Erzeugt eine IllegalArgumentException die gewuenschten Schlangenarten und deren Anzahlen
	 * 									mehr Felder benoetigen wuerden als der Dschungel fassen kann.
	 * @throws TimeoutException Erzeugt eine TimeoutException wenn der Dschungel nicht in der Zeitvorgabe erzeugt werden konnte.
	 */
	public void generiereDschungel() throws IllegalArgumentException, TimeoutException {
		dschungel = schlangenjagdModell.getDschungel();
		zeilen = dschungel.getZeilen();
		spalten = dschungel.getSpalten();
		schlangenarten = schlangenjagdModell.getSchlangenarten();
		vorgabeZeit = schlangenjagdModell.getVorgabeZeit();
		startZeit = System.currentTimeMillis();
		schlangenAnzahl = getBenoetigteSchlangenAnzahl();
		felder = new Feld[zeilen][spalten];
		
		if(zeilen*spalten < getBenoetigteFelderAnzahl()) {
			throw new IllegalArgumentException("Im Dschungel sind nicht genug Felder vorhanden um alle Schlangenarten"
					+ " mit der gewuenschten Anzahl zu platzieren.");
		}
		platziereSchlangen();
		dschungel.befuelleRestlicheFelder();
	}
	
	/***
	 * Platziere alle Schlangen im Dschungel
	 * @throws TimeoutException Erzeugt eine TimeoutException wenn der Dschungel nicht in der Zeitvorgabe erzeugt werden konnte.
	 */
	private void platziereSchlangen() throws TimeoutException {
		schlangen = new ArrayList<Schlange>();
		dschungel.loescheFelder();
		platziereSchlange();
		speichereDschungel();
	}
	
	/***
	 * Platziere eine Schlange im Dschungel. 
	 * Falls alle Schlangen platziert sind breche die Rekursion ab.
	 * Falls die Zeitvorgabe erreicht wurde breche die Rekursion mit einer TimeoutException ab.
	 * Andernfalls erzeuge einen rekursiven Aufruf um die naechste Schlange zu platzieren. 
	 * @throws TimeoutException Erzeugt eine TimeoutException wenn der Dschungel nicht in der Zeitvorgabe erzeugt werden konnte.
	 */
	private void platziereSchlange() throws TimeoutException {
		if(alleSchlangenPlatziert()) {
			return;
		}
		if(vorgabeZeitErreicht()) {
			throw new TimeoutException("Der Dschungel konnte nicht in der vorgegebenen Zeit erstellt werden.");
		}
		List<int[]> verfuegbareKoordinaten = getVerfuegbareKoordinaten();
		for(int[] koordinaten : verfuegbareKoordinaten) {
			List<Schlangenart> verbleibendeSchlangenarten = getVerbleibendeSchlangenarten();
			for(Schlangenart schlangenart : verbleibendeSchlangenarten) {
				int zeile = koordinaten[0];
				int spalte = koordinaten[1];
				String zeichen = "" + schlangenart.getZeichenkette().charAt(0);
				Feld startfeld = erzeugeFeld(zeichen, zeile, spalte);
				Schlange schlange = new Schlange(schlangenart);
				Schlangenglied schlangenkopf = new Schlangenglied(0, startfeld, schlange);
				addSchlange(schlange, schlangenkopf, startfeld);
				platziereSchlangenglied(schlange, schlangenkopf);
				if(alleSchlangenPlatziert()) {
					return;
				}
				removeSchlange(schlange, startfeld);
			}
		}
	}
	
	/***
	 * Platziere ein Schlangenglied im Dschungel.
	 * Falls alle Schlangen platziert sind breche die Rekursion ab.
	 * Falls die momentane Schlange vollstaendig ist, platziere die naechste Schlange.
	 * Falls die Zeitvorgabe erreicht wurde breche die Rekursion mit einer TimeoutException ab.
	 * Andernfalls erzeuge einen rekursiven Aufruf um das naechste Schlangenglied zu platzieren.
	 * @param schlange Momentane Schlange zu welcher das naechste Schlangenglied platziert werden soll.
	 * @param vorherigesSchlangenglied Schlangenglied welches zuvor platziert wurde.
	 * @throws TimeoutException Erzeugt eine TimeoutException wenn der Dschungel nicht in der Zeitvorgabe erzeugt werden konnte.
	 */
	private void platziereSchlangenglied(Schlange schlange, Schlangenglied vorherigesSchlangenglied) throws TimeoutException {
		if(alleSchlangenPlatziert()) {
			return;
		}
		if(schlange.isVollstaendig()) {
			platziereSchlange();
			return;
		}
		if(vorgabeZeitErreicht()) {
			throw new TimeoutException("Der Dschungel konnte nicht in der vorgegebenen Zeit erstellt werden.");
		}
		int index = vorherigesSchlangenglied.getIndex() + 1;
		List<int[]> verfuegbareKoordinaten = getVerfuegbareKoordinaten(vorherigesSchlangenglied, 
				schlange.getSchlangenart().getNachbarschaftsstruktur());
		for(int[] koordinaten : verfuegbareKoordinaten) {
			int zeile = koordinaten[0];
			int spalte = koordinaten[1];
			String zeichen = "" + schlange.getSchlangenart().getZeichenkette().charAt(index);
			Feld feld = erzeugeFeld(zeichen, zeile, spalte);
			Schlangenglied schlangenglied = new Schlangenglied(index, feld, schlange);
			addSchlangenglied(schlange, schlangenglied, feld);
			platziereSchlangenglied(schlange, schlangenglied);
			if(alleSchlangenPlatziert()) {
				return;
			}
			removeSchlangenglied(schlange, schlangenglied, feld);
		}
	}
	
	/***
	 * Gibt zurueck ob bereits alle Schlangen platziert wurden.
	 * @return <ttt>true</ttt> falls alle Schlangen platziert sind, andernfalls <ttt>false</ttt>.
	 */
	private boolean alleSchlangenPlatziert() {
		for(Schlangenart schlangenart : schlangenarten) {
			int anzahl = 0;
			for(Schlange schlange : schlangen) {
				if(schlange.getSchlangenart().equals(schlangenart) && schlange.isVollstaendig()) {
					anzahl++;
				}
			}
			if(anzahl < schlangenart.getAnzahl()) {
				return false;
			}
		}
		return true;
	}
	
	/***
	 * Erzeuge ein Feld.
	 * @param zeichen Zeichen des Feldes.
	 * @param zeile Zeile in der das Feld positioniert sein soll.
	 * @param spalte Spalte in der das Feld positioniert sein soll.
	 * @return Das erzeugte Feld.
	 */
	private Feld erzeugeFeld(String zeichen, int zeile, int spalte) {
		String feldId = "F" + (zeile * spalten + spalte);
		Feld feld = new Feld(feldId, zeichen, zeile, spalte, 1, 1);
		return feld;
	}
	
	/***
	 * Fuege eine neue Schlange der Schlangenliste hinzu, initialisiere dessen Kopf 
	 * und fuege das benoetigte Feld dem Felder-Array hinzu.
	 * @param schlange Schlange die platziert wird.
	 * @param schlangenkopf Schlangenkopf der zu platzierenden Schlange.
	 * @param startfeld Feld wo der Schlangenkopf liegt.
	 */
	private void addSchlange(Schlange schlange, Schlangenglied schlangenkopf, Feld startfeld) {
		schlangen.add(schlange);
		schlange.addSchlangenglied(startfeld);
		felder[startfeld.getZeile()][startfeld.getSpalte()] = startfeld;
	}
	
	/***
	 * Entferne die Schlange aus der Schlangenliste und loesche das Feld an dem der
	 * Schlangenkopf lag aus dem Felder-Array.
	 * @param schlange Zu loeschende Schlange.
	 * @param startfeld Feld auf dem der Schlangenkopf lag.
	 */
	private void removeSchlange(Schlange schlange, Feld startfeld) {
		schlangen.remove(schlange);
		felder[startfeld.getZeile()][startfeld.getSpalte()] = null;
	}
	
	/***
	 * Fuege ein neues Schlangenglied der Schlange hinzu 
	 * und fuege das benoetigte Feld dem Felder-Array hinzu.
	 * @param schlange Schlange dessen Schlangenglied platziert wird.
	 * @param schlangenglied Zu platzierendes Schlangenglied.
	 * @param feld Feld das durch das Schlangenglied belegt wird.
	 */
	private void addSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.addSchlangenglied(schlangenglied);
		felder[feld.getZeile()][feld.getSpalte()] = feld;
	}
	
	/***
	 * Entferne das Schlangenglied aus der Schlange
	 * und entferne das zugehoerige Feld aus dem Felder-Array.
	 * @param schlange Schlange welcher das Schlangenglied entfernt wird.
	 * @param schlangenglied Zu entfernendes Schlangenglied.
	 * @param feld Feld auf dem das Schlangenglied lag.
	 */
	private void removeSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.removeSchlangenglied();
		felder[feld.getZeile()][feld.getSpalte()] = null;
	}
	
	/***
	 * Generiere eine Liste aller verbleibenden Schlangenarten
	 * und mische diese zufaellig.
	 * @return Liste aller verbleibenden Schlangenarten.
	 */
	private List<Schlangenart> getVerbleibendeSchlangenarten() {
		List<Schlangenart> verbleibendeSchlangenarten = new ArrayList<Schlangenart>();
		for(Schlangenart schlangenart : schlangenarten) {
			int anzahl = schlangenart.getAnzahl();
			for(Schlange schlange : schlangen) {
				if(schlange.getSchlangenart().equals(schlangenart)) {
					anzahl--;
				}
			}
			if(anzahl > 0) {
				verbleibendeSchlangenarten.add(schlangenart);
			}
		}
		return verbleibendeSchlangenarten;
	}
	
	/***
	 * Generiere eine Liste aller Koordinaten von Feldern welche noch frei sind
	 * und mische diese zufaellig.
	 * @return Liste der Koordinaten aller freien Felder.
	 */
	private List<int[]> getVerfuegbareKoordinaten() {
		List<int[]> verfuegbareKoordinaten = new ArrayList<int[]>();
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				if(felder[zeile][spalte] == null) {
					int[] koordinaten = {zeile, spalte}; 
					verfuegbareKoordinaten.add(koordinaten);
				}
			}
		}
		Collections.shuffle(verfuegbareKoordinaten);
		return verfuegbareKoordinaten;
	}
	
	/***
	 * Generiere eine Liste aller Koordinaten von Feldern welche noch frei sind
	 * und mittels der gegebenen Nachbarschaftsstruktur vom Feld des vorherigen
	 * Schlangengliedes aus erreichbar sind und mische diese zufaellig.
	 * @param vorherigesSchlangenglied Vorherige Schlangenglied von welchen aus die verfuegbaeren Nachbarn ermittelt werden.
	 * @param nachbarschaftsstruktur Nachbarschaftsstruktur welche in der Berechnung beachtet wird.
	 * @return Liste aller Koordinaten freier und zugaenglichen Felder.
	 */
	private List<int[]> getVerfuegbareKoordinaten(Schlangenglied vorherigesSchlangenglied, Nachbarschaftsstruktur nachbarschaftsstruktur) {
		List<int[]> verfuegbareKoordinaten = new ArrayList<int[]>();
		int vorherigeZeile = vorherigesSchlangenglied.getFeld().getZeile();
		int vorherigeSpalte = vorherigesSchlangenglied.getFeld().getSpalte();
		int[][] deltas = nachbarschaftsstruktur.getDeltas();
		for(int[] delta : deltas) {
			int zeile = vorherigeZeile + delta[0];
			int spalte = vorherigeSpalte + delta[1];
			if(zeile >= 0 && zeile < zeilen && spalte >= 0 && spalte < spalten) {
				if(felder[zeile][spalte] == null) {
					int[] koordinaten = {zeile, spalte};
					verfuegbareKoordinaten.add(koordinaten);
				}
			}
		}
		Collections.shuffle(verfuegbareKoordinaten);
		return verfuegbareKoordinaten;
	}
	
	/***
	 * Schreibe alle belegten Felder in den Dschungel des SchlangenjagdModells.
	 */
	private void speichereDschungel() {
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld != null) {
					dschungel.setFeld(feld);
				}
			}
		}
	}
	
	/***
	 * Gibt die Anzahl der gesamt benoetigten Schlangen zurueck.
	 * @return Anzahl der gesamt benoetigten Schlangen.
	 */
	private int getBenoetigteSchlangenAnzahl() {
		int anzahl = 0;
		for(Schlangenart schlangenart : schlangenarten) {
			anzahl += schlangenart.getAnzahl();
		}
		return anzahl;
	}
	
	/***
	 * Gibt zurueck ob die Zeitvorgabe erreicht wurde.
	 * @return <ttt>true</ttt> falls Zeitvorgabe erreicht wurde, andernfalls <ttt>false</ttt>.
	 */
	private boolean vorgabeZeitErreicht() {
		return (getVergangeneZeit() >= vorgabeZeit);
	}
	
	/***
	 * Gibt die noch verbleibende Zeit fuer das Generieren zurueck.
	 * @return Verbleibende Zeit.
	 */
	private long getVergangeneZeit() {
		return (System.currentTimeMillis() - startZeit);
	}
	
	/***
	 * Gibt die Anzahl Felder zurueck die benoetigt waeren um alle Schlangen im Dschungel zu verstecken.
	 * @return Anzahl aller benoetigten Felder.
	 */
	private int getBenoetigteFelderAnzahl() {
		int felderAnzahl = 0;
		for(Schlangenart schlangenart : schlangenjagdModell.getSchlangenarten()) {
			felderAnzahl += schlangenart.getZeichenkette().length() * schlangenart.getAnzahl();
		}
		return felderAnzahl;
	}
}
