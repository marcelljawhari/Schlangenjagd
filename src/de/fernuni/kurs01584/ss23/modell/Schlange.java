package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class Schlange {
	Schlangenart schlangenart;
	
	List<Schlangenglied> schlangenglieder;
	
	public Schlange(Schlangenart schlangenart) {
		this.schlangenart = schlangenart;
		schlangenglieder = new ArrayList<Schlangenglied>();
	}
	
	public void addSchlangenglied(int index, Feld feld) {
		schlangenglieder.add(new Schlangenglied(index, feld, this));
	}
	
	public void removeSchlangenglied() {
		schlangenglieder.remove(schlangenglieder.size() - 1);
	}
	
	public List<Schlangenglied> getSchlangenglieder() {
		return schlangenglieder;
	}
	
	public boolean isVollstaendig() {
		if(schlangenglieder.size() < schlangenart.getZeichenkette().length()) {
			return false;
		} else {
			return true;
		}
	}
}
