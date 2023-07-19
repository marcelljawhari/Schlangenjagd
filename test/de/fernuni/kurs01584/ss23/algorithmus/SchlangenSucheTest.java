package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.darstellung.Darstellung;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DocumentParser;

class SchlangenSucheTest {

	@Test
	void testeSucheSchlangen() {
		String file = "./res/sj_p15_probleminstanz.xml";
		int schlangenAnzahl = 1;
		try {
			DateiLeser leser = new DateiLeser();
			DocumentParser parser = new DocumentParser(leser.lese(file));
			SchlangenjagdModell schlangenjagdModell = new SchlangenjagdModell(parser.parseDschungel()
					, parser.parseSchlangenarten(), parser.parseVorgabeZeit());
			SchlangenSuche schlangenSuche = new SchlangenSuche(schlangenjagdModell);
			schlangenSuche.sucheSchlangen();
			List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
			LoesungsPruefer pruefer = new LoesungsPruefer(schlangenjagdModell);
			Darstellung darstellung = new Darstellung(schlangenjagdModell);
			darstellung.print();
			System.out.println("Fehler: ");
			System.out.println("Glieder: " + pruefer.pruefeGlieder());
			System.out.println("Verwendung: " + pruefer.pruefeVerwendung());
			System.out.println("Nachbarschaft: " + pruefer.pruefeNachbarschaft());
			System.out.println("Zuordnung: " + pruefer.pruefeZuordnung());
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
