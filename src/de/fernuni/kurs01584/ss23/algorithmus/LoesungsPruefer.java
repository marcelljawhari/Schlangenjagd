package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import de.fernuni.kurs01584.ss23.hauptkomponente.SchlangenjagdAPI.Fehlertyp;
import de.fernuni.kurs01584.ss23.modell.Feld;
import de.fernuni.kurs01584.ss23.modell.Schlange;
import de.fernuni.kurs01584.ss23.modell.Schlangenglied;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;
import de.fernuni.kurs01584.ss23.modell.Nachbarschaftsstruktur;

public class LoesungsPruefer {
	private SchlangenjagdModell schlangenjagdModell;
	
	public LoesungsPruefer(SchlangenjagdModell schlangenjagdModell) {
		this.schlangenjagdModell = schlangenjagdModell;
	}
	
	public Map<Fehlertyp, Integer> pruefe() {
		Map<Fehlertyp, Integer> fehler = new HashMap<Fehlertyp, Integer>();
		fehler.put(Fehlertyp.GLIEDER, pruefeGlieder());
		fehler.put(Fehlertyp.ZUORDNUNG, pruefeZuordnung());
		fehler.put(Fehlertyp.VERWENDUNG, pruefeVerwendung());
		fehler.put(Fehlertyp.NACHBARSCHAFT, pruefeNachbarschaft());
		return fehler;
	}
	
	public int pruefeGlieder() {
		int fehler = 0;
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		if(schlangen != null) {
			for(Schlange schlange : schlangen) {
				if(schlange.getSchlangenglieder().size() != schlange.getSchlangenart().getZeichenkette().length()) {
					fehler++;
				}
			}
		}
		return fehler;
	}
	
	public int pruefeZuordnung() {
		int fehler = 0;
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		if(schlangen != null) {
			for(Schlange schlange : schlangen) {
				String zeichenkette = schlange.getSchlangenart().getZeichenkette();
				List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
				for(Schlangenglied schlangenglied : schlangenglieder) {
					String schlangengliedZeichen = "" + zeichenkette.charAt(schlangenglied.getIndex());
					String feldZeichen = schlangenglied.getFeld().getZeichen();
					if(!schlangengliedZeichen.equals(feldZeichen)) {
						fehler++;
					}
				}
			}
		}
		return fehler;
	}
	
	public int pruefeVerwendung() {
		int fehler = 0;
		int[][] verwendbarkeiten = schlangenjagdModell.getDschungel().getVerwendbarkeiten();
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		if(schlangen != null) {
			for(Schlange schlange : schlangen) {
				List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
				for(Schlangenglied schlangenglied : schlangenglieder) {
					int zeile = schlangenglied.getFeld().getZeile();
					int spalte = schlangenglied.getFeld().getSpalte();
					if(verwendbarkeiten[zeile][spalte] > 0) {
						verwendbarkeiten[zeile][spalte]--;
					} else {
						fehler++;
					}
				}
			}
		}
		return fehler;
	}
	
	public int pruefeNachbarschaft() {
		int fehler = 0;
		List<Schlange> schlangen = schlangenjagdModell.getSchlangen();
		if(schlangen != null) {
			for(Schlange schlange : schlangen) {
				List<Schlangenglied> schlangenglieder = schlange.getSchlangenglieder();
				Schlangenglied vorherigesSchlangenglied = schlangenglieder.get(0);
				for(int schlangengliedIndex = 1; schlangengliedIndex < schlangenglieder.size(); schlangengliedIndex++) {
					Schlangenglied aktuellesSchlangenglied = schlangenglieder.get(schlangengliedIndex);
					if(!isInNachbarschaft(vorherigesSchlangenglied, aktuellesSchlangenglied)) {
						fehler++;
					}
					vorherigesSchlangenglied = aktuellesSchlangenglied;
				}
			}
		}
		return fehler;
	}
	
	private boolean isInNachbarschaft(Schlangenglied vorherigesSchlangenglied, Schlangenglied aktuellesSchlangenglied) {
		Schlange schlange = vorherigesSchlangenglied.getSchlange();
		Nachbarschaftsstruktur nachbarschaftsstruktur = schlange.getSchlangenart().getNachbarschaftsstruktur();
		Feld vorherigesFeld = vorherigesSchlangenglied.getFeld();
		Feld aktuellesFeld = aktuellesSchlangenglied.getFeld();
		int vorherigeZeile = vorherigesFeld.getZeile();
		int vorherigeSpalte = vorherigesFeld.getSpalte();
		int aktuelleZeile = aktuellesFeld.getZeile();
		int aktuelleSpalte = aktuellesFeld.getSpalte();
		int[][] deltas = nachbarschaftsstruktur.getDeltas();
		for(int[] delta : deltas) {
			if(vorherigeZeile + delta[0] == aktuelleZeile && vorherigeSpalte + delta[1] == aktuelleSpalte) {
				return true;
			}
		}
		return false;
	}
	
}
