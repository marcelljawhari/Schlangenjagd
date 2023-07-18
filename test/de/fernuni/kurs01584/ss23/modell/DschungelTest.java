package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DschungelTest {
	
	@Test
	void testeKonstruktorKleinerGleichNullWerteSolltenExceptionAusloesen() {
		int zeilen = 0;
		int spalten = -4;
		assertThrows(IllegalArgumentException.class, () -> new Dschungel("A", zeilen, 1),
				"Fuer den Zeilenwert '" + zeilen + "' wird keine Ausnahme erzeugt");
		assertThrows(IllegalArgumentException.class, () -> new Dschungel("A", 1, spalten),
				"Fuer den (negativen) Spaltenwert '" + spalten + "' wird keine Ausnahme erzeugt");
	}

	@Test
	void testeGetZeilenPositiv() {
		int zeilen = 2;
		Dschungel dschungel = new Dschungel("a", zeilen, 1);
		assertEquals(zeilen, dschungel.getZeilen(), "Der Zeilenwert '" + dschungel.getZeilen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeilen + "'.");
	}
	
	@Test
	void testeGetSpaltenPositiv() {
		int spalten = 4;
		Dschungel dschungel = new Dschungel("a", 1, spalten);
		assertEquals(spalten, dschungel.getSpalten(), "Der Spaltenwert '" + dschungel.getSpalten() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + spalten + "'.");
	}
	
	@Test
	void testeGetZeichenmenge() {
		String zeichenmenge = "ab";
		Dschungel dschungel = new Dschungel(zeichenmenge, 1, 1);
		assertEquals(zeichenmenge, dschungel.getZeichenmenge(), "Die Zeichenmenge '" + dschungel.getZeichenmenge() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichenmenge + "'.");
	}
	
	@Test
	void testeGetFelder() {
		String zeichenmenge = "ab";
		int zeilen = 3;
		int spalten = 3;
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel(zeichenmenge, zeilen, spalten);
		assertEquals(zeilen, dschungel.getFelder().length, "Das Feld-Array hat " + dschungel.getFelder().length
				+ " Zeilen statt " + zeilen + ".");
		assertEquals(spalten, dschungel.getFelder()[0].length, "Das Feld-Array hat " + dschungel.getFelder()[0].length
				+ " Spalten statt " + spalten + ".");
	}
	
	@Test
	void testeGetFeld() {
		Feld feld = new Feld("F0", "a", 0, 0);
		Dschungel dschungel = new Dschungel("ab", 3, 3);
		dschungel.setFeld(feld);
		dschungel.befuelleRestlicheFelder();
		assertEquals(feld, dschungel.getFeld(feld.getZeile(), feld.getSpalte()), "Das Feld "
				+ "an der Position 0 0 entspricht nicht dem vorgegebenen Feld.");
	}
	
	@Test
	void testeGetFeldById() {
		String id = "F0";
		Feld feld = new Feld(id, "a", 0, 0);
		Dschungel dschungel = new Dschungel("ab", 3, 3);
		dschungel.setFeld(feld);
		dschungel.befuelleRestlicheFelder();
		assertEquals(feld, dschungel.getFeldById(id), "Das Feld " + dschungel.getFeldById(id)
				+ " entspricht nicht dem vorgegebenen Feld " + feld + ".");
	}
	
	@Test
	void testeGetFelderByZeichen() {
		Dschungel dschungel = new Dschungel("abcdefg", 3, 3);
		String zeichen = "a";
		dschungel.setFeld(zeichen, 0, 0);
		dschungel.setFeld(zeichen, 1, 1);
		dschungel.setFeld(zeichen, 2, 0);
		dschungel.befuelleRestlicheFelder();
		assertTrue((dschungel.getFelderByZeichen(zeichen).size() >= 3), "Statt 3 Feldern wurden nur "
				+ dschungel.getFelderByZeichen(zeichen).size() + " Felder gefunden.");
	}
	
	@Test
	void testeGetVerwendbarkeiten() {
		int[][] verwendbarkeiten = { {1,2,3}, {3,2,1}, {4,5,6} };
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeDschungelBasierendAufVerwendbarkeiten("abcd", verwendbarkeiten);
		int[][] dschungelVerwendbarkeiten = dschungel.getVerwendbarkeiten();
		for(int zeile = 0; zeile < verwendbarkeiten.length; zeile++) {
			for(int spalte = 0; spalte < verwendbarkeiten[0].length; spalte++) {
				int verwendbarkeit = verwendbarkeiten[zeile][spalte];
				int dschungelVerwendbarkeit = dschungelVerwendbarkeiten[zeile][spalte];
				assertEquals(verwendbarkeit, dschungelVerwendbarkeit, 
						"Die zurueckgegebene Verwendbarkeit " + dschungelVerwendbarkeit + " entspricht nicht "
						+ "dem vorgegebenen Wert " + verwendbarkeit);
			}
		}
	}
	
	@Test
	void testeSetFeld() {
		Dschungel dschungel = new Dschungel("ab", 1, 1);
		dschungel.setFeld("F0", "a", 0, 0, 1, 1);
		assertNotNull(dschungel.getFeld(0, 0), "Das eben platzierte Feld an Position 0 0 ist null.");
	}
	
	@Test
	void testeSetFeldStandardFeld() {
		Dschungel dschungel = new Dschungel("ab", 1, 1);
		dschungel.setFeld("a", 0, 0);
		Feld feld = dschungel.getFeld(0, 0);
		assertEquals(1, feld.getPunkte(), "Der Punktwert des platzierten Feldes " + feld.getPunkte()
				+ " entspricht nicht dem Standardwert 1.");
		assertEquals(1, feld.getVerwendbarkeit(), "Die Verwendbarkeit des platzierten Feldes " + feld.getVerwendbarkeit()
		+ " entspricht nicht dem Standardwert 1.");
	}
	
	@Test
	void testeSetFeldLeeresFeld() {
		Dschungel dschungel = new Dschungel("ab", 1, 1);
		dschungel.setFeld(0, 0);
		Feld feld = dschungel.getFeld(0, 0);
		assertEquals(0, feld.getPunkte(), "Der Punktwert des platzierten leeren Feldes " + feld.getPunkte()
				+ " entspricht nicht dem Standardwert 0.");
		assertEquals(0, feld.getVerwendbarkeit(), "Die Verwendbarkeit des platzierten leeren Feldes " + feld.getVerwendbarkeit()
		+ " entspricht nicht dem Standardwert 0.");
	}
	
	@Test
	void testeSetFeldVorhandenesFeld() {
		Dschungel dschungel = new Dschungel("ab", 1, 1);
		Feld feld = new Feld("F0", "a", 0, 0);
		dschungel.setFeld(feld);
		assertEquals(feld, dschungel.getFeld(0, 0), "Das eben platzierte Feld " + dschungel.getFeld(0, 0) 
			+ " entspricht nicht dem vorgegebenen Feld " + feld + ".");
	}
	
	@Test
	void testeLoescheFelder() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("ab", 3, 3);
		dschungel.loescheFelder();
		for(Feld[] zeile : dschungel.getFelder()) {
			for(Feld feld : zeile) {
				assertNull(feld, "Das feld " + feld + " ist nicht null.");
			}
		}
	}
	
	@Test
	void testeBefuelleLeereFelder() {
		String zeichen = "a";
		int zeilen = 3;
		int spalten = 3;
		Dschungel dschungel = new Dschungel("ab", zeilen, spalten);
		dschungel.setFeld(zeichen, 0, 0);
		dschungel.setFeld(zeichen, 0, 1);
		dschungel.setFeld(zeichen, 0, 2);
		dschungel.befuelleLeereFelder();
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				Feld feld = dschungel.getFeld(zeile, spalte);
				if(zeile == 0) {
					assertEquals(zeichen, feld.getZeichen(), "Das Feld " + feld + " beinhaelt das Zeichen " 
							+ feld.getZeichen() + " statt " + zeichen + ".");
				} else {
					assertEquals("", feld.getZeichen(), "Das Feld " + feld + " ist nicht leer.");
					assertEquals(0, feld.getPunkte(), "Das Feld " + feld + " ist nicht leer.");
					assertEquals(0, feld.getVerwendbarkeit(), "Das Feld " + feld + " ist nicht leer.");
				}
			}
		}
	}
	
	@Test
	void testeBefuelleRestlicheFelder() {
		String zeichen = "a";
		int zeilen = 3;
		int spalten = 3;
		Dschungel dschungel = new Dschungel("ab", zeilen, spalten);
		dschungel.setFeld("F0", zeichen, 0, 0, 2, 2);
		dschungel.setFeld("F1", zeichen, 0, 1, 2, 2);
		dschungel.setFeld("F2", zeichen, 0, 2, 2, 2);
		dschungel.befuelleRestlicheFelder();
		for(int zeile = 0; zeile < zeilen; zeile++) {
			for(int spalte = 0; spalte < spalten; spalte++) {
				Feld feld = dschungel.getFeld(zeile, spalte);
				if(zeile == 0) {
					assertEquals(2, feld.getPunkte(), "Das Feld " + feld + " hat den Punktwert " 
							+ feld.getPunkte() + " statt 2.");
				} else {
					assertEquals(1, feld.getPunkte(), "Das Feld " + feld + " hat den Punktwert " 
							+ feld.getPunkte() + " statt 1.");
					assertEquals(1, feld.getVerwendbarkeit(), "Das Feld " + feld + " hat die Verwendbarkeit " 
							+ feld.getVerwendbarkeit() + " statt 1.");
				}
			}
		}
	}
}
