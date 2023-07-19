package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.SchlangenjagdFabrik;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class LoesungsBewerterTest {
	private static String zeichenmenge = "asd";
	private static String[][] feldZeichen = { {"d", "a", "s"}, {"a", "a", "a"}, {"s", "s", "d"} };
	private static String[] schlangenartZeichenketten = { "das", "sad" };
	private static String[][][] schlangenKodierungen = { { {"A0"}, {"F0", "F1", "F2", "F3"} }, { {"A1"}, {"F1", "F8"} } };
	private static long vorgabeZeit = 10000;
	private static long abgabeZeit = 5000;
	private static SchlangenjagdModell schlangenjagdModell = SchlangenjagdFabrik.erzeugeLoesung(zeichenmenge, feldZeichen,
			schlangenartZeichenketten, schlangenKodierungen, vorgabeZeit, abgabeZeit);

	@Test
	void testeBewerte() {
		LoesungsBewerter bewerter = new LoesungsBewerter(schlangenjagdModell);
		int gesamtpunkte = bewerter.bewerte();
		assertEquals(8, gesamtpunkte, "Die Gesamtpunkte sind falsch.");
	}

}
