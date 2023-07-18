package de.fernuni.kurs01584.ss23.modell;

public class Schlangenglied {
	private int index;
	private Feld feld;
	private Schlange schlange;
	
	/***
	 * Erzeugt ein Schlangenglied.
	 * @param index		Index des Schlangenglieds bezogen auf alle Schlangenglieder der Schlange.
	 * @param feld		Feld welches das Schlangenglied belegen soll.
	 * @param schlange	Schlange zu der das Schlangenglied zugeordnet wird.
	 */
	public Schlangenglied(int index, Feld feld, Schlange schlange) {
		this.index = index;
		this.feld = feld;
		this.schlange = schlange;
	}
	
	/***
	 * Gibt den Index des Schlangenglieds zurueck.
	 * @return Index des Schlangenglieds.
	 */
	public int getIndex() {
		return index;
	}

	/***
	 * Gibt das Feld welches das Schlangenglied belegt zurueck.
	 * @return Feld des Schlangenglieds.
	 */
	public Feld getFeld() {
		return feld;
	}

	/***
	 * Gibt die Schlange zu der das Schlangenglied zugeordnet wurde zurueck.
	 * @return Schlange des Schlangenglieds.
	 */
	public Schlange getSchlange() {
		return schlange;
	}

}
