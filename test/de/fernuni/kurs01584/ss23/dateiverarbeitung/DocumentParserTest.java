package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DocumentParserTest {
	
	@Test
	void testeParseVorgabeZeit() {
		String file = "./res/sj_t1_probleminstanz.xml";
		long vorgabeZeit = 60000;
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			assertEquals(vorgabeZeit, schlangenjagdModell.getVorgabeZeit(), () -> "Die gelesene Zeitvorgabe '" 
			+ schlangenjagdModell.getVorgabeZeit() + "' entspricht nicht dem vorgegebenen Wert '" + vorgabeZeit + "'.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}
	
	@Test
	void testeParseAbgabeZeit() {
		String file = "./res/sj_t1_loesung.xml";
		long abgabeZeit = 1;
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			assertEquals(abgabeZeit, schlangenjagdModell.getAbgabeZeit(), () -> "Die gelesene Abgabezeit '" 
			+ schlangenjagdModell.getAbgabeZeit() + "' entspricht nicht dem vorgegebenen Wert '" + abgabeZeit + "'.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testeParseVollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_t1_probleminstanz.xml";
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			Dschungel dschungel = schlangenjagdModell.getDschungel();
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
	void testeParseUnvollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_t1_unvollstaendig.xml";
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			Dschungel dschungel = schlangenjagdModell.getDschungel();
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
	void testeParseLoesungSchlangenAnzahl() {
		String file = "./res/sj_t2_loesung.xml";
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			List <Schlange> schlangen = schlangenjagdModell.getSchlangen();
			int schlangenAnzahl = 6;
			assertEquals(schlangenAnzahl, schlangen.size(), "Es wurden " + schlangen.size() + " statt "
			+ schlangenAnzahl + " Schlangen gefunden.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
