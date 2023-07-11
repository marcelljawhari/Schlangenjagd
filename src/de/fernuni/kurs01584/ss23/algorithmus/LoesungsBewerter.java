package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

public class LoesungsBewerter {
	private SchlangenjagdModell schlangenjagdModell;
	
	public LoesungsBewerter(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	public int bewerte() {
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		int punkte = 0;
		
		if(schlangen != null) {
			for(Schlange schlange : schlangen) {
				punkte += schlange.getSchlangenart().getPunkte();
				List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
				for(Schlangenglied schlangenglied : schlangenglieder) {
					punkte += schlangenglied.getFeld().getPunkte();
				}
			}
		}
		
		return punkte;
	}
}
