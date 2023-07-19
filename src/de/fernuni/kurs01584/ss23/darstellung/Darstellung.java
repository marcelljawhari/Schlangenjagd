package de.fernuni.kurs01584.ss23.darstellung;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.*;

public class Darstellung {
	private SchlangenjagdModell schlangenjagdModell;
	
	/***
	 * Erzeugt eine Instanz der Darstellungsklasse.
	 * @param schlangenjagdModell SchlangenjagdModell welches dargestellt werden soll.
	 */
	public Darstellung (SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	/***
	 * Stellt die Probleminstanz plus eine eventuelle Loesung in der Konsole dar.
	 * Zuerst die Probleminstanz, Dschungelinformationen, Dschungel, und die Schlangenarten.
	 * Und als letztes die eventuell gefundenen Schlangen.
	 */
	public void print() {
		System.out.println("=====Darstellung der Probleminstanz=====");
		printDschungelInformationen();
		printDschungel();
		printSchlangenarten();
		printLoesung();
	}
	
	/***
	 * Stellt, falls vorhanden, die gefundenen Schlangen dar. 
	 */
	private void printLoesung() {
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		if(schlangen != null) {
			if(schlangen.size() > 0) {
				System.out.println();
				System.out.println("===============Schlangen================");
				int i = 0;
				Schlange schlange = schlangen.get(i);
				System.out.println((i+1) + ". Schlange");
				printSchlangenart(schlange.getSchlangenart());
				printPositionImDschungel(schlange);
				for(int j = i+1; j < schlangen.size(); j++) {
					System.out.println();
					schlange = schlangen.get(j);
					System.out.println((j+1) + ". Schlange");
					printSchlangenart(schlange.getSchlangenart());
					printPositionImDschungel(schlange);
				}
			}
		}
	}
	
	/***
	 * Stellt die Position der angegebenen Schlange innerhalb des Dschungels dar, 
	 * indem der ganze Dschungel ausgegeben wird, wobei nur die Felder der Schlange
	 * die echten Zeichen sind, die restlichen Zeichen werden als Punkt dargestellt.
	 * @param schlange
	 */
	private void printPositionImDschungel(Schlange schlange) {
		System.out.println("Position im Dschungel:");
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		Feld[][] felder = dschungel.getFelder();
		for(int zeile = 0; zeile < dschungel.getZeilen(); zeile++) {
			for(int spalte = 0; spalte < dschungel.getSpalten(); spalte++) {
				Feld feld = felder[zeile][spalte];
				if(schlange.belegtFeld(feld)) {
					System.out.print(feld.getZeichen());
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	/***
	 * Stellt die Dschungelinformationen Zeichenmenge, Zeilen und Spalten dar.
	 */
	private void printDschungelInformationen() {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		System.out.println();
		System.out.println("=========Dschungelinformationen=========");
		System.out.println("Zeichenmenge: " + dschungel.getZeichenmenge());
		System.out.println("Anzahl Zeilen: " + dschungel.getZeilen());
		System.out.println("Anzahl Spalten: " + dschungel.getSpalten());
	}
	
	/***
	 * Stellt den Dschungel dar. Dabei wird nacheinander jeweils ein Dschungel mit
	 * den Zeichen, den Verwendbarkeiten und den Punkten ausgegeben.
	 */
	private void printDschungel() {
		printZeichen();
		printVerwendbarkeiten();
		printPunkte();
	}
	
	/***
	 * Stellt den Dschungel mit den Zeichen der Felder dar.
	 */
	private void printZeichen() {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		System.out.println();
		System.out.println("=========Zeichen des Dschungels=========");
		Feld[][] felder = dschungel.getFelder();
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld.getZeichen().equals("")) {
					System.out.print(" ");
				} else {
					System.out.print(feld.getZeichen());
				}
			}
			System.out.println();
		}
	}

	/***
	 * Stellt den Dschungel mit den Verwendbarkeiten der Felder dar.
	 */
	private void printVerwendbarkeiten() {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		System.out.println();
		System.out.println("====Verwendbarkeiten des Dschungels=====");
		Feld[][] felder = dschungel.getFelder();
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld.getZeichen().equals("")) {
					System.out.print(" ");
				} else {
					System.out.print(feld.getVerwendbarkeit());
				}
			}
			System.out.println();
		}
	}

	/***
	 * Stellt den Dschungel mit den Punktwerten der Felder dar.
	 */
	private void printPunkte() {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		System.out.println();
		System.out.println("=========Punkte des Dschungels==========");
		Feld[][] felder = dschungel.getFelder();
		for(Feld[] zeile : felder) {
			for(Feld feld : zeile) {
				if(feld.getZeichen().equals("")) {
					System.out.print(" ");
				} else {
					System.out.print(feld.getPunkte());
				}
			}
			System.out.println();
		}
	}
	
	/***
	 * Stellt alle Schlangenarten dar.
	 */
	private void printSchlangenarten() {
		Schlangenart[] schlangenarten = schlangenjagdModell.getSchlangenarten();
		System.out.println();
		System.out.println("=============Schlangenarten=============");
		for(Schlangenart schlangenart : schlangenarten) {
			printSchlangenart(schlangenart);
		}
	}
	
	/***
	 * Stellt eine Schlangenart dar, wobei ID, Zeichenkette und Nachbarschaftsstruktur ausgegeben werden.
	 * @param schlangenart
	 */
	private void printSchlangenart(Schlangenart schlangenart) {
		System.out.println("SchlangenartID: " + schlangenart.getId());
		System.out.println("Zeichenkette: " + schlangenart.getZeichenkette());
		System.out.println("Nachbarschaftsstruktur: " + schlangenart.getNachbarschaftsstruktur().toString());
	}
	
}
