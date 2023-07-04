package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DschungelTest {

	@Test
	void testeGetZeilenPositiv() {
		int zeilen = 2;
		Dschungel dschungel = new Dschungel("a", zeilen, 1);
		assertEquals(dschungel.getZeilen(), zeilen, () -> "Der Zeilenwert '" + dschungel.getZeilen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeilen + "'.");
	}
	
	@Test
	void testeGetSpaltenPositiv() {
		int spalten = 4;
		Dschungel dschungel = new Dschungel("a", 1, spalten);
		assertEquals(dschungel.getSpalten(), spalten, () -> "Der Spaltenwert '" + dschungel.getSpalten() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + spalten + "'.");
	}
	
	@Test
	void testeGetZeichenmengeAB() {
		String zeichenmenge = "AB";
		Dschungel dschungel = new Dschungel(zeichenmenge, 1, 1);
		assertEquals(dschungel.getZeichenmenge(), zeichenmenge, () -> "Die Zeichenmenge '" + dschungel.getZeichenmenge() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichenmenge + "'.");
	}
	
	@Test
	void testeGetFeld() {
		Feld feld = new Feld("F0", 'a', 0, 0);
		Dschungel dschungel = new Dschungel("a", 1, 1);
		dschungel.setFeld(feld);
		assertEquals(dschungel.getFeld(feld.getZeile(), feld.getSpalte()), feld, () -> "Das Feld "
				+ "an der Position 0 0 entspricht nicht dem vorgegebenen Feld.");
	}
	
	@Test
	void testeKonstruktorKleinerGleichNullWerteSolltenExceptionAusloesen() {
		int zeilen = 0;
		int spalten = -4;
		assertThrows(IllegalArgumentException.class, () -> new Dschungel("A", zeilen, 1),
				() -> "Fuer den Zeilenwert '" + zeilen + "' wird keine Ausnahme erzeugt");
		assertThrows(IllegalArgumentException.class, () -> new Dschungel("A", 1, spalten),
				() -> "Fuer den (negativen) Spaltenwert '" + spalten + "' wird keine Ausnahme erzeugt");
	}

}
