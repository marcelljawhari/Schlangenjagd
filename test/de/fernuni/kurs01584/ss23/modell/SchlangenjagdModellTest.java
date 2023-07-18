package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

class SchlangenjagdModellTest {

	@Test
	void testeKonstruktorProbleminstanz() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, 1);
		assertNotNull(schlangenjagdModell.getDschungel(), "Der Dschungel wurde nicht ins Modell geschrieben.");
		assertNotNull(schlangenjagdModell.getSchlangenarten(), "Die Schlangenarten wurden nicht ins Modell geschrieben.");
		assertNotEquals(0, schlangenjagdModell.getVorgabeZeit(), "Die Zeitvorgabe wurde nicht ins Modell geschrieben.");
		assertNull(schlangenjagdModell.getSchlangen(), "Die Schlangen wurden unerwarteterweise ins Modell geschrieben.");
		assertEquals(0, schlangenjagdModell.getAbgabeZeit(), "Die Abgabezeit wurde unerwarteterweise ins Modell geschrieben.");
	}
	
	@Test
	void testeKonstruktorLoesung() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		List<Schlange> schlangen = new ArrayList<Schlange>();
		schlangen.add(new Schlange(schlangenarten[0]));
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, 1, 1);
		assertNotNull(schlangenjagdModell.getDschungel(), "Der Dschungel wurde nicht ins Modell geschrieben.");
		assertNotNull(schlangenjagdModell.getSchlangenarten(), "Die Schlangenarten wurden nicht ins Modell geschrieben.");
		assertNotEquals(0, schlangenjagdModell.getVorgabeZeit(), "Die Zeitvorgabe wurde nicht ins Modell geschrieben.");
		assertNotNull(schlangenjagdModell.getSchlangen(), "Die Schlangen wurden unerwarteterweise ins Modell geschrieben.");
		assertNotEquals(0, schlangenjagdModell.getAbgabeZeit(), "Die Abgabezeit wurde unerwarteterweise ins Modell geschrieben.");
	}
	
	@Test
	void testeGetDschungel() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, 1);
		assertEquals(dschungel, schlangenjagdModell.getDschungel(), "Der Dschungel des Modells " + schlangenjagdModell.getDschungel()
					+ " entspricht nicht dem vorgegebenen Dschungel " + dschungel + ".");
	}
	
	@Test
	void testeGetSchlangenarten() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, 1);
		assertEquals(schlangenarten, schlangenjagdModell.getSchlangenarten(), "Die Schlangenarten des Modells " 
					+ schlangenjagdModell.getSchlangenarten() + " entspricht nicht den vorgegebenen Schlangenarten " + schlangenarten + ".");
	}
	
	@Test
	void testeGetVorgabeZeit() {
		int vorgabeZeit = 10;
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, vorgabeZeit);
		assertEquals(vorgabeZeit, schlangenjagdModell.getVorgabeZeit(), "Die Zeitvorgabe des Modells " + schlangenjagdModell.getVorgabeZeit()
					+ " entspricht nicht dem vorgegebenen Wert " + vorgabeZeit + ".");
	}
	
	@Test
	void testeGetAbgabeZeit() {
		int abgabeZeit = 10;
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		List<Schlange> schlangen = new ArrayList<Schlange>();
		schlangen.add(new Schlange(schlangenarten[0]));
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, 1, abgabeZeit);
		assertEquals(abgabeZeit, schlangenjagdModell.getAbgabeZeit(), "Die Abgabezeit des Modells " + schlangenjagdModell.getAbgabeZeit()
		+ " entspricht nicht dem vorgegebenen Wert " + abgabeZeit + ".");
	}
	
	@Test
	void testSetAbgabeZeit() {
		int abgabeZeit = 10;
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		List<Schlange> schlangen = new ArrayList<Schlange>();
		schlangen.add(new Schlange(schlangenarten[0]));
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, 1, 1);
		schlangenjagdModell.setAbgabeZeit(abgabeZeit);
		assertEquals(abgabeZeit, schlangenjagdModell.getAbgabeZeit(), "Die Abgabezeit des Modells " + schlangenjagdModell.getAbgabeZeit()
		+ " entspricht nicht dem vorgegebenen Wert " + abgabeZeit + ".");
	}
	
	@Test
	void testeGetSchlangen() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		List<Schlange> schlangen = new ArrayList<Schlange>();
		schlangen.add(new Schlange(schlangenarten[0]));
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen, 1, 1);
		assertEquals(schlangen, schlangenjagdModell.getSchlangen(), "Die Schlangen des Modells " + schlangenjagdModell.getSchlangen()
					+ " entsprechen nicht den vorgegebenen Schlangen " + schlangen + ".");
	}
	
	@Test
	void testeSetSchlangen() {
		Dschungel dschungel = SchlangenjagdFabrik.erzeugeZufaelligenDschungel("abc", 3, 3);
		Schlangenart[] schlangenarten = { new Schlangenart("A0", "ab", 1, 1, 1) };
		List<Schlange> schlangen1 = new ArrayList<Schlange>();
		schlangen1.add(new Schlange(schlangenarten[0]));
		SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, schlangenarten, schlangen1, 1, 1);
		List<Schlange> schlangen2 = new ArrayList<Schlange>();
		schlangen2.add(new Schlange(schlangenarten[0]));
		schlangenjagdModell.setSchlangen(schlangen2);
		assertEquals(schlangen2, schlangenjagdModell.getSchlangen(), "Die Schlangen des Modells " + schlangenjagdModell.getSchlangen()
					+ " entsprechen nicht den vorgegebenen Schlangen " + schlangen2 + ".");
	}

}
