package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.XMLParser;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class LoesungsPrueferTest {

	@Test
	void testePruefeGlieder() {
		String file = "./res/sj_p11_loesung_fehler.xml";
		int fehlerAnzahl = 2;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, 
					schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
			int gefundeneFehler = loesungsPruefer.pruefeGlieder();
			assertEquals(fehlerAnzahl, gefundeneFehler, 
					() -> "Die gefundene Fehleranzahl " + gefundeneFehler + " entspricht nicht der Vorgabe.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testePruefeVerwendung() {
		String file = "./res/sj_p12_loesung_fehler.xml";
		int fehlerAnzahl = 1;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, 
					schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
			int gefundeneFehler = loesungsPruefer.pruefeVerwendung();
			assertEquals(fehlerAnzahl, gefundeneFehler, 
					() -> "Die gefundene Fehleranzahl " + gefundeneFehler + " entspricht nicht der Vorgabe.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testePruefeNachbarschaft() {
		String file = "./res/sj_p13_loesung_fehler.xml";
		int fehlerAnzahl = 1;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, 
					schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
			int gefundeneFehler = loesungsPruefer.pruefeNachbarschaft();
			assertEquals(fehlerAnzahl, gefundeneFehler, 
					() -> "Die gefundene Fehleranzahl " + gefundeneFehler + " entspricht nicht der Vorgabe.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testePruefeZuordnung() {
		String file = "./res/sj_p14_loesung_fehler.xml";
		int fehlerAnzahl = 1;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel, 
					schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
			int gefundeneFehler = loesungsPruefer.pruefeZuordnung();
			assertEquals(fehlerAnzahl, gefundeneFehler, 
					() -> "Die gefundene Fehleranzahl " + gefundeneFehler + " entspricht nicht der Vorgabe.");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
