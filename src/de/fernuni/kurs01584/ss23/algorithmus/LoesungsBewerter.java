package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

public class LoesungsBewerter {
	private SchlangenjagdModell schlangenjagdModell;
	
	/***
	 * Erzeuge einen LoesungsBewerter fuer das gegebene SchlangenjagdModell.
	 * @param schlangenjagdModell SchlangenjagdModell welches bewertet werden soll.
	 */
	public LoesungsBewerter(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}

	/***
	 * Bewerte das gegebene SchlangenjagdModell. Fuer jedes Schlangenglied wird dafuer der Punktwert des zugehoerigen Feldes gutgeschrieben.
	 * Fuer jede Schlange wird der Punktwert der zugehoerigen Schlangenart gutgeschrieben.
	 * @return Kummulierte Punkte aller Schlangen und Schlangenglieder.
	 */
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
