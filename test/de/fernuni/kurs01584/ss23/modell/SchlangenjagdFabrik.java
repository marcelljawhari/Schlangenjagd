package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class SchlangenjagdFabrik {
	
	public static Dschungel erzeugeZufaelligenDschungel(String zeichenkette, int zeilen, int spalten) {
		Dschungel dschungel = new Dschungel(zeichenkette, zeilen, spalten);
		dschungel.befuelleRestlicheFelder();
		return dschungel;
	}
	
	public static Dschungel erzeugeDschungelBasierendAufVerwendbarkeiten(String zeichenmenge, int[][] verwendbarkeiten) {
		int zeilen = verwendbarkeiten.length;
		int spalten = verwendbarkeiten[0].length;
		Dschungel dschungel = new Dschungel(zeichenmenge, zeilen, spalten);
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				String zeichen = "" + zeichenmenge.charAt((int) (Math.random() * zeichenmenge.length()));
				dschungel.setFeld(zeichenmenge, zeichen, zeile, spalte, 1 , verwendbarkeiten[zeile][spalte]);
			}
		}
		return dschungel;
	}
	
	public static SchlangenjagdModell erzeugeLoesung(String zeichenmenge, String[][] feldZeichen, 
			String[] schlangenartZeichenketten,String[][][] schlangenKodierungen , long vorgabeZeit, long abgabeZeit) {
		int zeilen = feldZeichen.length;
		int spalten = feldZeichen[0].length;
		Dschungel dschungel = new Dschungel(zeichenmenge, zeilen, spalten);
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				dschungel.setFeld(feldZeichen[zeile][spalte], zeile, spalte);
			}
		}
		
		Schlangenart[] schlangenarten = new Schlangenart[schlangenartZeichenketten.length];
		for(int i = 0; i < schlangenartZeichenketten.length; i++) {
			schlangenarten[i] = new Schlangenart("A" + i, schlangenartZeichenketten[i], 1, 1, 1);
		}
		
		List<Schlange> schlangen = new ArrayList<Schlange>();
		for(String[][] schlangenKodierung : schlangenKodierungen) {
			String schlangenartId = schlangenKodierung[0][0];
			Schlangenart schlangenartById = null;
			for(Schlangenart schlangenart : schlangenarten) {
				if(schlangenart.getId().equals(schlangenartId)) {
					schlangenartById = schlangenart;
				}
			}
			Schlange schlange = new Schlange(schlangenartById);
			for(String feldId : schlangenKodierung[1]) {
				schlange.addSchlangenglied(dschungel.getFeldById(feldId));
			}
			schlangen.add(schlange);
		}
		
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, vorgabeZeit, abgabeZeit);
		return schlangenjagdModell;
	}
	
}
