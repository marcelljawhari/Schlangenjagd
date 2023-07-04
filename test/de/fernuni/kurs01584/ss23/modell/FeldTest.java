package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class FeldTest {

	@Test
	void testeGetZeichenA() {
		String id = "F0";
		char zeichen = 'a';
		Feld feld = new Feld(id, zeichen, 0, 0, 0, 0);
		assertEquals(feld.getZeichen(), zeichen, () -> "Der Zeilenwert '" + feld.getZeichen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichen + "'.");
	}
	
	@Test
	void testeGetZeichenEmoji() {
		String id = "F0";
		char zeichen = '⛹';
		Feld feld = new Feld(id, zeichen, 0, 0, 0, 0);
		assertEquals(feld.getZeichen(), zeichen, () -> "Der Zeilenwert '" + feld.getZeichen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichen + "'.");
	}
	
	@Test
	void testeGetZeilePositiv() {
		String id = "F0";
		int zeile = 2;
		Feld feld = new Feld(id, 'a', zeile, 0, 0, 0);
		assertEquals(feld.getZeile(), zeile, () -> "Der Zeilenwert '" + feld.getZeile() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeile + "'.");
	}
	
	@Test
	void testeGetSpaltePositiv() {
		String id = "F0";
		int spalte = 4;
		Feld feld = new Feld(id, 'a', 0, spalte, 0, 0);
		assertEquals(feld.getSpalte(), spalte, () -> "Der Spaltenwert '" + feld.getSpalte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + spalte + "'.");
	}
	
	@Test
	void testeGetPunktePositiv() {
		String id = "F0";
		int punkte = 6;
		Feld feld = new Feld(id, 'a', 0, 0, punkte, 0);
		assertEquals(feld.getPunkte(), punkte, () -> "Der Punktwert '" + feld.getPunkte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + punkte + "'.");
	}
	
	@Test
	void testeGetVerwendbarkeitPositiv() {
		String id = "F0";
		int verwendbarkeit = 8;
		Feld feld = new Feld(id, 'a', 0, 0, 0, verwendbarkeit);
		assertEquals(feld.getVerwendbarkeit(), verwendbarkeit, () -> "Die Verwendbarkeit '" + feld.getVerwendbarkeit() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + verwendbarkeit + "'.");
	}
	
	@Test
	void testeKonstruktorNegativeWerteSolltenExceptionAusloesen() {
		String id = "F0";
		int zeile = -1;
		int spalte = -2;
		int punkte = -3;
		int verwendbarkeit = -4;
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, 'a', zeile, 0, 0, 0),
				() -> "Fuer den (negativen) Zeilenwert '" + zeile + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, 'a', 0, spalte, 0, 0),
				() -> "Fuer den (negativen) Spaltenwert '" + spalte + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, 'a', 0, 0, punkte, 0),
				() -> "Fuer den (negativen) Punktwert '" + punkte + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, 'a', 0, 0, 0, verwendbarkeit),
				() -> "Fuer die (negative) Verwendbarkeit '" + verwendbarkeit + "' wird keine Ausnahme erzeugt.");
	}
	
}
