package de.fernuni.kurs01584.ss23.darstellung;

import java.util.List;

import de.fernuni.kurs01584.ss23.modell.*;

public class Darstellung {
	private SchlangenjagdModell schlangenjagdModell;
	
	public Darstellung (SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	public void print() {
		System.out.println("=====Darstellung der Probleminstanz=====");
		printDschungelInformationen();
		printDschungel();
		printSchlangenarten();
		printLoesung();
	}
	
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
	
	private void printDschungelInformationen() {
		Dschungel dschungel = schlangenjagdModell.getDschungel();
		System.out.println();
		System.out.println("=========Dschungelinformationen=========");
		System.out.println("Zeichenmenge: " + dschungel.getZeichenmenge());
		System.out.println("Anzahl Zeilen: " + dschungel.getZeilen());
		System.out.println("Anzahl Spalten: " + dschungel.getSpalten());
	}
	
	private void printDschungel() {
		printZeichen();
		printVerwendbarkeiten();
		printPunkte();
	}
	
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
	
	private void printSchlangenarten() {
		Schlangenart[] schlangenarten = schlangenjagdModell.getSchlangenarten();
		System.out.println();
		System.out.println("=============Schlangenarten=============");
		for(Schlangenart schlangenart : schlangenarten) {
			printSchlangenart(schlangenart);
		}
	}
	
	private void printSchlangenart(Schlangenart schlangenart) {
		System.out.println("SchlangenartID: " + schlangenart.getId());
		System.out.println("Zeichenkette: " + schlangenart.getZeichenkette());
		System.out.println("Nachbarschaftsstruktur: " + schlangenart.getNachbarschaftsstruktur().toString());
	}
	
}
