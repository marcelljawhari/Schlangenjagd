package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;
import de.fernuni.kurs01584.ss23.hauptkomponente.SchlangenjagdAPI.Fehlertyp;
import de.fernuni.kurs01584.ss23.modell.Dschungel;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenart;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdFabrik;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class LoesungsPrueferTest {
	private static String zeichenmenge = "asd";
	private static String[][] feldZeichen = { {"d", "a", "s"}, {"a", "a", "a"}, {"s", "s", "d"} };
	private static String[] schlangenartZeichenketten = { "das", "sad" };
	private static String[][][] schlangenKodierungen = { { {"A0"}, {"F0", "F1", "F2", "F3"} },  { {"A0"}, {"F1", "F6"} },  { {"A1"}, {"F0", "F4", "F8"} } };
	private static long vorgabeZeit = 10000;
	private static long abgabeZeit = 5000;
	private static SchlangenjagdModell schlangenjagdModell = SchlangenjagdFabrik.erzeugeLoesung(zeichenmenge, feldZeichen,
			schlangenartZeichenketten, schlangenKodierungen, vorgabeZeit, abgabeZeit);

	@Test
	void testePruefeGlieder() {
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehler = loesungsPruefer.pruefe();
		assertEquals(2, fehler.get(Fehlertyp.GLIEDER), "Die Anzahl der gefundenen Gliederfehler ist falsch.");
	}
	
	@Test
	void testePruefeZurodnung() {
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehler = loesungsPruefer.pruefe();
		assertEquals(3, fehler.get(Fehlertyp.ZUORDNUNG), "Die Anzahl der gefundenen Zuordnungsfehler ist falsch.");
	}
	
	@Test
	void testePruefeVerwendung() {
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehler = loesungsPruefer.pruefe();
		assertEquals(2, fehler.get(Fehlertyp.VERWENDUNG), "Die Anzahl der gefundenen Verwendungsfehler ist falsch.");
	}
	
	@Test
	void testePruefeNachbarschaft() {
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehler = loesungsPruefer.pruefe();
		assertEquals(2, fehler.get(Fehlertyp.NACHBARSCHAFT), "Die Anzahl der gefundenen Nachbarschaftsfehler ist falsch.");
	}
}
