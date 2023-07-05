package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.jdom2.JDOMException;

class DateiLeserTest {
	
	@Test
	void testeKonstruktorVollstaendigeProbleminstanzSollteKeineExceptionWerfen() {
		String file = "./res/sj_p1_probleminstanz.xml";
		DateiLeser leser = new DateiLeser();
		
		assertDoesNotThrow(() -> leser.lese(file),
				() -> "Fuer das Vollstaendige File '" + file + "' wird eine Ausnahme erzeugt.");
	}
	
	@Test
	void testeKonstruktorFehlerhafteProbleminstanzSollteExceptionWerfen() {
		String file = "./res/sj_t1_DTD_fehlerhaft.xml";
		DateiLeser leser = new DateiLeser();
		
		assertThrows(JDOMException.class, () -> leser.lese(file),
				() -> "Fuer das Fehlerhafte File '" + file + "' wird keine Ausnahme erzeugt.");
	}

}
