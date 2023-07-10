package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DateiSchreiberTest {

	@Test
	void testeSchreibe() {
		String inputFile = "./res/sj_p1_loesung.xml";
		String outputFile = "./res/sj_t1_loesung.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(inputFile));
			Schlangenart[] schlangenarten = parser.parseSchlangenarten();
			Dschungel dschungel = parser.parseDschungel();
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(dschungel
					, schlangenarten, parser.parseSchlangen(schlangenarten, dschungel), parser.parseVorgabeZeit(), parser.parseAbgabeZeit());
			DateiSchreiber schreiber = new DateiSchreiber();
			schreiber.schreibe(schlangenjagdModell, outputFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
