package de.fernuni.kurs01584.ss23.modell;

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
	
}
