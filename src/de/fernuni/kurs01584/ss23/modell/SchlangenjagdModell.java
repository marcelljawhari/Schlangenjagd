package de.fernuni.kurs01584.ss23.modell;

public class SchlangenjagdModell {
	private Dschungel dschungel;
	private Schlangenart[] schlangenarten;
	private long zeitVorgabe;
	
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, long zeitVorgabe) {
		this.dschungel = dschungel;
		this.schlangenarten = schlangenarten;
		this.zeitVorgabe = zeitVorgabe;
	}

	public Dschungel getDschungel() {
		return dschungel;
	}

	public Schlangenart[] getSchlangenarten() {
		return schlangenarten;
	}

	public long getZeitVorgabe() {
		return zeitVorgabe;
	}

}
