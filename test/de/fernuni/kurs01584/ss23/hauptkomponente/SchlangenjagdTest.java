package de.fernuni.kurs01584.ss23.hauptkomponente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.hauptkomponente.SchlangenjagdAPI.Fehlertyp;

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
		String eingabeDatei = "./res/sj_test_loesung_fehler.xml";
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		List<Fehlertyp> fehler = schlangenjagd.pruefeLoesung(eingabeDatei);
		assertTrue(fehler.contains(Fehlertyp.GLIEDER), "Gliederfehler wurden nicht gefunden.");
		assertTrue(fehler.contains(Fehlertyp.ZUORDNUNG), "Zuordnungsfehler wurden nicht gefunden.");
		assertTrue(fehler.contains(Fehlertyp.VERWENDUNG), "Verwendungsfehler wurden nicht gefunden.");
		assertTrue(fehler.contains(Fehlertyp.NACHBARSCHAFT), "Nachbarschaftsfehler wurden nicht gefunden.");
	}
	
	@Test
	void testeBewerteLoesung() {
		String eingabeDatei = "./res/sj_p1_loesung.xml";
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		int punkte = schlangenjagd.bewerteLoesung(eingabeDatei);
		assertEquals(8, punkte, "Es wird die false Punktzahl berechnet.");
	}
	
	@Test
	void testeGetName() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("Bugovsky Marcell", schlangenjagd.getName(), "Es wird der falsche Name ausgegeben.");
	}
	
	@Test
	void testeGetMatrikelnummer() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("3989690", schlangenjagd.getMatrikelnummer(), "Es wird die falsche Matrikelnummer ausgegeben.");
	}
	
	@Test
	void testeGetEmail() {
		Schlangenjagd schlangenjagd = new Schlangenjagd();
		assertEquals("marcell@bugovsky.at", schlangenjagd.getEmail(), "Es wird die falsche Email-Adresse ausgegeben.");
	}

}
