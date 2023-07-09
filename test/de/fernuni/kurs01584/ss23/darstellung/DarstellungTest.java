package de.fernuni.kurs01584.ss23.darstellung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.XMLParser;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DarstellungTest {

	@Test
	void testeSucheSchlangen() {
		String file = "./res/sj_p15_loesung.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			Dschungel dschungel = parser.parseDschungel();
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel,
					schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			Darstellung darstellung = new Darstellung(schlangenjagdModell);
			darstellung.print();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
