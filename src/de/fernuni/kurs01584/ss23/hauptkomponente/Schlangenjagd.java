package de.fernuni.kurs01584.ss23.hauptkomponente;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import de.fernuni.kurs01584.ss23.algorithmus.DschungelGenerator;
import de.fernuni.kurs01584.ss23.algorithmus.LoesungsBewerter;
import de.fernuni.kurs01584.ss23.algorithmus.LoesungsPruefer;
import de.fernuni.kurs01584.ss23.algorithmus.SchlangenSuche;
import de.fernuni.kurs01584.ss23.darstellung.Darstellung;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiLeser;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateiSchreiber;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.XMLParser;
import de.fernuni.kurs01584.ss23.modell.SchlangenjagdModell;

public class Schlangenjagd implements SchlangenjagdAPI {
	private static SchlangenjagdModell schlangenjagdModell;	
	
	private enum AblaufParameter {
		l, e, p, b, d;
	}
	
	public static void main(String[] args) {		
		// Parameter verifizieren
		if(!verifiziereParameter(args)) {
			return;
		}
		String ablauf = args[0].split("=")[1];
		String eingabe = args[1].split("=")[1];
		String ausgabe = "";
		if(args.length == 3) {
			ausgabe = args[2].split("=")[1];
		}
		
		// Versuche Eingabedatei zu laden und gebe gegebenenfalls Fehler aus
		if(!leseSchlangenjagdModell(eingabe)) {
			return;
		}
		
		// Verarbeite Ablaufparameter
		for(int i = 0; i < ablauf.length(); i++) {
			switch(AblaufParameter.valueOf("" + ablauf.charAt(i))) {
				case l:
					// Loesen
					loese();
					if(!schreibeSchlangenjagdModell(ausgabe)) {
						return;
					}
					break;
				case e:
					// Erzeugen
					try {
						erzeuge();
					} catch (Exception e) {
						System.out.println("Fehler: " + e.getMessage());
						return;
					}
					if(!schreibeSchlangenjagdModell(ausgabe)) {
						return;
					}
					break;
				case p:
					pruefe();
					break;
				case b:
					bewerte();
					break;
				case d:
					darstellen();
					break;
			}
		}
	}
	
	private static void loese() {
		SchlangenSuche schlangenSuche = new SchlangenSuche(schlangenjagdModell);
		schlangenSuche.sucheSchlangen();
	}
	
	private static void erzeuge() throws TimeoutException {
		DschungelGenerator dschungelGenerator = new DschungelGenerator(schlangenjagdModell);
		dschungelGenerator.generiereDschungel();
	}
	
	private static void pruefe() {
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehler = loesungsPruefer.pruefe();
		System.out.println("================Fehler==================");
		System.out.println("Glieder: " + fehler.get(Fehlertyp.GLIEDER));
		System.out.println("Zuordnung: " + fehler.get(Fehlertyp.ZUORDNUNG));
		System.out.println("Verwendung: " + fehler.get(Fehlertyp.VERWENDUNG));
		System.out.println("Nachbarschaft: " + fehler.get(Fehlertyp.NACHBARSCHAFT));
		System.out.println();
	}
	
	private static void bewerte() {
		LoesungsBewerter loesungsBewerter = new LoesungsBewerter(schlangenjagdModell);
		System.out.println("===============Bewertung================");
		System.out.println("Punkte: " + loesungsBewerter.bewerte());
		System.out.println();
	}
	
	private static void darstellen() {
		Darstellung darstellung = new Darstellung(schlangenjagdModell);
		darstellung.print();
		System.out.println();
	}
	
