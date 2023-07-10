package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import de.fernuni.kurs01584.ss23.modell.*;

public class DschungelGenerator {
	private SchlangenjagdModell schlangenjagdModell;
	private Dschungel dschungel;
	private String zeichenmenge;
	private int zeilen, spalten;
	private Schlangenart[] schlangenarten;
	private List<Schlange> schlangen;
	private long vorgabeZeit, startZeit;
	private int schlangenAnzahl;
	private Feld[][] felder;
	
	public DschungelGenerator(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	public void generiereDschungel() throws IllegalArgumentException, TimeoutException {
		dschungel = schlangenjagdModell.getDschungel();
		zeichenmenge = dschungel.getZeichenmenge();
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
	
	private void platziereSchlangen() throws TimeoutException {
		schlangen = new ArrayList<Schlange>();
		dschungel.loescheFelder();
		platziereSchlange();
		speichereDschungel();
	}
	
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
	
	private Feld erzeugeFeld(String zeichen, int zeile, int spalte) {
		String feldId = "F" + (zeile * spalten + spalte);
		return new Feld(feldId, zeichen, zeile, spalte, 1, 1);
	}
	
	private void addSchlange(Schlange schlange, Schlangenglied schlangenkopf, Feld startfeld) {
		schlangen.add(schlange);
		schlange.addSchlangenglied(startfeld);
		felder[startfeld.getZeile()][startfeld.getSpalte()] = startfeld;
	}
	
	private void removeSchlange(Schlange schlange, Feld startfeld) {
		schlangen.remove(schlange);
		felder[startfeld.getZeile()][startfeld.getSpalte()] = null;
	}
	
	private void addSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.addSchlangenglied(schlangenglied);
		felder[feld.getZeile()][feld.getSpalte()] = feld;
	}
	
	private void removeSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.removeSchlangenglied();
		felder[feld.getZeile()][feld.getSpalte()] = null;
	}
	
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
		return verfuegbareKoordinaten;
	}
	
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
		return verfuegbareKoordinaten;
	}
	
	private void speichereDschungel() {
		System.out.println("Ausgabe felder:");
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld != null) {
					System.out.print(feld.getZeichen());
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld != null) {
					dschungel.setFeld(feld);
				}
			}
		}
	}
	
	private int getBenoetigteSchlangenAnzahl() {
		int anzahl = 0;
		for(Schlangenart schlangenart : schlangenarten) {
			anzahl += schlangenart.getAnzahl();
		}
		return anzahl;
	}
	
	private boolean vorgabeZeitErreicht() {
		return (getVergangeneZeit() >= vorgabeZeit);
	}
	
	private long getVergangeneZeit() {
		return (System.currentTimeMillis() - startZeit);
	}
	
	private int getBenoetigteFelderAnzahl() {
		int felderAnzahl = 0;
		for(Schlangenart schlangenart : schlangenjagdModell.getSchlangenarten()) {
			felderAnzahl += schlangenart.getZeichenkette().length() * schlangenart.getAnzahl();
		}
		return felderAnzahl;
	}
}
