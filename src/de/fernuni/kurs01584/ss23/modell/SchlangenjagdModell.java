package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class SchlangenjagdModell {
	private Dschungel dschungel;
	private Schlangenart[] schlangenarten;
	private List<Schlange> schlangen;
	private long vorgabeZeit;
	private long abgabeZeit;
	
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, long vorgabeZeit) {
		this.dschungel = dschungel;
		this.schlangenarten = schlangenarten;
		this.vorgabeZeit = vorgabeZeit;
	}
	
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, List<Schlange> schlangen, long vorgabeZeit, long abgabeZeit) {
		this(dschungel, schlangenarten, vorgabeZeit);
		this.schlangen = schlangen;
		this.abgabeZeit = abgabeZeit;
	}

	public Dschungel getDschungel() {
		return dschungel;
	}

	public Schlangenart[] getSchlangenarten() {
		return schlangenarten;
	}

	public long getVorgabeZeit() {
		return vorgabeZeit;
	}

	public long getAbgabeZeit() {
		return abgabeZeit;
	}
	
	public void setAbgabeZeit(long abgabeZeit) {
		this.abgabeZeit = abgabeZeit;
	}
	
	public List<Schlange> getSchlangen() {
		return schlangen;
	}
	
	public void setSchlangen(List <Schlange> schlangen) {
		this.schlangen = schlangen;
	}

}
