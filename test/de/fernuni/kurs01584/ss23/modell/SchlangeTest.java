package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SchlangeTest {

	@Test
	void testeKonstruktor() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		assertEquals(4, schlange.getLaenge(), "Die Laenge " + schlange.getLaenge() + " der Schlange entspricht nicht dem"
				+ "vorgegebenen Wert 4.");
		assertEquals(schlangenart, schlange.getSchlangenart(), "Die Schlangenart " + schlange.getSchlangenart() + " der Schlange "
				+ "entspricht nicht der vorgegebenen Schlangenart " + schlangenart + ".");
	}
	
	@Test
	void testeGetLaenge() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		assertEquals(4, schlange.getLaenge(), "Die Laenge " + schlange.getLaenge() + " der Schlange entspricht nicht dem"
				+ "vorgegebenen Wert 4.");
	}
	
	@Test
	void testeGetSchlangenart() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		assertEquals(schlangenart, schlange.getSchlangenart(), "Die Schlangenart " + schlange.getSchlangenart() + " der Schlange "
				+ "entspricht nicht der vorgegebenen Schlangenart " + schlangenart + ".");
	}
	
	@Test
	void testeGetSchlangenglieder() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld1 = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied1 = new Schlangenglied(0, feld1, schlange);
		schlange.addSchlangenglied(schlangenglied1);
		Feld feld2 = new Feld("F0", "b", 0, 1);
		Schlangenglied schlangenglied2 = new Schlangenglied(1, feld2, schlange);
		schlange.addSchlangenglied(schlangenglied2);
		List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
		
		assertEquals(schlangenglied1, schlangenglieder.get(0), "Das Schlangenglied " + schlangenglieder.get(0)
					+ " entspricht nicht dem vorgegebenen Schlangenglied " + schlangenglied1 + ".");
		assertEquals(schlangenglied2, schlangenglieder.get(1), "Das Schlangenglied " + schlangenglieder.get(1)
					+ " entspricht nicht dem vorgegebenen Schlangenglied " + schlangenglied2 + ".");
		
	}
	
	@Test
	void testeGetLetztesSchlangenglied() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld1 = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied1 = new Schlangenglied(0, feld1, schlange);
		schlange.addSchlangenglied(schlangenglied1);
		Feld feld2 = new Feld("F0", "b", 0, 1);
		Schlangenglied schlangenglied2 = new Schlangenglied(1, feld2, schlange);
		schlange.addSchlangenglied(schlangenglied2);
		
		assertEquals(schlangenglied2, schlange.getLetztesSchlangengled(), "Das Schlangenglied " + schlange.getLetztesSchlangengled()
					+ " entspricht nicht dem vorgegebenen Schlangenglied " + schlangenglied2 + ".");
	}
	
	@Test
	void testeAddSchlangengliedFeld() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld = new Feld("F0", "a", 0, 0);
		schlange.addSchlangenglied(feld);
		
		assertEquals(feld, schlange.getLetztesSchlangengled().getFeld(), "Das Feld " + schlange.getLetztesSchlangengled().getFeld()
					+ " entspricht nicht dem vorgegebenen Feld " + feld + ".");
	}
	
	@Test
	void testeAddSchlangenglied() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld = new Feld("F0", "a", 0, 0);
		Schlangenglied schlangenglied = new Schlangenglied(0, feld, schlange);
		schlange.addSchlangenglied(schlangenglied);
		
		assertEquals(schlangenglied, schlange.getLetztesSchlangengled(), "Das Schlangenglied " + schlange.getLetztesSchlangengled()
					+ " entspricht nicht dem vorgegebenen Schlangenglied " + schlangenglied + ".");
	}
	
	@Test
	void testeBelegtFeld() {
		Schlangenart schlangenart = new Schlangenart("A0", "abcd", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld1 = new Feld("F0", "a", 0, 0);
		Feld feld2 = new Feld("F1", "a", 0, 0);
		schlange.addSchlangenglied(feld1);
		
		assertTrue(schlange.belegtFeld(feld1), "BelegtFeld ist false, obwohl true sein sollte.");
		assertFalse(schlange.belegtFeld(feld2), "BelegtFeld ist true, obwohl false sein sollte.");
	}
	
	@Test
	void testeIsVollstaendig() {
		Schlangenart schlangenart = new Schlangenart("A0", "ab", 1, 1, 1);
		Schlange schlange = new Schlange(schlangenart);
		Feld feld1 = new Feld("F0", "a", 0, 0);
		schlange.addSchlangenglied(feld1);
		
		assertFalse(schlange.isVollstaendig(), "IsVollstaendig ist true, obwohl false sein sollte.");

		Feld feld2 = new Feld("F1", "a", 0, 0);
		schlange.addSchlangenglied(feld2);
		assertTrue(schlange.isVollstaendig(), "IsVollstaendig ist false, obwohl true sein sollte.");
	}

}
