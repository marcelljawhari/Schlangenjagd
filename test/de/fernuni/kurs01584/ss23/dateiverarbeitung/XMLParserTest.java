package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class XMLParserTest {

	@Test
	void testeGetZeitVorgabe() {
		String file = "./res/sj_p1_probleminstanz.xml";
		long zeitVorgabe = 60000;
		try {
			DateiLeser leser = new DateiLeser(file);
			XMLParser parser = new XMLParser(leser.getDocument());
			SchlangenjagdModell schlangenjagdModell = parser.getSchlangenjagdModell();
			assertEquals(zeitVorgabe, schlangenjagdModell.getZeitVorgabe(), () -> "Die Zeitvorgabe '" + schlangenjagdModell.getZeitVorgabe() 
			+ "' entspricht nicht dem vorgegebenen Wert '" + zeitVorgabe + "'.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

	@Test
	void testeGetVollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_p1_probleminstanz.xml";
		try {
			DateiLeser leser = new DateiLeser(file);
			XMLParser parser = new XMLParser(leser.getDocument());
			SchlangenjagdModell schlangenjagdModell = parser.getSchlangenjagdModell();
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
	void testeGetUnvollstaendigerDschungelFelderAnzahl() {
		String file = "./res/sj_p1_unvollstaendig.xml";
		try {
			DateiLeser leser = new DateiLeser(file);
			XMLParser parser = new XMLParser(leser.getDocument());
			SchlangenjagdModell schlangenjagdModell = parser.getSchlangenjagdModell();
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
	void testeGetLoesungSchlangenAnzahl() {
		String file = "./res/sj_p2_loesung.xml";
		try {
			DateiLeser leser = new DateiLeser(file);
			XMLParser parser = new XMLParser(leser.getDocument());
			SchlangenjagdModell schlangenjagdModell = parser.getSchlangenjagdModell();
			List <Schlange> schlangen = schlangenjagdModell.getSchlangen();
			int schlangenAnzahl = 6;
			assertEquals(schlangenAnzahl, schlangen.size(), "Es wurden " + schlangen.size() + " statt "
			+ schlangenAnzahl + " Schlangen gefunden.");
		} catch (Exception e) {
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
