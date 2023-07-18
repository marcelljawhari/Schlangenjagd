package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SchlangenartTest {

	@Test
	void testeKonstruktorDistanz() {
		Schlangenart schlangenart = new Schlangenart("A0", "ab", 1, 1, 1);
		assertEquals("Distanz", schlangenart.getNachbarschaftsstruktur().getTyp(), "Die Schlangenart hat die Falsche Nachbarschaftsstruktur.");
	}
	
	@Test
	void testeKonstruktorSprung() {
		Schlangenart schlangenart = new Schlangenart("A0", "ab", 1, 1, 1, 1);
		assertEquals("Sprung", schlangenart.getNachbarschaftsstruktur().getTyp(), "Die Schlangenart hat die Falsche Nachbarschaftsstruktur.");
	}
	
	@Test
	void testeGetPunkte() {
		int punkte = 3;
		Schlangenart schlangenart = new Schlangenart("A0", "ab", punkte, 1, 1, 1);
		assertEquals(punkte, schlangenart.getPunkte(), "Der Punktwert der Schlangenart " + schlangenart.getPunkte()
					+ " entspricht nicht dem vorgegebenen Wert " + punkte + ".");
	}
	
	@Test
	void testeGetAnzahl() {
		int anzahl = 2;
		Schlangenart schlangenart = new Schlangenart("A0", "ab", 1, anzahl, 1, 1);
		assertEquals(anzahl, schlangenart.getAnzahl(), "Die Anzahl der Schlangenart " + schlangenart.getAnzahl()
					+ " entspricht nicht dem vorgegebenen Wert " + anzahl + ".");
	}
	
	@Test
	void testeGetId() {
		String id = "A1";
		Schlangenart schlangenart = new Schlangenart(id, "ab", 1, 1, 1, 1);
		assertEquals(id, schlangenart.getId(), "Die ID der Schlangenart " + schlangenart.getId()
					+ " entspricht nicht dem vorgegebenen Wert " + id + ".");
	}
	
	@Test
	void testeGetZeichenkette() {
		String zeichenkette = "abcd";
		Schlangenart schlangenart = new Schlangenart("A0", zeichenkette, 1, 1, 1, 1);
		assertEquals(zeichenkette, schlangenart.getZeichenkette(), "Die Zeichenkette der Schlangenart " + schlangenart.getZeichenkette()
					+ " entspricht nicht dem vorgegebenen Wert " + zeichenkette + ".");
	}
	
	@Test
	void testeGetNachbarschaftsstruktur() {
		Schlangenart distanzSchlangenart = new Schlangenart("A0", "abc", 1, 1, 1);
		Schlangenart sprungSchlangenart = new Schlangenart("A1", "abc", 1, 1, 1, 1);
		assertEquals("Distanz", distanzSchlangenart.getNachbarschaftsstruktur().getTyp(), 
				"Die Schlangenart hat die Falsche Nachbarschaftsstruktur.");
		assertEquals("Sprung", sprungSchlangenart.getNachbarschaftsstruktur().getTyp(), 
				"Die Schlangenart hat die Falsche Nachbarschaftsstruktur.");
	}
	
}
