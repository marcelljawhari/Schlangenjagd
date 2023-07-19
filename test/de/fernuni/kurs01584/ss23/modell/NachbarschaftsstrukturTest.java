package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NachbarschaftsstrukturTest {

	@Test
	void testeKonstruktorDistanz1Sollte8DeltasGeben() {
		int distanz = 1;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
		assertEquals(nachbarschaftsstruktur.getDeltas().length, 8, () -> "Die Nachbarschaftsstruktur "
				+ "enthält nicht die Richtige Menge an Deltas.");
	}

	@Test
	void testeKonstruktorDistanz2Sollte24DeltasGeben() {
		int distanz = 2;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
		assertEquals(nachbarschaftsstruktur.getDeltas().length, 24, () -> "Die Nachbarschaftsstruktur "
				+ "enthält nicht die Richtige Menge an Deltas.");
	}

	@Test
	void testeKonstruktorDistanz3Sollte48DeltasGeben() {
		int distanz = 3;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
		assertEquals(nachbarschaftsstruktur.getDeltas().length, 48, () -> "Die Nachbarschaftsstruktur "
				+ "enthält nicht die Richtige Menge an Deltas.");
	}

	@Test
	void testeKonstruktorZweiVerschiedeneWerteSollten8DeltasGeben() {
		int x = 1;
		int y = 2;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(x, y);
		assertEquals(nachbarschaftsstruktur.getDeltas().length, 8, () -> "Die Nachbarschaftsstruktur "
				+ "enthält nicht die Richtige Menge an Deltas.");
	}

	@Test
	void testeKonstruktorZweiGleicheWerteSollten4DeltasGeben() {
		int x = 2;
		int y = x;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(x, y);
		assertEquals(nachbarschaftsstruktur.getDeltas().length, 4, () -> "Die Nachbarschaftsstruktur "
				+ "enthält nicht die Richtige Menge an Deltas.");
	}
	
	@Test
	void testeKonstruktorDistanz1ArrayWerte() {
		int distanz = 1;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
		int[][] testDeltas = {{1,1},{1,0},{1,-1},{0,1},{0,-1},{-1,1},{-1,0},{-1,-1}};
		boolean alleGefunden = true;
		boolean gefunden;
		for(int[] testDelta : testDeltas) {
			gefunden = false;
			for(int[] delta : nachbarschaftsstruktur.getDeltas() ) {
				if(delta[0] == testDelta[0] && delta[1] == testDelta[1]) {
					gefunden = true;
				}
			}
			if(!gefunden) {
				alleGefunden = false;
				break;
			}
		}
		assertTrue(alleGefunden);
	}
	
	@Test
	void testeKonstruktorSprung12ArrayWerte() {
		int x = 1;
		int y = 2;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(x, y);
		int[][] testDeltas = {{1,2},{2,1},{1,-2},{-2,1},{-1,2},{2,-1},{-1,-2},{-2,-1}};
		boolean alleGefunden = true;
		boolean gefunden;
		for(int[] testDelta : testDeltas) {
			gefunden = false;
			for(int[] delta : nachbarschaftsstruktur.getDeltas() ) {
				if(delta[0] == testDelta[0] && delta[1] == testDelta[1]) {
					gefunden = true;
				}
			}
			if(!gefunden) {
				alleGefunden = false;
				break;
			}
		}
		assertTrue(alleGefunden, () -> "Es wurden nicht alle Deltas gefunden.");
	}
	
	@Test
	void testeKonstruktorSprung10ArrayWerte() {
		int x = 1;
		int y = 0;
		Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur(x, y);
		int[][] testDeltas = {{x,y},{-x,y},{y,x},{y,-x}};
		boolean alleGefunden = true;
		boolean gefunden;
		for(int[] testDelta : testDeltas) {
			gefunden = false;
			for(int[] delta : nachbarschaftsstruktur.getDeltas() ) {
				if(delta[0] == testDelta[0] && delta[1] == testDelta[1]) {
					gefunden = true;
				}
			}
			if(!gefunden) {
				alleGefunden = false;
				break;
			}
		}
		assertTrue(alleGefunden, () -> "Es wurden nicht alle Deltas gefunden.");
	}
	
	@Test
	void testeGetTyp() {
		Nachbarschaftsstruktur distanz = new Nachbarschaftsstruktur(1);
		Nachbarschaftsstruktur sprung = new Nachbarschaftsstruktur(1, 2);
		assertEquals("Distanz", distanz.getTyp(), () -> "Der Typ der Nachbarschaftsstruktur ist " + distanz.getTyp() 
				+ "statt Distanz.");
		assertEquals("Sprung", sprung.getTyp(), () -> "Der Typ der Nachbarschaftsstruktur ist " + sprung.getTyp() 
				+ "statt Sprung.");
	}
	
	@Test
	void testeGetParameter() {
		int deltaX = 1;
		int deltaY = 2;
		Nachbarschaftsstruktur distanz = new Nachbarschaftsstruktur(deltaX);
		Nachbarschaftsstruktur sprung = new Nachbarschaftsstruktur(deltaX, deltaY);
		assertEquals(deltaX, distanz.getParameter()[0], () -> "Der Parameter der Nachbarschaftsstruktur " + distanz + " ist "
				+ distanz.getParameter()[0] + " statt " + deltaX + ".");
		assertEquals(deltaX, sprung.getParameter()[0], () -> "Der erste Parameter der Nachbarschaftsstruktur " + sprung + " ist "
				+ sprung.getParameter()[0] + " statt " + deltaX + ".");
		assertEquals(deltaY, sprung.getParameter()[1], () -> "Der zweite Parameter der Nachbarschaftsstruktur " + sprung + " ist "
				+ sprung.getParameter()[1] + " statt " + deltaY + ".");
	}
	
	@Test
	void testeToString() {
		int deltaX = 1;
		int deltaY = 2;
		Nachbarschaftsstruktur distanz = new Nachbarschaftsstruktur(deltaX);
		Nachbarschaftsstruktur sprung = new Nachbarschaftsstruktur(deltaX, deltaY);
		assertEquals("Distanz(1)", distanz.toString(), () -> "Die Nachbarschaftsstruktur wird als " + distanz.toString() 
				+ " statt als Distanz(1) dargestellt.");
		assertEquals("Sprung(1,2)", sprung.toString(), () -> "Die Nachbarschaftsstruktur wird als " + sprung.toString() 
		+ " statt als Sprung(1,2) dargestellt.");
	}

}
