package de.fernuni.kurs01584.ss23.modell;

public class Nachbarschaftsstruktur {
	private int[][] deltas;
	private String typ;
	private int[] parameter;
	
	private final static String DISTANZ = "Distanz";
	private final static String SPRUNG = "Sprung";
	
	/***
	 * Erzeugt eine Nachbarschaftsstruktur des Typs Distanz(x).
	 * @param distanz Distanz ist der maximale Abstand den ein Feld waagrecht, senkrecht oder diagonal zu seinen Nachbar haben kann.
	 */
	public Nachbarschaftsstruktur(int distanz) {
		typ = DISTANZ;
		parameter = new int[1];
		parameter[0] = distanz;
		// distanz darf nur Werte groesser als 0 annehmen
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
	 * Erzeugt eine Nachbarschaftsstruktur von Typ Sprung(x,y).
	 * @param deltaX Der erste Delta Wert des Sprunges.
	 * @param deltaY Der zweite Delta Wert des Sprunges.
	 */
	public Nachbarschaftsstruktur(int deltaX, int deltaY) {
		typ = SPRUNG;
		
		parameter = new int[2];
		parameter[0] = deltaX;
		parameter[1] = deltaY;
		// deltaX und deltaY duerfen nur Werte groesser als 0 annehmen
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
			int z = x;
			for(int i = 0; i < 2; i++) {
				deltas[index][0] = 0;
				deltas[index][1] = z;
				index++;
				z = -z;
			}
			z = x;
			for(int i = 0; i < 2; i++) {
				deltas[index][0] = z;
				deltas[index][1] = 0;
				index++;
				z = -z;
			}
		}
	}
	
	/***
	 * Gibt die Nachbarschaftsstruktur in Form eines Zweidimensionalen Arrays mit Delta Werten fuer jeden Nachbar zurueck.
	 * @return Zweidimensionales int-Array zur Darstellung der Deltas.
	 */
	public int[][] getDeltas() {
		return deltas;
	}
	
	/***
	 * Gibt den Typ der Nachbarschaftsstruktur als String zurueck.
	 * @return Typ der Nachbarschaftsstruktur, Sprung oder Distanz.
	 */
	public String getTyp() {
		return typ;
	}
	
	/***
	 * Gibt die Parameter die bei der Eingabe entgegen genommen wurden als int-Array zurueck.
	 * Bei Distanz ist dies nur ein int Wert, und bei Sprung sind zwei int Werte zu erwarten.
	 * @return Parameter der Eingabe.
	 */
	public int[] getParameter() {
		return parameter;
	}
	
	/***
	 * Gibt die Nachbarschaftsstruktur als String zurueck.
	 * Dies ist in der Form DISTANZ(x) oder SPRUNG(x,y) wobei x und y die eingegebenen Parameter annehmen.
	 * @return Nachbarschaftsstruktur als String.
	 */
	public String toString() {
		if(typ.equals(DISTANZ)) {
			return (typ + "(" + parameter[0] + ")");
		} else {
			return (typ + "(" + parameter[0] + "," + parameter[1] + ")");
		}
	}
}
