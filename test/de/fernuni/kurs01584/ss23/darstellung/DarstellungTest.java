package de.fernuni.kurs01584.ss23.darstellung;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.SchlangenjagdFabrik;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class DarstellungTest {
	private static String zeichenmenge = "asd";
	private static String[][] feldZeichen = { {"d", "a", "s"}, {"a", "a", "a"}, {"s", "s", "d"} };
	private static String[] schlangenartZeichenketten = { "das", "sad" };
	private static String[][][] schlangenKodierungen = { { {"A0"}, {"F0", "F1", "F2"} }, { {"A1"}, {"F6", "F4", "F8"} } };
	private static long vorgabeZeit = 10000;
	private static long abgabeZeit = 5000;
	private static SchlangenjagdModell schlangenjagdModell = SchlangenjagdFabrik.erzeugeLoesung(zeichenmenge, feldZeichen,
			schlangenartZeichenketten, schlangenKodierungen, vorgabeZeit, abgabeZeit);
	
	private static PrintStream standardOut = System.out;
	private static ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeAll
	static void setUp() {
		Darstellung darstellung = new Darstellung(schlangenjagdModell);
		System.setOut(new PrintStream(outputStreamCaptor));
		darstellung.print();
	}
	
	@AfterAll
	static void tearDown() {
		System.setOut(standardOut);
	}
	
	@Test
	void testeHeader() {
		assertTrue(outputStreamCaptor.toString().trim().contains("=====Darstellung der Probleminstanz=====\r\n"));
	}
	
	@Test
	void testeDschungelInformationen() {
		String dschungelInformationen = "=========Dschungelinformationen=========\r\n"
				+ "Zeichenmenge: asd\r\n"
				+ "Anzahl Zeilen: 3\r\n"
				+ "Anzahl Spalten: 3";
		assertTrue(outputStreamCaptor.toString().trim().contains(dschungelInformationen));
	}
	
	@Test
	void testeDschungelZeichen() {
		String dschungelZeichen = "=========Zeichen des Dschungels=========\r\n"
				+ "das\r\n"
				+ "aaa\r\n"
				+ "ssd";
		assertTrue(outputStreamCaptor.toString().trim().contains(dschungelZeichen));
	}
	
	@Test
	void testeDschungelVerwendbarkeiten() {
		String dschungelVerwendbarkeiten = "====Verwendbarkeiten des Dschungels=====\r\n"
				+ "111\r\n"
				+ "111\r\n"
				+ "111";
		assertTrue(outputStreamCaptor.toString().trim().contains(dschungelVerwendbarkeiten));
	}
	
	@Test
	void testeDschungelPunkte() {
		String dschungelPunkte = "=========Punkte des Dschungels==========\r\n"
				+ "111\r\n"
				+ "111\r\n"
				+ "111";
		assertTrue(outputStreamCaptor.toString().trim().contains(dschungelPunkte));
	}
	
	@Test
	void testeSchlangenarten() {
		String schlangenarten = "=============Schlangenarten=============\r\n"
				+ "SchlangenartID: A0\r\n"
				+ "Zeichenkette: das\r\n"
				+ "Nachbarschaftsstruktur: Distanz(1)\r\n"
				+ "SchlangenartID: A1\r\n"
				+ "Zeichenkette: sad\r\n"
				+ "Nachbarschaftsstruktur: Distanz(1)";
		assertTrue(outputStreamCaptor.toString().trim().contains(schlangenarten));
	}
	
	@Test
	void testeSchlangen() {
		String schlangen = "===============Schlangen================\r\n"
				+ "1. Schlange\r\n"
				+ "SchlangenartID: A0\r\n"
				+ "Zeichenkette: das\r\n"
				+ "Nachbarschaftsstruktur: Distanz(1)\r\n"
				+ "Position im Dschungel:\r\n"
				+ "das\r\n"
				+ "...\r\n"
				+ "...\r\n"
				+ "\r\n"
				+ "2. Schlange\r\n"
				+ "SchlangenartID: A1\r\n"
				+ "Zeichenkette: sad\r\n"
				+ "Nachbarschaftsstruktur: Distanz(1)\r\n"
				+ "Position im Dschungel:\r\n"
				+ "...\r\n"
				+ ".a.\r\n"
				+ "s.d";
		assertTrue(outputStreamCaptor.toString().trim().contains(schlangen));
	}

}
