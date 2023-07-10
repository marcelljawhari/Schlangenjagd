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
	
	public SchlangenSuche(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
		vorgabeZeit = schlangenjagdModell.getVorgabeZeit();
		dschungel = schlangenjagdModell.getDschungel();
		schlangenarten = schlangenjagdModell.getSchlangenarten();
		feldSortierer = new FeldSortierer();
		schlangenartenSortierer = new SchlangenartenSortierer();
	}
	
	public void sucheSchlangen() {
		schlangen = new ArrayList<Schlange>();
		startZeit = System.currentTimeMillis();
		aktuellePunkte = 0; 
		maxPunkte = 0;
		verwendbarkeiten = dschungel.getVerwendbarkeiten();
		sucheSchlange();
		schlangenjagdModell.setAbgabeZeit(getVergangeneZeit());
	}
	
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
	
	private void addSchlange(Schlange schlange, Schlangenglied schlangenkopf, Feld startfeld) {
		schlange.addSchlangenglied(schlangenkopf);
		schlangen.add(schlange);
		reduziereVerwendbarkeit(startfeld);
		aktuellePunkte += schlange.getSchlangenart().getPunkte() + startfeld.getPunkte();
	}
	
	private void removeSchlange(Schlange schlange, Feld startfeld) {
		schlangen.remove(schlange);
		erhoeheVerwendbarkeit(startfeld);
		aktuellePunkte -= schlange.getSchlangenart().getPunkte() + startfeld.getPunkte();
	}
	
	private void addSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.addSchlangenglied(schlangenglied);
		reduziereVerwendbarkeit(feld);
		aktuellePunkte += feld.getPunkte();
	}
	
	private void removeSchlangenglied(Schlange schlange, Schlangenglied schlangenglied, Feld feld) {
		schlange.removeSchlangenglied();
		erhoeheVerwendbarkeit(feld);
		aktuellePunkte -= feld.getPunkte();
	}
	
	private void erhoeheVerwendbarkeit(Feld feld) {
		verwendbarkeiten[feld.getZeile()][feld.getSpalte()]++;
	}
	
	private void reduziereVerwendbarkeit(Feld feld) {
		verwendbarkeiten[feld.getZeile()][feld.getSpalte()]--;
	}
	
	private List<Schlangenart> erzeugeZulaessigeSchlangenartenFuerFeld(Feld feld) {
		List<Schlangenart> zulaessigeSchlangenarten = new ArrayList<Schlangenart>();
		for(Schlangenart schlangenart : schlangenarten) {
			if(feld.getZeichen().equals("" + schlangenart.getZeichenkette().charAt(0))) {
				zulaessigeSchlangenarten.add(schlangenart);
			}
		}
		return zulaessigeSchlangenarten;
	}
	
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
	
	private boolean vorgabeZeitErreicht() {
		return (getVergangeneZeit() >= vorgabeZeit);
	}
	
	private long getVergangeneZeit() {
		return (System.currentTimeMillis() - startZeit);
	}
	
	public void printLoesung() {
		LoesungsBewerter bewerter = new LoesungsBewerter();
		System.out.println("Loesung:");
		System.out.println("Punkte: " + bewerter.bewerte(schlangenjagdModell.getSchlangen()));
		System.out.println("Abgabe: " + schlangenjagdModell.getAbgabeZeit() + "ms");
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		for(Schlange schlange : schlangen) {
			System.out.println("Schlange: '" + schlange.getSchlangenart().getZeichenkette() + "'");
		}
	}
}
