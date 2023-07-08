package de.fernuni.kurs01584.ss23.algorithmus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Schlangenart;

class SchlangenartenSortiererTest {

	@Test
	void testeSortiereByPunkte() {
		List<Schlangenart> schlangenarten = new ArrayList<Schlangenart>();
		schlangenarten.add(new Schlangenart("A0", "TEST", 1, 3, 1));
		schlangenarten.add(new Schlangenart("A0", "TEST", 8, 2, 1));
		schlangenarten.add(new Schlangenart("A0", "TEST", 5, 1, 1));
		schlangenarten.add(new Schlangenart("A0", "TEST", 9, 4, 1));
		schlangenarten.add(new Schlangenart("A0", "TEST", 9, 3, 1));
		
		SchlangenartenSortierer sortierer = new SchlangenartenSortierer();
		sortierer.sortiereByPunkte(schlangenarten);
		
		for(int i = 0; i < schlangenarten.size() - 1; i++) {
			if(schlangenarten.get(i).getPunkte() < schlangenarten.get(i+1).getPunkte()) {
				fail("Die Felder mit dem Index " + i + " und " + (i+1) + " wurden falsch sortiert, denn " 
						+ schlangenarten.get(i).getPunkte() + " ist kleiner als " + schlangenarten.get(i+1).getPunkte() + ".");
			}
		}
	}

}
