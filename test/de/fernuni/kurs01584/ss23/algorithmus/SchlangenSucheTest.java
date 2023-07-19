package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.jdom2.Document;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.darstellung.Darstellung;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdFabrik;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;

class SchlangenSucheTest {
	private static String zeichenmenge = "asd";
	private static String[][] feldZeichen = { {"d", "a", "s"}, {"a", "a", "a"}, {"s", "s", "d"} };
	private static String[] schlangenartZeichenketten = { "das", "sad" };
	private static String[][][] schlangenKodierungen = { { {"A0"}, {"F0", "F1", "F2"} }, { {"A1"}, {"F6", "F4", "F8"} } };
	private static long vorgabeZeit = 10000;
	private static long abgabeZeit = 5000;
	private static SchlangenjagdModell schlangenjagdModell = SchlangenjagdFabrik.erzeugeLoesung(zeichenmenge, feldZeichen,
			schlangenartZeichenketten, schlangenKodierungen, vorgabeZeit, abgabeZeit);
	
	@Test
	void testeSucheSchlangen() {
		SchlangenSuche suche = new SchlangenSuche(schlangenjagdModell);
		suche.sucheSchlangen();
		assertTrue(schlangenjagdModell.getSchlangen().size() >= 2, "Es wurden nicht alle Schlangen platziert.");
	}

}
