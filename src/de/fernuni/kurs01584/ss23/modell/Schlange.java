package de.fernuni.kurs01584.ss23.modell;

import java.util.List;
import java.util.ArrayList;

public class Schlange {
	int laenge;
	Schlangenart schlangenart;
	List<Schlangenglied> schlangenglieder;
	
	/***
	 * Erzeugt eine Schlange.
	 * @param schlangenart Schlangenart der Schlange.
	 */
	public Schlange(Schlangenart schlangenart) {
		this.schlangenart = schlangenart;
		schlangenglieder = new ArrayList<Schlangenglied>();
		laenge = schlangenart.getZeichenkette().length();
	}
	
	/***
	 * Gibt die momentane Laenge der Schlange zurueck.
	 * @return Laenge der Schlange.
	 */
	public int getLaenge() {
		return laenge;
	}
	
	/***
	 * Gibt die Schlangenart der Schlange zurueck.
	 * @return Schlangenart der Schlange.
	 */
	public Schlangenart getSchlangenart() {
		return schlangenart;
	}
	
	/***
	 * Gibt eine Liste aller Schlangenglieder der Schlange zurueck.
	 * @return Liste aller Schlangenglieder.
	 */
	public List<Schlangenglied> getSchlangenglieder() {
		return schlangenglieder;
	}
	
	/***
	 * Gibt das zuletzt platzierte Schlangenglied zurueck.
	 * @return Letztes Schlangenglied.
	 */
	public Schlangenglied getLetztesSchlangengled() {
		return schlangenglieder.get(schlangenglieder.size()-1);
	}
	
	/***
	 * Erzeugt ein Schlangenglied, welches das angegebene Feld belegt
	 * und fuegt es der Schlange hinzu.
	 * @param feld Feld welches von dem neuen Schlangenglied belegt werden soll.
	 */
	public void addSchlangenglied(Feld feld) {
		int index = 0;
		if(schlangenglieder.size() > 0) {
			index = getLetztesSchlangengled().getIndex() + 1;
		}
		schlangenglieder.add(new Schlangenglied(index, feld, this));
	}
	
	/***
	 * Fuegt der Schlange ein bereits bestehendes Schlangenglied hinzu.
	 * @param schlangenglied Schlangenglied welches hinzugefuegt werden soll.
	 */
	public void addSchlangenglied(Schlangenglied schlangenglied) {
		schlangenglieder.add(schlangenglied);
	}
	
	/***
	 * Entfernt das zuletzt hinzugefuegte Schlangenglied.
	 */
	public void removeSchlangenglied() {
		schlangenglieder.remove(schlangenglieder.size() - 1);
	}
	
	/***
	 * Prueft ob die Schlange das angegebene Feld mit einem Schlangenglied belegt.
	 * @param 	feld Feld welches ueberprueft werden soll.
	 * @return	<ttt>true</ttt> wenn das Feld von der Schlange belegt wird, andernfalls <ttt>false</ttt>.
	 */
	public boolean belegtFeld(Feld feld) {
		for(Schlangenglied schlangenglied : schlangenglieder) {
			if(schlangenglied.getFeld().equals(feld)) {
				return true;
			}
		}
		return false;
	}
	
	/***
	 * Prueft ob die Schlange bezueglich der Schlangenart vollstaendig ist.
	 * @return <ttt>true</ttt> falls vollstaendig, andernfalls <ttt>false</ttt>.
	 */
	public boolean isVollstaendig() {
		return schlangenglieder.size() == schlangenart.getZeichenkette().length();
	}
}
