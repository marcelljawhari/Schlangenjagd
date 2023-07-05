package de.fernuni.kurs01584.ss23.modell;

public class Feld {
	private String id;
	private int zeile, spalte, punkte, verwendbarkeit;
	private String zeichen;

	/***
	 * Erzeugt eine Instanz der Klasse 'Feld' mit den angegebenen Parametern
	 * @param id
	 * @param zeichen
	 * @param zeile
	 * @param spalte
	 * @param punkte
	 * @param verwendbarkeit
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
	 * Erzeugt eine Instanz der Klasse 'Feld' mit den angegebenen Parametern und 1 für Punkte und Verwendbarkeit
	 * @param id
	 * @param zeichen
	 * @param zeile
	 * @param spalte
	 */
	public Feld(String id, String zeichen, int zeile, int spalte) {
		this(id, zeichen, zeile, spalte, 1, 1);
	}
	
	/***
	 * Erzeugt eine Instanz der Klasse 'Feld' an der angegebenen Position mit 0 für Punkte und Verwendbarkeit, 
	 * zeichen bleibt hierbei null
	 * @param id
	 * @param zeile
	 * @param spalte
	 */
	public Feld(String id, int zeile, int spalte) {
		this(id, "", zeile, spalte, 0, 0);
	}

	/***
     * Gibt die Zeile an der das Feld innerhalb des Dschungels positioniert ist zurück.
     * @return Zeile des Feldes
     */
	public int getZeile() {
		return zeile;
	}

	/***
     * Gibt die Spalte an der das Feld innerhalb des Dschungels positioniert ist zurück.
     * @return Spalte des Feldes
     */
	public int getSpalte() {
		return spalte;
	}
	
	/***
     * Gibt die Punkte zurück, die der Loesung angerechnet werden, wenn eine Schlange diesem Feld
     * zugeordnet wird, dies ist immer ein nichtnegativer ganzzahliger Wert
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