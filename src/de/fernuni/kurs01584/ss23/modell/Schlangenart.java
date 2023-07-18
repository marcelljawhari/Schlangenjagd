package de.fernuni.kurs01584.ss23.modell;

public class Schlangenart {
	private int punkte, anzahl;
	private String id, zeichenkette;
	private Nachbarschaftsstruktur nachbarschaftsstruktur;
	
	/***
	 * Erzeugt eine Schlangenart.
	 * @param id			ID der Schlangenart.
	 * @param zeichenkette	Zeichenkette der Schlangenart.
	 * @param punkte		Punkte die der Loesung pro Schlange dieser Schlangenart gutgeschrieben werden.
	 * @param anzahl		Mindestanzahl der versteckten Schlangen dieser Schlangenart.
	 * @param distanz		Distanz der Nachbarschaftsstruktur.
	 */
	public Schlangenart(String id, String zeichenkette, int punkte, int anzahl, int distanz) {
		this.id = id;
		this.zeichenkette = zeichenkette;
		this.punkte = punkte;
		this.anzahl = anzahl;
		nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
	}

	/***
	 * Erzeugt eine Schlangenart.
	 * @param id			ID der Schlangenart.
	 * @param zeichenkette	Zeichenkette der Schlangenart.
	 * @param punkte		Punkte die der Loesung pro Schlange dieser Schlangenart gutgeschrieben werden.
	 * @param anzahl		Mindestanzahl der versteckten Schlangen dieser Schlangenart.
	 * @param deltaX		Der erste Parameter der Sprung Nachbarschaftsstruktur.
	 * @param deltaY		Der zweite Parameter der Sprung Nachbarschaftsstruktur.
	 */
	public Schlangenart(String id, String zeichenkette, int punkte, int anzahl, int deltaX, int deltaY) {
		this.id = id;
		this.zeichenkette = zeichenkette;
		this.punkte = punkte;
		this.anzahl = anzahl;
		nachbarschaftsstruktur = new Nachbarschaftsstruktur(deltaX, deltaY);
	}
	
	/***
	 * Gibt die Punkte die der Loesung pro Schlange dieser Schlangenart gutgeschrieben werden zurueck.
	 * @return Punkte der Schlangenart.
	 */
	public int getPunkte() {
		return punkte;
	}
	
	/***
	 * Gibt die Mindestanzahl der versteckten Schlangen dieser Schlangenart zurueck.
	 * @return Anzahl der Schlangen dieser Schlangenart.
	 */
	public int getAnzahl() {
		return anzahl;
	}
	
	/***
	 * Gibt die ID der Schlangenart zurueck.
	 * @return ID der Schlangenart.
	 */
	public String getId() {
		return id;
	}
	
	/***
	 * Gibt die Zeichenkette der Schlangenart zurueck.
	 * @return Zeichenkette der Schlangenart.
	 */
	public String getZeichenkette() {
		return zeichenkette;
	}
	
	/***
	 * Gibt die Nachbarschaftsstruktur der Schlangenart zurueck.
	 * @return Nachbarschaftsstruktur der Schlangenart.
	 */
	public Nachbarschaftsstruktur getNachbarschaftsstruktur() {
		return nachbarschaftsstruktur;
	}
}
