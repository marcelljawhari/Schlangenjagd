package de.fernuni.kurs01584.ss23.hauptkomponente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

class SchlangenjagdTest {

	@Test
	void testeLoeseProbleminstanz() {
		String eingabeDatei = "./res/sj_p1_probleminstanz.xml";
		String ausgabeDatei = "./res/sj_test.xml";
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertTrue(schlangenjagd.loeseProbleminstanz(eingabeDatei, ausgabeDatei), "Es konnte keine Loesung gefunden werden.");
	}
	
	@Test
	void testeErzeugeProbleminstanz() {
		String eingabeDatei = "./res/sj_p1_probleminstanz.xml";
		String ausgabeDatei = "./res/sj_test.xml";
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertTrue(schlangenjagd.erzeugeProbleminstanz(eingabeDatei, ausgabeDatei), "Es konnte kein Dschungel generiert werden.");
		
	}
	
	@Test
	void testePruefeLoesung() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		
	}
	
	@Test
	void testeBewerteLoesung() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		
	}
	
	@Test
	void testeGetName() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("Bugovsky Marcell", schlangenjagd.getName(), "Es wir der falsche Name ausgegeben.");
	}
	
	@Test
	void testeGetMatrikelnummer() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("3989690", schlangenjagd.getMatrikelnummer(), "Es wir die falsche Matrikelnummer ausgegeben.");
	}
	
	@Test
	void testeGetEmail() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("marcell@bugovsky.at", schlangenjagd.getEmail(), "Es wir die falsche Email-Adresse ausgegeben.");
	}

}
