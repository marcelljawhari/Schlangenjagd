package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SchlangengliedTest {

	@Test
	void testeKonstruktor() {
		Schlange schlange = new Schlange(new Schlangenart("A0", "ab", 1, 1, 1));
		Feld feld = new Feld("F0", "a", 0, 0);
		assertDoesNotThrow(() -> new Schlangenglied(0, feld, schlange), "Unerwartete Exception.");
	}
	
	@Test
	void testeGetIndex() {
		int index = 0;
		Schlange schlange = new Schlange(new Schlangenart("A0", "ab", 1, 1, 1));
		Feld feld = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied = new Schlangenglied(index, feld, schlange);
		assertEquals(index, schlangenglied.getIndex(), "Das Schlangenglied hat den Index " + schlangenglied.getIndex()
					+ "statt " + index + ".");
	}

	@Test
	void testeGetFeld() {
		Schlange schlange = new Schlange(new Schlangenart("A0", "ab", 1, 1, 1));
		Feld feld = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied = new Schlangenglied(0, feld, schlange);
		assertEquals(feld, schlangenglied.getFeld(), "Das Schlangenglied belegt das Feld " + schlangenglied.getFeld()
					+ "statt " + feld + ".");
	}

	@Test
	void testeGetSchlange() {
		Schlange schlange = new Schlange(new Schlangenart("A0", "ab", 1, 1, 1));
		Feld feld = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied = new Schlangenglied(0, feld, schlange);
		assertEquals(schlange, schlangenglied.getSchlange(), "Das Schlangenglied wurde faelschlicherweise der Schlange " 
					+ schlangenglied.getSchlange() + "statt der Schlange " + schlange + " zugeordnet.");
	}
	
}
