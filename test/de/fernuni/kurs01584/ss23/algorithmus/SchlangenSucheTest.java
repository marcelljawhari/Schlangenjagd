package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.XMLParser;

class SchlangenSucheTest {

	@Test
	void testeSucheSchlangen() {
		String file = "./res/sj_p11_probleminstanz.xml";
		int schlangenAnzahl = 3;
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(parser.parseDschungel()
					, parser.parseSchlangenarten(), parser.parseVorgabeZeit());
			SchlangenSuche schlangenSuche = new SchlangenSuche(schlangenjagdModell);
			schlangenSuche.sucheSchlangen();
			List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
			schlangenSuche.printLoesung();
			assertEquals(schlangenAnzahl, schlangen.size(), 
					() -> "Die gefundene Schlangenanzahl " + schlangen.size() + " entspricht nicht der Vorgabe "
					+ schlangenAnzahl + ".");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
	}

}
