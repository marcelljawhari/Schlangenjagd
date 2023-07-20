package de.fernuni.kurs01584.ss23.hauptkomponente;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;
import de.fernuni.kurs01584.ss23.hauptkomponente.SchlangenjagdAPI.Fehlertyp;
import de.fernuni.kurs01584.ss23.algorithmus.LoesungsBewerter;
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
	
//	@Test
//	void loeseAlleProbleminstanzen() {
//		String ausgabeDatei = "./res/sj_test.xml";
//		Schlangenjagd schlangenjagd = new Schlangenjagd();
//		for(int p = 1; p < 16; p++) {
//			String eingabeDatei = "./res/sj_p" + p + "_probleminstanz.xml";
//			schlangenjagd.loeseProbleminstanz(eingabeDatei, ausgabeDatei);
//
//			try {
//				DateiLeser leser = new DateiLeser();
//				DocumentParser parser = new DocumentParser(leser.lese(ausgabeDatei));
//				SchlangenjagdModell schlangenjagdModell = parser.parseSchlangenjagd();
//				LoesungsBewerter bewerter = new LoesungsBewerter(schlangenjagdModell);
//				System.out.println("Probleminstanz: p" + p);
//				System.out.println("Punkte: " + bewerter.bewerte());
//				System.out.println("Zeitvorgabe: " + schlangenjagdModell.getVorgabeZeit());
//				System.out.println("Abgabezeit: " + schlangenjagdModell.getAbgabeZeit());
//				System.out.println();
//			} catch (Exception e) {
//				System.out.println("Fehler: " + e.getMessage());
//			}
//		}
//	}
	
//	@Test
//	void bewerteAlleLoesungen() {
//		Schlangenjagd schlangenjagd = new Schlangenjagd();
//		for(int p = 1; p < 16; p++) {
//			String eingabeDatei = "./res/sj_p" + p + "_loesung.xml";
//			;
//			System.out.println("Probleminstanz: p" + p);
//			System.out.println("Punkte: " + schlangenjagd.bewerteLoesung(eingabeDatei));
//		}
//	}

}