	private static boolean schreibeSchlangenjagdModell(String file) {
		try {
			DateiSchreiber schreiber = new DateiSchreiber();
			schreiber.schreibe(schlangenjagdModell, file);
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	private static boolean leseSchlangenjagdModell(String file) {
		try {
			DateiLeser leser = new DateiLeser();
			XMLParser parser = new XMLParser(leser.lese(file));
			schlangenjagdModell = parser.parseSchlangenjagd();
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	private static boolean verifiziereParameter(String[] args) {
		if(args.length > 3) {
			System.out.println("Fehler: Zu viele Parameter angegeben, es kann maximal 'ablauf', 'eingabe' und 'ausgabe' angegeben werden.");
			return false;
		} else if (args.length < 2) {
			System.out.println("Fehler: Zu wenige Parameter angegeben, es muss mindestens 'ablauf' und 'eingabe' angegeben werden.");
			return false;
		}
		String parameterName = args[0].split("=")[0];
		if(!parameterName.equals("ablauf")) {
			System.out.println("Fehler: Parameter 'ablauf' ist nicht an erster Stelle.");
			return false;
		}
		parameterName = args[1].split("=")[0];
		if(!parameterName.equals("eingabe")) {
			System.out.println("Fehler: Parameter 'eingabe' ist nicht an zweiter Stelle.");
			return false;
		}
		if(args.length == 3) {
			parameterName = args[2].split("=")[0];
			if(!parameterName.equals("ausgabe")) {
				System.out.println("Fehler: Parameter 'ausgabe' ist nicht an dritter Stelle.");
				return false;
			}
		}
		String ablaufparameter = args[0].split("=")[1];
		for(int i = 0; i < ablaufparameter.length(); i++) {
			char zeichen = ablaufparameter.charAt(i);
			if(zeichen != 'l' && zeichen != 'e' && zeichen != 'p' && zeichen != 'b' && zeichen != 'd') {
				System.out.println("Fehler: Ablaufparameter '" + zeichen + "' unbekannt, es werden nur 'l', 'e', 'p', 'b' oder 'd' erkannt.");
				return false;
			}
		}
		for(int i = 0; i < ablaufparameter.length(); i++) {
			char zeichen = ablaufparameter.charAt(i);
			if(zeichen == 'l' || zeichen == 'e') {
				if(args.length != 3) {
					System.out.println("Fehler: Fuer die Ablaufparameter 'l' und 'e' muessen "
							+ "die Parameter 'ablauf', 'eingabe' und 'ausgabe' angegeben werden.");
					return false;
				}
			}
		}
		return true;
	}
	
	

	@Override
	public boolean loeseProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {
		if(!leseSchlangenjagdModell(xmlEingabeDatei)) {
			return false;
		}
		loese();
		if(schlangenjagdModell.getSchlangen() == null) {
			return false;
		}
		if(!schreibeSchlangenjagdModell(xmlEingabeDatei)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean erzeugeProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {
		if(!leseSchlangenjagdModell(xmlEingabeDatei)) {
			return false;
		}
		try {
			erzeuge();
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
			return false;
		}
		if(!schreibeSchlangenjagdModell(xmlEingabeDatei)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Fehlertyp> pruefeLoesung(String xmlEingabeDatei) {
		List<Fehlertyp> fehlerListe = new ArrayList<Fehlertyp>();
		if(!leseSchlangenjagdModell(xmlEingabeDatei)) {
			return fehlerListe;
		}
		LoesungsPruefer loesungsPruefer = new LoesungsPruefer(schlangenjagdModell);
		Map<Fehlertyp, Integer> fehlerMap = loesungsPruefer.pruefe();
		for(Fehlertyp fehlerTyp : Fehlertyp.values()) {
			if(fehlerMap.get(fehlerTyp) > 0) {
				fehlerListe.add(fehlerTyp);
			}
		}
		return fehlerListe;
	}

	@Override
	public int bewerteLoesung(String xmlEingabeDatei) {
		LoesungsBewerter loesungsBewerter = new LoesungsBewerter(schlangenjagdModell);
		return loesungsBewerter.bewerte();
	}

	@Override
	public String getName() {
		return "Bugovsky Marcell";
	}

	@Override
	public String getMatrikelnummer() {
		return "3989690";
	}

	@Override
	public String getEmail() {
		return "marcell@bugovsky.at";
	}
}
