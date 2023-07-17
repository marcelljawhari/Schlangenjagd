package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class FeldTest {
	
	@Test
	void testeKonstruktorNegativeWerteSolltenExceptionAusloesen() {
		String id = "F0";
		String zeichen = "a";
		int negativerWert = -1;
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, zeichen, negativerWert, 0, 0, 0),
				() -> "Fuer den (negativen) Zeilenwert '" + negativerWert + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, zeichen, 0, negativerWert, 0, 0),
				() -> "Fuer den (negativen) Spaltenwert '" + negativerWert + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, zeichen, 0, 0, negativerWert, 0),
				() -> "Fuer den (negativen) Punktwert '" + negativerWert + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld(id, zeichen, 0, 0, 0, negativerWert),
				() -> "Fuer die (negative) Verwendbarkeit '" + negativerWert + "' wird keine Ausnahme erzeugt.");
	}
	
	@Test
	void testeKonstruktorStandardFeld() {
		String id = "F0";
		String zeichen = "A";
		int zeile = 0;
		int spalte = 0;
		
		Feld feld = new Feld(id, zeichen, zeile, spalte);
		assertEquals(feld.getPunkte(), 1, () -> "Der Punktewert '" + feld.getPunkte() 
		+ "' entspricht nicht dem Standardwert '1'.");
		assertEquals(feld.getVerwendbarkeit(), 1, () -> "Die Verwendbarkeit '" + feld.getVerwendbarkeit() 
		+ "' entspricht nicht dem Standardwert '1'.");
	}
	
	@Test
	void testeKonstruktorLeeresFeld() {
		String id = "F0";
		int zeile = 0;
		int spalte = 0;
		
		Feld feld = new Feld(id, zeile, spalte);
		assertEquals(feld.getPunkte(), 0, () -> "Der Punktewert '" + feld.getPunkte() 
		+ "' entspricht nicht dem Standardwert '0'.");
		assertEquals(feld.getVerwendbarkeit(), 0, () -> "Die Verwendbarkeit '" + feld.getVerwendbarkeit() 
		+ "' entspricht nicht dem Standardwert '0'.");
	}
	
	@Test
	void testeGetZeilePositiv() {
		String id = "F0";
		String zeichen = "a";
		int zeile = 2;
		Feld feld = new Feld(id, zeichen, zeile, 0, 0, 0);
		assertEquals(feld.getZeile(), zeile, () -> "Der Zeilenwert '" + feld.getZeile() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeile + "'.");
	}
	
	@Test
	void testeGetSpaltePositiv() {
		String id = "F0";
		String zeichen = "a";
		int spalte = 4;
		Feld feld = new Feld(id, zeichen, 0, spalte, 0, 0);
		assertEquals(feld.getSpalte(), spalte, () -> "Der Spaltenwert '" + feld.getSpalte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + spalte + "'.");
	}
	
	@Test
	void testeGetPunktePositiv() {
		String id = "F0";
		String zeichen = "a";
		int punkte = 6;
		Feld feld = new Feld(id, zeichen, 0, 0, punkte, 0);
		assertEquals(feld.getPunkte(), punkte, () -> "Der Punktwert '" + feld.getPunkte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + punkte + "'.");
	}
	
	@Test
	void testeGetVerwendbarkeitPositiv() {
		String id = "F0";
		String zeichen = "a";
		int verwendbarkeit = 8;
		Feld feld = new Feld(id, zeichen, 0, 0, 0, verwendbarkeit);
		assertEquals(feld.getVerwendbarkeit(), verwendbarkeit, () -> "Die Verwendbarkeit '" + feld.getVerwendbarkeit() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + verwendbarkeit + "'.");
	}

	@Test
	void testeGetZeichenA() {
		String id = "F0";
		String zeichen = "A";
		Feld feld = new Feld(id, zeichen, 0, 0, 0, 0);
		assertEquals(feld.getZeichen(), zeichen, () -> "Der Zeilenwert '" + feld.getZeichen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichen + "'.");
	}
	
	@Test
	void testeGetZeichenEmoji() {
		String id = "F0";
		String zeichen = "⛹";
		Feld feld = new Feld(id, zeichen, 0, 0, 0, 0);
		assertEquals(feld.getZeichen(), zeichen, () -> "Der Zeilenwert '" + feld.getZeichen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichen + "'.");
	}
	
	@Test
	void testeGetId() {
		String id = "F0";
		Feld feld = new Feld(id, "a", 0, 0, 0, 0);
		assertEquals(feld.getId(), id, () -> "Der Zeilenwert '" + feld.getId() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + id + "'.");
	}
	
}
