package de.fernuni.kurs01584.ss23.darstellung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DarstellungTest {

	@Test
	void testeSucheSchlangen() {
		String file = "./res/sj_p5_probleminstanz.xml";
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
			Darstellung darstellung = new Darstellung(schlangenjagdModell);
			darstellung.print();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
