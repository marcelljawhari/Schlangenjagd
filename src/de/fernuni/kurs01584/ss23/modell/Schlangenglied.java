package de.fernuni.kurs01584.ss23.modell;

public class Schlangenglied {
	private int index;
	private Feld feld;
	private Schlange schlange;
	
	public Schlangenglied(int index, Feld feld, Schlange schlange) {
		this.index = index;
		this.feld = feld;
		this.schlange = schlange;
	}

	public int getIndex() {
		return index;
	}

	public Feld getFeld() {
		return feld;
	}

	public Schlange getSchlange() {
		return schlange;
	}

}
