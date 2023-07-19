package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import de.fernuni.kurs01584.ss23.modell.SchlangenjagdFabrik;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DocumentBuilderTest {
	private static String zeichenmenge = "asd";
	private static String[][] feldZeichen = { {"d", "a", "s"}, {"a", "a", "a"}, {"s", "s", "d"} };
	private static String[] schlangenartZeichenketten = { "das", "sad" };
	private static String[][][] schlangenKodierungen = { { {"A0"}, {"F0", "F1", "F2"} }, { {"A1"}, {"F6", "F4", "F8"} } };
	private static long vorgabeZeit = 10000;
	private static long abgabeZeit = 5000;
	private static Document document;
	
	@BeforeAll
	static void init() {
		SchlangenjagdModell schlangenjagdModell = SchlangenjagdFabrik.erzeugeLoesung(zeichenmenge, feldZeichen,
				schlangenartZeichenketten, schlangenKodierungen, vorgabeZeit, abgabeZeit);
		DocumentBuilder builder = new DocumentBuilder();
		document = builder.build(schlangenjagdModell);
	}
	
	@Test
	void testeDocType() {
		assertEquals("[DocType: <!DOCTYPE Schlangenjagd SYSTEM \"schlangenjagd.dtd\">]", 
				document.getDocType().toString(), "Der DocType ist falsch.");
	}
	
	@Test
	void testeRootElement() {
		Element rootElement = document.getRootElement();
		assertNotNull(rootElement, "Das Root Element ist null.");
		assertEquals("Schlangenjagd", rootElement.getName(), "Das Root Element ist nicht 'Schlangenjagd'");
	}
	
	@Test
	void testeZeitElement() {
		Element rootElement = document.getRootElement();
		Element zeitElement = rootElement.getChild("Zeit");
		assertNotNull(zeitElement, "Das Zeit Element ist null.");
		assertEquals("s", zeitElement.getAttributeValue("einheit"), "Die Einheit des Zeit Elements ist nicht 's'.");
	}
	
	@Test
	void testeVorgabeZeitElement() {
		Element rootElement = document.getRootElement();
		Element zeitElement = rootElement.getChild("Zeit");
		Element vorgabeZeitElement = zeitElement.getChild("Vorgabe");
		assertNotNull(vorgabeZeitElement, "Das Vorgabe Element ist null.");
		assertEquals("10.0", vorgabeZeitElement.getText(), "Die Zeitvorgabe ist " + vorgabeZeitElement.getText() 
				+ " statt 10.0.");
	}
	
	@Test
	void testeAbgabeZeitElement() {
		Element rootElement = document.getRootElement();
		Element zeitElement = rootElement.getChild("Zeit");
		Element abgabeZeitElement = zeitElement.getChild("Abgabe");
		assertNotNull(abgabeZeitElement, "Das Abgabe Element ist null.");
		assertEquals("5.0", abgabeZeitElement.getText(), "Die Abgabezeit ist " + abgabeZeitElement.getText() 
				+ " statt 5.0.");
	}
	
	@Test
	void testeDschungelElement() {
		Element rootElement = document.getRootElement();
		Element dschungelElement = rootElement.getChild("Dschungel");
		assertNotNull(dschungelElement, "Das Dschungel Element ist null.");
		assertEquals("3", dschungelElement.getAttributeValue("zeilen"), "Der Dschungel hat " 
				+ dschungelElement.getAttributeValue("zeilen") + " Zeilen statt 3.");
		assertEquals("3", dschungelElement.getAttributeValue("spalten"), "Der Dschungel hat " 
				+ dschungelElement.getAttributeValue("spalten") + " Spalten statt 3.");
		assertEquals(zeichenmenge, dschungelElement.getAttributeValue("zeichen"), "Der Dschungel hat"
				+ " die falsche Zeichenmenge.");
		
	}
	
	@Test
	void testeFelderElemente() {
		Element rootElement = document.getRootElement();
		Element dschungelElement = rootElement.getChild("Dschungel");
		List<Element> felderElemente = dschungelElement.getChildren("Feld");
		int index = 0;
		for(int zeile = 0; zeile < feldZeichen.length; zeile++) {
			for(int spalte = 0; spalte < feldZeichen[0].length; spalte++) {
				Element feldElement = felderElemente.get(index);
				assertEquals("F" + index, feldElement.getAttributeValue("id"), "Das Feld mit index " + index + " hat die falsche ID.");
				assertEquals("" + zeile, feldElement.getAttributeValue("zeile"), "Das Feld mit index " + index + " ist in der falschen Zeile.");
				assertEquals("" + spalte, feldElement.getAttributeValue("spalte"), "Das Feld mit index " + index + " ist in der falschen Spalte.");
				assertEquals(feldZeichen[zeile][spalte], feldElement.getText(), "Das Feld mit index " + index + " ist in das falsche Zeichen.");
				index++;
			}
		}
		
	}
	
	@Test
	void testeSchlangenartenElement() {
		Element rootElement = document.getRootElement();
		Element schlangenartenElement = rootElement.getChild("Schlangenarten");
		assertNotNull(schlangenartenElement, "Das Element Schlangenarten ist null.");
		List<Element> schlangenartElemente = schlangenartenElement.getChildren("Schlangenart");
		assertEquals(schlangenartZeichenketten.length, schlangenartElemente.size(), "Die Anzahl der Schlangenarten ist falsch.");
		for(int i = 0; i < schlangenartElemente.size(); i++) {
			Element schlangenartElement = schlangenartElemente.get(i);
			Element zeichenketteElement = schlangenartElement.getChild("Zeichenkette");
			assertEquals("A" + i, schlangenartElement.getAttributeValue("id"), "Die ID der Schlangenart ist falsch");
			assertEquals(schlangenartZeichenketten[i], zeichenketteElement.getText(), "Die Zeichenkette der Schlangenart ist falsch.");
			assertEquals("Distanz", schlangenartElement.getChild("Nachbarschaftsstruktur").getAttributeValue("typ"), 
					"Der Typ der Nachbarschaftsstruktur der Schlangenart ist falsch.");
		}
	}
	
	@Test
	void testeSchlangenElement() {
		Element rootElement = document.getRootElement();
		Element schlangenElement = rootElement.getChild("Schlangen");
		assertNotNull(schlangenElement, "Das Element Schlangen ist null.");
		List<Element> schlangenElemente = schlangenElement.getChildren("Schlange");
		assertEquals(schlangenKodierungen.length, schlangenElemente.size(), "Die Anzahl der Schlangen ist falsch.");
		for(int i = 0; i < schlangenElemente.size(); i++) {
			Element schlangeElement = schlangenElemente.get(i);
			List<Element> feldElemente = schlangeElement.getChildren("Schlangenglied");
			assertEquals(schlangenKodierungen[i][1].length, feldElemente.size(), "Die Anzahl der Felder der Schlange ist falsch.");
			for(int j = 0; j < feldElemente.size(); j++) {
				assertEquals(schlangenKodierungen[i][1][j], feldElemente.get(j).getAttributeValue("feld"), 
						"Das Feld der Schlange " + feldElemente.get(j) + " sollte " + schlangenKodierungen[i][1][j] + " sein.");
			}
		}
	}

}
