package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.darstellung.Darstellung;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.XMLParser;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DschungelGeneratorTest {

	@Test
	void testeGeneriereDschungel() {
		String file = "./res/sj_p15_probleminstanz.xml";
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(parser.parseDschungel()
					, parser.parseSchlangenarten(), parser.parseVorgabeZeit());
			DschungelGenerator dschungelGenerator = new DschungelGenerator(schlangenjagdModell);
			Darstellung darstellung = new Darstellung(schlangenjagdModell);
			dschungelGenerator.generiereDschungel();
			darstellung.print();
			SchlangenSuche schlangenSuche = new SchlangenSuche(schlangenjagdModell);
			schlangenSuche.sucheSchlangen();
			schlangenSuche.printLoesung();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unerwartete Exception " + e + " wurde ausgeloest.");
		}
		
	}

}
