package de.fernuni.kurs01584.ss23.modell;

public class Nachbarschaftsstruktur {
	private int[][] deltas;
	private String typ;
	private int distanz;
	private int[] sprung;
	
	/***
	 * Erzeugt eine Nachbarschaftsstruktur basierend auf dem Parameter Distanz,
	 * Distanz beschreibt hierbei alle Felder, welche waagrecht, senkrecht und diagonal
	 * innerhalb der Distanz sind (ein Feld selber ist nicht in seiner eigenen Nachbarschaft)
	 * @param distanz
	 */
	public Nachbarschaftsstruktur(int distanz) {
		typ = "Distanz";
		this.distanz = distanz;
		// distanz darf nur Werte groesser als 0 sein
		if(distanz <= 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Nachbarschaftsstruktur' darf der Parameter 'distanz' keine "
					+ "Werte kleiner gleich Null annehmen.");
		}
		// mit der Formel ((2n+1)^2)-1 kann die Menge der Felder,
		// welche innerhalb der Nachbarschaftsstruktur sind berechnet werden
		int deltaLength = (2*distanz+1)*(2*distanz+1)-1;
		deltas = new int[deltaLength][2];
		int index = 0;
		// wir iterieren durch die Felder der Nachbarschaftsstruktur und befüllen das delta Array
		for(int x = distanz; x>=-distanz;x--) {
			for(int y = distanz; y>=-distanz;y--) {
				if(x != 0 || y != 0) {
					deltas[index][0] = x;
					deltas[index][1] = y;
					index++;
				}
			}
		}
	}

	/***
	 * Erzeugt eine Nachbarschaftsstruktur basierend auf Spruengen, 
	 * welche aus deltaX und deltaY kombiniert werden koennen
	 * @param deltaX
	 * @param deltaY
	 */
	public Nachbarschaftsstruktur(int deltaX, int deltaY) {
		typ = "Sprung";
		this.sprung = new int[2];
		sprung[0] = deltaX;
		sprung[1] = deltaY;
		// deltaX und deltaY duerfen nur Werte groesser als 0 sein
		if(deltaX < 0 || deltaY < 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Nachbarschaftsstruktur' duerfen die Parameter 'deltaX' und 'deltaY' keine "
					+ "Werte kleiner als annehmen.");
		}
		if(deltaX == 0 && deltaY == 0) {
			throw new IllegalArgumentException(
					"Fuer die Klasse 'Nachbarschaftsstruktur' duerfen nicht beide Parameter 'deltaX' und 'deltaY' keine "
					+ "Null als Wert annehmen.");
		}
		int deltaLength;
		if(deltaX == deltaY || (deltaX == 0 || deltaY == 0)) {
			// Wenn deltaX und deltaY gleich sind bzw eines der beiden null ist haben wir nur 4 Nachbarn 
			deltaLength = 4;
		} else {
			// Wenn deltaX und deltaY nicht gleich und keines der beiden null sind haben wir 8 Nachbarn 
			deltaLength = 8;
		}
		deltas = new int[deltaLength][2];
		
		if(deltaX != 0 && deltaY != 0) {
			int index = 0;
			int x = deltaX;
			// wir iterieren durch die Felder der Nachbarschaftsstruktur und befüllen das delta Array
			while(x>=-deltaX) {
				int y = deltaY;
				while(y>=-deltaY) {
					deltas[index][0] = x;
					deltas[index][1] = y;
					index++;
					// wenn deltaX und deltaY ungleich sind, dann fügen wir ebenso den umgekehrten Wert ein
					// also zB 1,2 und ebenso 2,1
					if(deltaX != deltaY) {
						deltas[index][0] = y;
						deltas[index][1] = x;
						index++;
					}
					y = y - 2*deltaY;
				}
				x = x - 2*deltaX;
			}
		} else {
			int index = 0;
			int x;
			if(deltaX == 0) {
				x = deltaY;
			} else {
				x = deltaX;
			}
			for(int z = x; z > -x; z = -z) {
				deltas[index][0] = 0;
				deltas[index][1] = z;
			}
			for(int z = x; z > -x; z = -z) {
				deltas[index][0] = z;
				deltas[index][1] = 0;
			}
		}
	}
	
	/***
	 * Gibt die Nachbarschaftsstruktur in Form eines Zweidimensionalen Arrays mit Delta Werten zurueck
	 * @return deltas Array
	 */
	public int[][] getDeltas() {
		return deltas;
	}
	
	public String toString() {
		if(typ.equals("Distanz")) {
			return (typ + "(" + distanz + ")");
		} else {
			return (typ + "(" + sprung[0] + "," + sprung[1] + ")");
		}
	}
}
