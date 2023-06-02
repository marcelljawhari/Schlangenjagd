package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


class FeldTest {

	@Test
	void testeGetZeichenA() {
		char zeichen = 'a';
		Feld feld = new Feld(zeichen, 0, 0, 0, 0);
		assertEquals(feld.getZeichen(), zeichen, () -> "Der Zeilenwert '" + feld.getZeichen() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeichen + "'.");
	}
	
	@Test
	void testeGetZeilePositiv() {
		int zeile = 2;
		Feld feld = new Feld('a', zeile, 0, 0, 0);
		assertEquals(feld.getZeile(), zeile, () -> "Der Zeilenwert '" + feld.getZeile() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + zeile + "'.");
	}
	
	@Test
	void testeGetSpaltePositiv() {
		int spalte = 4;
		Feld feld = new Feld('a', 0, spalte, 0, 0);
		assertEquals(feld.getSpalte(), spalte, () -> "Der Spaltenwert '" + feld.getSpalte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + spalte + "'.");
	}
	
	@Test
	void testeGetPunktePositiv() {
		int punkte = 6;
		Feld feld = new Feld('a', 0, 0, punkte, 0);
		assertEquals(feld.getPunkte(), punkte, () -> "Der Punktwert '" + feld.getPunkte() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + punkte + "'.");
	}
	
	@Test
	void testeGetVerwendbarkeitPositiv() {
		int verwendbarkeit = 8;
		Feld feld = new Feld('a', 0, 0, 0, verwendbarkeit);
		assertEquals(feld.getVerwendbarkeit(), verwendbarkeit, () -> "Die Verwendbarkeit '" + feld.getVerwendbarkeit() 
				+ "' entspricht nicht dem vorgegebenen Wert '" + verwendbarkeit + "'.");
	}
	
	@Test
	void testeKonstruktorNegativeWerteSolltenExceptionWerfen() {
		int zeile = -1;
		int spalte = -2;
		int punkte = -3;
		int verwendbarkeit = -4;
		assertThrows(IllegalArgumentException.class, () -> new Feld('a', zeile, 0, 0, 0),
				() -> "Fuer den (negativen) Zeilenwert '" + zeile + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld('a', 0, spalte, 0, 0),
				() -> "Fuer den (negativen) Spaltenwert '" + spalte + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld('a', 0, 0, punkte, 0),
				() -> "Fuer den (negativen) Punktwert '" + punkte + "' wird keine Ausnahme erzeugt.");
		assertThrows(IllegalArgumentException.class, () -> new Feld('a', 0, 0, 0, verwendbarkeit),
				() -> "Fuer die (negative) Verwendbarkeit '" + verwendbarkeit + "' wird keine Ausnahme erzeugt.");
	}
	
/**
 * Die Testklasse demonstriert verschiedene Annotationen und Methoden fuer den
 * Lebenszyklus von Testinstanzen (BeforeAll, BeforeEach, AfterEach, AfterAll)
 * und fuer verschiedene Moeglichkeiten zur Implementierung von Tests (Test,
 * RepeatedTest, ParameterizedTest) sowie geschachtelte Tests (Nested).
 */	
	
//	@BeforeAll
//	static void initAll() {
//		System.out.println("Vor allen Testfaellen.");
//	}
//
//	@BeforeEach
//	void init() {
//		System.out.println("Vor jedem Testfall.");
//	}
//
//	@AfterEach
//	void tearDown() {
//		System.out.println("Nach jedem Testfall.");
//	}
//
//	@AfterAll
//	static void tearDownAll() {
//		System.out.println("Nach allen Testfaellen.");
//	}
//
//	@DisplayName("Einfacher positiver Test.")
//	@Test
//	void testeGetZeile() {
//		int zeile = 1;
//		Feld feld = new Feld(zeile, 0);
//		assertEquals(feld.getZeile(), zeile, () -> "Der Zeilenwert '" + feld.getZeile()
//				+ "' entspricht nicht dem vorgegebenen Wert '" + zeile + "'.");
//	}
//
//	@DisplayName("Einfacher negativer Test.")
//	@Test
//	void testeGetSpalte() {
//		int spalte = -1;
//		assertThrows(IllegalArgumentException.class, () -> new Feld(0, spalte),
//				() -> "Fuer den (negativen) Spaltenwert '" + spalte + "' wird keine Ausnahme erzeugt.");
//	}
//
//	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//	@Nested
//	class Wiederholte_Tests {
//		static Random zufallszahlenGenerator;
//
//		@BeforeAll
//		static void initAll() {
//			zufallszahlenGenerator = new Random(1);
//		}
//
//		@DisplayName("Wiederholter negativer Test.")
//		@RepeatedTest(10)
//		void testeGetSpalte(RepetitionInfo repetitionInfo) {
//			int spalte = zufallszahlenGenerator.nextInt(-100000, -1);
//			assertThrows(IllegalArgumentException.class, () -> new Feld(0, spalte),
//					() -> "Fuer den (negativen) Spaltenwert '" + spalte + "' wird keine Ausnahme erzeugt.");
//		}
//	}
//
//	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//	@Nested
//	class Parametrisierte_Tests {
//		@DisplayName("Parameterisierter positiver Test.")
//		@ParameterizedTest
//		@ValueSource(ints = { 0, 1, 2, 3, 5, 7, 11, 64, 1024 })
//		void testeKonstruktorUndGetSpaltePositiv(int spalte) {
//			Feld feld = new Feld(0, spalte);
//			assertEquals(feld.getSpalte(), spalte, "Der Spaltenwert '" + feld.getSpalte()
//					+ "' entspricht nicht dem vorgegebenen Wert '" + spalte + "'.");
//		}
//
//		static IntStream erzeugeNegativeParameterwerte() {
//			return IntStream.iterate(-1, i -> i - 10).limit(20);
//		}
//
//		@DisplayName("Parameterisierter negativer Test.")
//		@ParameterizedTest
//		@MethodSource("erzeugeNegativeParameterwerte")
//		void testeKonstruktorUndGetZeileNegativ(int zeile) {
//			assertThrows(IllegalArgumentException.class, () -> new Feld(zeile, 0),
//					() -> "Fuer den (negativen) Zeilenwert '" + zeile + "' wird keine Ausnahme erzeugt.");
//		}
//	}
}
