package de.fernuni.kurs01584.ss23.modell;

public class Schlangenart {
	private String id, zeichenkette;
	private int punkte, anzahl;
	private Nachbarschaftsstruktur nachbarschaftsstruktur;
	
	public Schlangenart(String id, String zeichenkette, int punkte, int anzahl, int distanz) {
		this.id = id;
		this.zeichenkette = zeichenkette;
		this.punkte = punkte;
		this.anzahl = anzahl;
		nachbarschaftsstruktur = new Nachbarschaftsstruktur(distanz);
	}
	
	public Schlangenart(String id, String zeichenkette, int punkte, int anzahl, int deltaX, int deltaY) {
		this.id = id;
		this.zeichenkette = zeichenkette;
		this.punkte = punkte;
		this.anzahl = anzahl;
		nachbarschaftsstruktur = new Nachbarschaftsstruktur(deltaX, deltaY);
	}
	
	public String getId() {
		return id;
	}
	
	public String getZeichenkette() {
		return zeichenkette;
	}
	
	public int getPunkte() {
		return punkte;
	}
	
	public int getAnzahl() {
		return anzahl;
	}
	
	public Nachbarschaftsstruktur getNachbarschaftsstruktur() {
		return nachbarschaftsstruktur;
	}
}
