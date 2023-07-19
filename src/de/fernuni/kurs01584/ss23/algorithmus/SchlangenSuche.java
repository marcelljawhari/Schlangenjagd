package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;
import java.util.ArrayList;

import de.fernuni.kurs01584.ss23.modell.*;

public class SchlangenSuche {
	private SchlangenjagdModell schlangenjagdModell;
	private Dschungel dschungel;
	private Schlangenart[] schlangenarten;
	private long vorgabeZeit, startZeit;
	private List<Schlange> schlangen;
	private int aktuellePunkte, maxPunkte;
	private int[][] verwendbarkeiten;
	private FeldSortierer feldSortierer;
	private SchlangenartenSortierer schlangenartenSortierer;
	
	/***
	 * Erzeugt eine SchlangenSuche fuer das angegebene SchlangenjagdModell.
	 * @param schlangenjagdModell SchlangenjagdModell worauf die SchlangenSuche operieren soll.
	 */
	public SchlangenSuche(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
		vorgabeZeit = schlangenjagdModell.getVorgabeZeit();
		dschungel = schlangenjagdModell.getDschungel();
		schlangenarten = schlangenjagdModell.getSchlangenarten();
		feldSortierer = new FeldSortierer();
		schlangenartenSortierer = new SchlangenartenSortierer();
	}
	
	/***
	 * Suche Schlangen bis eine optimale Loesung erreicht oder
	 * die Zeitvorgabe ueberschritten wurde.
	 */
	public void sucheSchlangen() {
		schlangen = new ArrayList<Schlange>();
		startZeit = System.currentTimeMillis();
		aktuellePunkte = 0; 
		maxPunkte = 0;
		verwendbarkeiten = dschungel.getVerwendbarkeiten();
		sucheSchlange();
		schlangenjagdModell.setAbgabeZeit(getVergangeneZeit());
	}
	
	/***
	 * Suche eine Schlange.
	 * Falls momentane Punkte hoeher als bisher maximale Punkte sind speichere die aktuelle Loesung.
	 * Falls die Zeitvorgabe erreicht wurde breche die Rekursion ab und beende die Schlangensuche.
	 * Andernfalls erzeuge und sortiere eine Liste der zulaessigen Startfelder.
	 * Erzeuge und Sortiere eine Liste der zulaessigen Schlangenarten.
	 * Generiere diesbezueglich eine neue Schlange und suche das naechste Schlangenglied.
	 * Bei Rekursionsabbruch entferne die Schlange.
	 */
	private void sucheSchlange() {
		if(aktuellePunkte > maxPunkte) {
			maxPunkte = aktuellePunkte;
			speichereLoesung();
		}
		if(vorgabeZeitErreicht()) {
			return;
		}
		List<Feld> startfelder = erzeugeStartfelder();
		feldSortierer.sortiereByPunkte(startfelder);
		for(Feld startfeld : startfelder) {
			List<Schlangenart> schlangenarten = erzeugeZulaessigeSchlangenartenFuerFeld(startfeld);
			schlangenartenSortierer.sortiereByPunkte(schlangenarten);
			for(Schlangenart schlangenart : schlangenarten) {
				Schlange schlange = new Schlange(schlangenart);
				Schlangenglied schlangenkopf = new Schlangenglied(0, startfeld, schlange);
				addSchlange(schlange, schlangenkopf, startfeld);
				sucheSchlangenglied(schlange, schlangenkopf);
				removeSchlange(schlange, startfeld);
			}
		}
	}
	
