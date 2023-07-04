package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class SchlangenjagdModell {
	private Dschungel dschungel;
	private Schlangenart[] schlangenarten;
	private List<Schlange> schlangen;
	private long zeitVorgabe;
	private long zeitAbgabe;
	
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, long zeitVorgabe) {
		this.dschungel = dschungel;
		this.schlangenarten = schlangenarten;
		this.zeitVorgabe = zeitVorgabe;
	}
	
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, List<Schlange> schlangen, long zeitVorgabe, long zeitAbgabe) {
		this(dschungel, schlangenarten, zeitVorgabe);
		this.schlangen = schlangen;
		this.zeitAbgabe = zeitAbgabe; 
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
	
	public List<Schlange> getSchlangen() {
		return schlangen;
	}
	
	public void setSchlangen(List <Schlange> schlangen) {
		this.schlangen = schlangen;
	}

}
