package de.fernuni.kurs01584.ss23.modell;

public enum Zeiteinheit {
	d(86400000), h(3600000), min(60000), s(1000), ms(1);
	
	private final long multiplicator;
	
	private Zeiteinheit(long multiplicator) {
		this.multiplicator = multiplicator;
	}
	
	public long getMultiplicator() {
		return multiplicator;
	}
}
