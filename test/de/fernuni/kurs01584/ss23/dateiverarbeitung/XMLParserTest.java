package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;

class XMLParserTest {
	
	@Test
	void testeGetVorgabeZeit() {
		String file = "./res/sj_t1_probleminstanz.xml";
		long vorgabeZeit = 60000;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			assertEquals(vorgabeZeit, parser.parseVorgabeZeit(), () -> "Die gelesene Zeitvorgabe '" + parser.parseVorgabeZeit() 
			+ "' entspricht nicht dem vorgegebenen Wert '" + vorgabeZeit + "'.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}
	
	@Test
	void testeGetAbgabeZeit() {
		String file = "./res/sj_t1_loesung.xml";
		long abgabeZeit = 1;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			assertEquals(abgabeZeit, parser.parseAbgabeZeit(), () -> "Die gelesene Abgabezeit '" + parser.parseAbgabeZeit() 
			+ "' entspricht nicht dem vorgegebenen Wert '" + abgabeZeit + "'.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testeGetVollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_t1_probleminstanz.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Dschungel dschungel = parser.parseDschungel();
			Feld[][] felder = dschungel.getFelder();
			
			for (int zeile = 0; zeile < dschungel.getZeilen(); zeile++) {
				for (int spalte = 0; spalte < dschungel.getSpalten(); spalte++) {
					if (felder[zeile][spalte] == null) {
						fail("Das Feld in Zeile " + zeile + " und Spalte " + spalte + " ist null.");
					}
				}
			}
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testeGetUnvollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_t1_unvollstaendig.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Dschungel dschungel = parser.parseDschungel();
			Feld[][] felder = dschungel.getFelder();
			
			for (int zeile = 0; zeile < dschungel.getZeilen(); zeile++) {
				for (int spalte = 0; spalte < dschungel.getSpalten(); spalte++) {
					if (felder[zeile][spalte] == null) {
						fail("Das Feld in Zeile " + zeile + " und Spalte " + spalte + " ist null.");
					}
				}
			}
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testeGetLoesungSchlangenAnzahl() {
		String file = "./res/sj_t2_loesung.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			List <Schlange> schlangen = parser.parseSchlangen(schlangenarten, dschungel);
			int schlangenAnzahl = 6;
			assertEquals(schlangenAnzahl, schlangen.size(), "Es wurden " + schlangen.size() + " statt "
			+ schlangenAnzahl + " Schlangen gefunden.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