	/***
	 * Suche ein Schlangenglied.
	 * Falls momentane Schlange bereits vollstaendig ist, suche die naechste Schlange.
	 * Falls die Zeitvorgabe erreicht wurde breche die Rekursion ab und beende die Schlangensuche.
	 * Andernfalls erzeuge und sortiere eine Liste der zulaessigen Nachbarfelder.
	 * Generiere diesbezueglich ein neues Schlangenglied und suche das naechste Schlangenglied.
	 * Bei Rekursionsabbruch entferne das Schlangenglied.
	 * @param schlange Schlange wofuer ein neues Schlangenglied erzeugt werden soll.
	 * @param vorherigesSchlangenglied Vorheriges Schlangenglied in wessen Nachbarschaft das neue Schlangenglied sein soll.
	 */
	private void sucheSchlangenglied(Schlange schlange, Schlangenglied vorherigesSchlangenglied) {
		if(schlange.isVollstaendig()) {
			sucheSchlange();
			return;
		}
		if(vorgabeZeitErreicht()) {
			return;
		}
		int index = vorherigesSchlangenglied.getIndex() + 1;
		List<Feld> nachbarfelder = erzeugeNachbarfelder(vorherigesSchlangenglied);
		feldSortierer.sortiereByPunkte(nachbarfelder);
		for(Feld nachbarfeld : nachbarfelder) {
			Schlangenglied schlangenglied = new Schlangenglied(index, nachbarfeld, schlange);
			addSchlangenglied(schlange, schlangenglied, nachbarfeld);
			sucheSchlangenglied(schlange, schlangenglied);
			removeSchlangenglied(schlange, schlangenglied, nachbarfeld);
		}		
	}
	
	/***
	 * Speichere die momentanen Schlangen im SchlangenjagdModell ab.
	 */
	private void speichereLoesung() {
		List<Schlange> loesung = new ArrayList<Schlange>();
		for(Schlange schlange : schlangen) {
			Schlange neueSchlange = new Schlange(schlange.getSchlangenart());
			List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
			for(int i = 0; i < schlangenglieder.size(); i++) {
				Schlangenglied schlangenglied = schlangenglieder.get(i);
				Schlangenglied neuesSchlangenglied = new Schlangenglied(schlangenglied.getIndex(), schlangenglied.getFeld(), neueSchlange);
				neueSchlange.addSchlangenglied(neuesSchlangenglied);
			}
			loesung.add(neueSchlange);
		}
		schlangenjagdModell.setSchlangen(loesung);
	}
	
	/***
	 * Fuege der Loesung eine neue Schlange hinzu.
	 * @param schlange Schlange die hinzugefuegt werden soll.
	 * @param schlangenkopf Kopf der Schlange.
	 * @param startfeld Feld auf das der Schlangenkopf platziert wird.
	 */
	private void addSchlange(Schlange schlange, Schlangenglied schlangenkopf, Feld startfeld) {
		schlange.addSchlangenglied(schlangenkopf);
		schlangen.add(schlange);
		reduziereVerwendbarkeit(startfeld);
		aktuellePunkte += schlange.getSchlangenart().getPunkte() + startfeld.getPunkte();
	}
	
	/***
	 * Entferne Schlange aus der Loesung.
	 * @param schlange Schlange die entfernt werden soll.
	 * @param startfeld Feld welches durch den Schlangenkopf belegt war.
	 */
	private void removeSchlange(Schlange schlange, Feld startfeld) {
		schlangen.remove(schlange);
		erhoeheVerwendbarkeit(startfeld);
		aktuellePunkte -= schlange.getSchlangenart().getPunkte() + startfeld.getPunkte();
	}
	
