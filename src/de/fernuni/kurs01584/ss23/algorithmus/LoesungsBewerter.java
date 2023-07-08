package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;

public class LoesungsBewerter {
	
	public int bewerte(List<Schlange> schlangen) {
		int punkte = 0;
		
		for(Schlange schlange : schlangen) {
			punkte += schlange.getSchlangenart().getPunkte();
			List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
			for(Schlangenglied schlangenglied : schlangenglieder) {
				punkte += schlangenglied.getFeld().getPunkte();
			}
		}
		
		return punkte;
	}
}
