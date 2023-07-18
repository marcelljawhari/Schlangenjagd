package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class SchlangenjagdModell {
	private long vorgabeZeit;
	private long abgabeZeit;
	private Dschungel dschungel;
	private Schlangenart[] schlangenarten;
	private List<Schlange> schlangen;
	
	/***
	 * Erzeuge ein SchlangenjagdModell basierend auf einer Probleminstanz.
	 * @param dschungel			Dschungel fuer die Schlangenjagd.
	 * @param schlangenarten	Schlangenarten die im Dschungel versteckt sind.
	 * @param vorgabeZeit		Zeitvorgabe fuer die Loesung.
	 */
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, long vorgabeZeit) {
		this.dschungel = dschungel;
		this.schlangenarten = schlangenarten;
		this.vorgabeZeit = vorgabeZeit;
	}
	
	/***
	 * Erzeuge ein SchlangenjagdModell basierend auf einer Loesung.
	 * @param dschungel			Dschungel fuer die Schlangenjagd.
	 * @param schlangenarten	Schlangenarten die im Dschungel versteckt sind.
	 * @param schlangen			Gefundene Schlangen.
	 * @param vorgabeZeit		Zeitvorgabe fuer die Loesung.
	 * @param abgabeZeit		Abgabezeit der Loesung.
	 */
	public SchlangenjagdModell (Dschungel dschungel, Schlangenart[] schlangenarten, List<Schlange> schlangen, long vorgabeZeit, long abgabeZeit) {
		this(dschungel, schlangenarten, vorgabeZeit);
		this.schlangen = schlangen;
		this.abgabeZeit = abgabeZeit;
	}

	/***
	 * Gibt die Zeitvorgabe fuer die Loesung zurueck.
	 * @return Zeitvorgabe fuer die Loesung.
	 */
	public long getVorgabeZeit() {
		return vorgabeZeit;
	}

	/***
	 * Gibt die tatsaechliche Abgabezeit der Loesung zurueck.
	 * @return Abgabezeit der Loesung.
	 */
	public long getAbgabeZeit() {
		return abgabeZeit;
	}

	/***
	 * Gibt den Dschungel des Modells zurueck.
	 * @return Dschungel des Modells.
	 */
	public Dschungel getDschungel() {
		return dschungel;
	}

	/***
	 * Gibt die Schlangenarten die im Dschungel versteckt sind zurueck.
	 * @return Schlangenarten des Dschungels.
	 */
	public Schlangenart[] getSchlangenarten() {
		return schlangenarten;
	}
	
	/***
	 * Speichert die Abgabezeit der Loesung im Modell.
	 * @param abgabeZeit Zeit die fuer die Loesung benoetigt wurde.
	 */
	public void setAbgabeZeit(long abgabeZeit) {
		this.abgabeZeit = abgabeZeit;
	}
	
	/***
	 * Gibt die gefundenen Schlangen der Loesung zurueck.
	 * @return Gefundene Schlangen.
	 */
	public List<Schlange> getSchlangen() {
		return schlangen;
	}
	
	/***
	 * Speichert die im Dschungel gefundenen Schlangen im Modell.
	 * @param schlangen Schlangen die im Dschungel gefunden wurden.
	 */
	public void setSchlangen(List <Schlange> schlangen) {
		this.schlangen = schlangen;
	}

}