	/***
	 * Fuege der Schlange ein neues Schlangenglied hinzu.
	 * @param schlange Schlange der das Schlangenglied hinzugefuegt wird.
	 * @param schlangenglied Schlangenglied das hinzugefuegt wird.
	 * @param feld Feld welches durch das neue Schlangenglied belegt wird.
	 */
	private void addSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.addSchlangenglied(schlangenglied);
		reduziereVerwendbarkeit(feld);
		aktuellePunkte += feld.getPunkte();
	}
	
	/***
	 * Entfernt ein Schlangenglied von einer Schlange.
	 * @param schlange Schlange der das Schlangenglied entfernt wird.
	 * @param schlangenglied Schlangenglied das entfernt wird.
	 * @param feld Feld welches durch das Schlangenglied belegt war.
	 */
	private void removeSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.removeSchlangenglied();
		erhoeheVerwendbarkeit(feld);
		aktuellePunkte -= feld.getPunkte();
	}
	
	/***
	 * Erhoehe die Verwendbarkeit im Verwendbarkeit-Array fuer das entsprechende Feld um 1.
	 * @param feld Feld dessen Verwendbarkeit erhoeht wird.
	 */
	private void erhoeheVerwendbarkeit(Feld feld) {
		verwendbarkeiten[feld.getZeile()][feld.getSpalte()]++;
	}

	/***
	 * Reduziere die Verwendbarkeit im Verwendbarkeit-Array fuer das entsprechende Feld um 1.
	 * @param feld Feld dessen Verwendbarkeit reduziert wird.
	 */
	private void reduziereVerwendbarkeit(Feld feld) {
		verwendbarkeiten[feld.getZeile()][feld.getSpalte()]--;
	}
	
	/***
	 * Erzeuge eine Liste aller zulaessigen Schlangenarten fuer ein bestimmtes Feld.
	 * @param feld Feld fuer das die Schlangenarten gesucht werden.
	 * @return Liste aller Schlangenarten die auf dem Feld platziert werden koennen.
	 */
	private List<Schlangenart> erzeugeZulaessigeSchlangenartenFuerFeld(Feld feld) {
		List<Schlangenart> zulaessigeSchlangenarten = new ArrayList<Schlangenart>();
		for(Schlangenart schlangenart : schlangenarten) {
			if(feld.getZeichen().equals("" + schlangenart.getZeichenkette().charAt(0))) {
				zulaessigeSchlangenarten.add(schlangenart);
			}
		}
		return zulaessigeSchlangenarten;
	}
	
	/***
	 * Erzeuge eine Liste aller Startfelder, deren Verwendbarkeit noch nicht ueberschritten wurde.
	 * @return Liste aller zulaessigen Startfelder.
	 */
	private List<Feld> erzeugeStartfelder() {
		List<Feld> startfelder = new ArrayList<Feld>();
		String zeichen = getEinzigartigeSchlangenartenAnfangszeichen();
		for(int i = 0; i < zeichen.length(); i++) {
			for(Feld feld : dschungel.getFelderByZeichen("" + zeichen.charAt(i))) {
				if(verwendbarkeiten[feld.getZeile()][feld.getSpalte()] > 0) {
					startfelder.add(feld);
				}
			}
		}
		return startfelder;
	}
	
	/***
	 * Holt aus jeder Schlangenart das Anfangszeichen und generiert daraus einen gemeinsamen String ohne Duplikate.
	 * @return String mit Anfangsbuchstaben jeder Schlangenart ohne Duplikate.
	 */
	private String getEinzigartigeSchlangenartenAnfangszeichen() {
		String zeichen = "";
		for(Schlangenart schlangenart : schlangenarten) {
			String anfangszeichen = "" + schlangenart.getZeichenkette().charAt(0);
			if(!zeichen.contains(anfangszeichen)) {
				zeichen += anfangszeichen;
			}
		}
		return zeichen;
	}
	
	
	/***
	 * Erzeuge eine Liste aller Nachbarfelder eines Feldes (des gegebenen Schlangenglieds)
	 * deren Verwendbarkeit noch nicht ueberschritten wurde.
	 * @param vorherigesSchlangenglied Schlangenglied dessen Nachbarn gesucht werden.
	 * @return Liste aller zulaessigen Nachbarfelder.
	 */
	private List<Feld> erzeugeNachbarfelder(Schlangenglied vorherigesSchlangenglied) {
		List<Feld> nachbarfelder = new ArrayList<Feld>();
		Feld vorherigesFeld = vorherigesSchlangenglied.getFeld();
		int vorherigeZeile = vorherigesFeld.getZeile();
		int vorherigeSpalte = vorherigesFeld.getSpalte();
		Schlange schlange = vorherigesSchlangenglied.getSchlange();
		Schlangenart schlangenart = schlange.getSchlangenart();
		Nachbarschaftsstruktur nachbarschaftsstruktur = schlangenart.getNachbarschaftsstruktur();
		String zeichen = "" + schlangenart.getZeichenkette().charAt(vorherigesSchlangenglied.getIndex()+1);
		int[][] deltas = nachbarschaftsstruktur.getDeltas();
		for(int[] delta : deltas) {
			int zeile = vorherigeZeile + delta[0];
			int spalte = vorherigeSpalte + delta[1];
			if(zeile >= 0 && zeile < dschungel.getZeilen() && spalte >= 0 && spalte < dschungel.getSpalten()) {
				Feld feld = dschungel.getFeld(zeile, spalte);
				if(verwendbarkeiten[zeile][spalte] > 0 && feld.getZeichen().equals(zeichen)) {
					nachbarfelder.add(feld);
				}
			}
		}
		return nachbarfelder;
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
}
