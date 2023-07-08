package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Feld;

class FeldSortiererTest {

	@Test
	void testeSortiereByPunkte() {
		List<Feld> felder = new ArrayList<Feld>();
		felder.add(new Feld("A", "F0", 0, 0, 1, 1));
		felder.add(new Feld("B", "F1", 0, 1, 8, 1));
		felder.add(new Feld("C", "F2", 0, 2, 5, 1));
		felder.add(new Feld("D", "F3", 0, 3, 9, 1));
		felder.add(new Feld("E", "F4", 0, 4, 9, 1));
		
		FeldSortierer sortierer = new FeldSortierer();
		sortierer.sortiereByPunkte(felder);
		
		for(int i = 0; i < felder.size() - 1; i++) {
			if(felder.get(i).getPunkte() < felder.get(i+1).getPunkte()) {
				fail("Die Felder mit dem Index " + i + " und " + (i+1) + " wurden falsch sortiert, denn " 
						+ felder.get(i).getPunkte() + " ist kleiner als " + felder.get(i+1).getPunkte() + ".");
			}
		}
	}

}
