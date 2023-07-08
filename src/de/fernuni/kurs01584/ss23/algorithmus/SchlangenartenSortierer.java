package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.Schlangenart;

public class SchlangenartenSortierer {
	
	public void sortiereByPunkte(List<Schlangenart> schlangenarten) {
		Vergleicher<Schlangenart> vergleicher = (schlangenart1, schlangenart2) 
				-> { return (schlangenart1.getPunkte() - schlangenart2.getPunkte()); };
		sortiere(schlangenarten, vergleicher);
	}
	
	public void sotiereByLaenge(List<Schlangenart> schlangenarten) {
		Vergleicher<Schlangenart> vergleicher = (schlangenart1, schlangenart2) 
				-> { return (schlangenart1.getZeichenkette().length() - schlangenart2.getZeichenkette().length()); };
		sortiere(schlangenarten, vergleicher);
	}
	
	private void sortiere(List<Schlangenart> schlangenarten, Vergleicher<Schlangenart> vergleicher) {
		insertSort(schlangenarten, vergleicher);
	}
	
	private void insertSort(List<Schlangenart> schlangenarten, Vergleicher<Schlangenart> vergleicher) {
		for(int i = 0; i < schlangenarten.size(); i++) {
			int maxIndex = findeIndexVonGroessterSchlangenart(schlangenarten, i, vergleicher);
			if(i < maxIndex) {
				tausche(schlangenarten, i, maxIndex);
			}
		}
	}
	
	private int findeIndexVonGroessterSchlangenart(List<Schlangenart> schlangenarten, int startIndex, Vergleicher<Schlangenart> vergleicher) {
		int index = startIndex;
		Schlangenart maxSchlangenart = schlangenarten.get(index);
		for(int i = index; i < schlangenarten.size(); i++) {
			if(vergleicher.vergleiche(schlangenarten.get(i), maxSchlangenart) > 0) {
				index = i;
				maxSchlangenart = schlangenarten.get(i);
			}
		}
		return index;
	}
	
	private void tausche(List<Schlangenart> schlangenarten, int index1, int index2) {
		Schlangenart schlangenart1 = schlangenarten.get(index1);
		schlangenarten.set(index1, schlangenarten.get(index2));
		schlangenarten.set(index2, schlangenart1);
	}

}
