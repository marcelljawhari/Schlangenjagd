package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class Schlange {
	Schlangenart schlangenart;
	List<Schlangenglied> schlangenglieder;
	int laenge;
	
	public Schlange(Schlangenart schlangenart) {
		this.schlangenart = schlangenart;
		schlangenglieder = new ArrayList<Schlangenglied>();
		laenge = schlangenart.getZeichenkette().length();
	}
	
	public void addSchlangenglied(Feld feld) {
		int index = 0;
		if(schlangenglieder.size() > 0) {
			index = getLetztesSchlangengled().getIndex() + 1;
		}
		schlangenglieder.add(new Schlangenglied(index, feld, this));
	}
	
	public void addSchlangenglied(Schlangenglied schlangenglied) {
		schlangenglieder.add(schlangenglied);
	}
	
	public void removeSchlangenglied() {
		schlangenglieder.remove(schlangenglieder.size() - 1);
	}
	
	public List<Schlangenglied> getSchlangenglieder() {
		return schlangenglieder;
	}
	
	public Schlangenglied getLetztesSchlangengled() {
		return schlangenglieder.get(schlangenglieder.size()-1);
	}
	
	public Schlangenart getSchlangenart() {
		return schlangenart;
	}
	
	public int getLaenge() {
		return laenge;
	}
	
	public boolean isVollstaendig() {
		return schlangenglieder.size() == schlangenart.getZeichenkette().length();
	}
}
