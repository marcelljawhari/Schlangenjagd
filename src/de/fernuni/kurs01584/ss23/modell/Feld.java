package de.fernuni.kurs01584.ss23.modell;

/***
 * Die Klasse 'Feld' ist Teil des Datenmodells und dient dazu die Felder des Dschungels zu repräsentieren.
 */
public class Feld {
	private int zeile, spalte, punkte, verwendbarkeit;
	private String zeichen;
	private String id;

	/***
	 * Erzeugt ein Feld.
	 * @param id				ID des Feldes.
	 * @param zeichen			Zeichen des Feldes.
	 * @param zeile				Zeile des Feldes im Dschungel.
	 * @param spalte			Spalte des Feldes im Dschungel.
	 * @param punkte			Punkte die durch belegen des Feldes der Lösung gutgeschrieben werden.
	 * @param verwendbarkeit	Wir oft das Feld belegt werden kann.
	 */
	public Feld(String id, String zeichen, int zeile, int spalte, int punkte, int verwendbarkeit) throws IllegalArgumentException {
		if (zeile < 0 || spalte < 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Feld' duerfen die Attribute 'zeile' und 'spalte' keine negativen Werte annehmen.");
		}
		if (punkte < 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Feld' darf das Attribut 'punkte' keinen negativen Wert annehmen.");
		}
		if (verwendbarkeit < 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Feld' darf das Attribut 'verwendbarkeit' keinen negativen Wert annehmen.");
		}
		this.id = id;
		this.zeichen = zeichen;
		this.zeile = zeile;
		this.spalte = spalte;
		this.punkte = punkte;
		this.verwendbarkeit = verwendbarkeit;
	}
	
	/***
	 * Erzeugt ein Feld mit Standardwerten 1 Punkt und 1 Verwendbarkeit.
	 * @param id				ID des Feldes.
	 * @param zeichen			Zeichen des Feldes.
	 * @param zeile				Zeile des Feldes im Dschungel.
	 * @param spalte			Spalte des Feldes im Dschungel.
	 */
	public Feld(String id, String zeichen, int zeile, int spalte) throws IllegalArgumentException {
		this(id, zeichen, zeile, spalte, 1, 1);
	}

	/***
	 * Erzeugt ein leeres Feld. Da das Feld leer sein soll, wird ein Feld erzeugt 
	 * mit dem leeren String als zeichen und jeweils 0 punkte und verwendbarkeit.
	 * @param id				ID des Feldes.
	 * @param zeile				Zeile des Feldes im Dschungel.
	 * @param spalte			Spalte des Feldes im Dschungel.
	 */
	public Feld(String id, int zeile, int spalte) throws IllegalArgumentException {
		this(id, "", zeile, spalte, 0, 0);
	}

	/***
     * Gibt die Zeile an der das Feld innerhalb des Dschungels positioniert ist zurück.
     * @return Zeile des Feldes im Dschungel
     */
	public int getZeile() {
		return zeile;
	}

	/***
     * Gibt die Spalte an der das Feld innerhalb des Dschungels positioniert ist zurück.
     * @return Spalte des Feldes im Dschungel
     */
	public int getSpalte() {
		return spalte;
	}
	
	/***
     * Gibt die Punkte zurück, die der Loesung gutgeschrieben werden, wenn eine Schlange diesem Feld zugeordnet wird.
     * @return Punkte des Feldes
     */
	public int getPunkte() {
		return punkte;
	}

	/***
     * Gibt die Verwendbarkeit des Feldes zurück, dies ist immer ein nichtnegativer ganzzahliger Wert,
     * der vorgibt wie viele Schlange diesem Feld zugeordnet werden dürfen
     * @return Verwendbarkeit des Feldes
     */
	public int getVerwendbarkeit() {
		return verwendbarkeit;
	}

	/***
     * Gibt das Zeichen des Feldes zurück
     * @return Zeichen des Feldes
     */
	public String getZeichen() {
		return zeichen;
	}

	/***
     * Gibt die ID des Feldes zurück
     * @return ID des Feldes
     */
	public String getId() {
		return id;
	}

}