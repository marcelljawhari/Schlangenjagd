package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.Feld;

public class FeldSortierer {
	
	public void sortiereByPunkte(List<Feld> felder) {
		Vergleicher<Feld> vergleicher = (feld1, feld2) -> { return (feld1.getPunkte() - feld2.getPunkte()); };
		sortiere(felder, vergleicher);
	}
	
	public void sortiereByVerwendbarkeit(List<Feld> felder) {
		Vergleicher<Feld> vergleicher = (feld1, feld2) -> { return (feld1.getVerwendbarkeit() - feld2.getVerwendbarkeit()); };
		sortiere(felder, vergleicher);
	}
	
	private void sortiere(List<Feld> felder, Vergleicher<Feld> vergleicher) {
		insertSort(felder, vergleicher);
	}
	
	private void insertSort(List<Feld> felder, Vergleicher<Feld> vergleicher) {
		for(int i = 0; i < felder.size(); i++) {
			int maxIndex = findeIndexVonGroesstenFeld(felder, i, vergleicher);
			if(i < maxIndex) {
				tausche(felder, i, maxIndex);
			}
		}
	}
	
	private int findeIndexVonGroesstenFeld(List<Feld> felder, int startIndex, Vergleicher<Feld> vergleicher) {
		int index = startIndex;
		Feld maxFeld = felder.get(index);
		for(int i = index; i < felder.size(); i++) {
			if(vergleicher.vergleiche(felder.get(i), maxFeld) > 0) {
				index = i;
				maxFeld = felder.get(i);
			}
		}
		return index;
	}
	
	private void tausche(List<Feld> felder, int index1, int index2) {
		Feld feld1 = felder.get(index1);
		felder.set(index1, felder.get(index2));
		felder.set(index2, feld1);
	}
	
}
