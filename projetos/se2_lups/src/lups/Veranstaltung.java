package lups;

import java.util.HashMap;


public class Veranstaltung {

	/*
	 * Veranstaltung
	 * HashMap teilnehmer, Liste eingeschriebener studenten (zu/an)
	 */
	private String titel;
	
	private String beschreibung;
	
	private String pruefender = "";
	
	private String lehrender;
	
	private String zeit;
	
	private HashMap<String, Boolean> teilnehmer = new HashMap<String, Boolean>();
	
	/**
	 * Veranstaltungskonstruktor
	 * @param titel - Name der veranstaltung
	 * @param lehrender - Lehrperson einer Veranstaltung
	 * @param beschreibung - Worum geht es in der Veranstaltung?
	 * @param zeit - Zeitraum der Veranstaltung
	 */
	public Veranstaltung(String titel, String lehrender, String beschreibung, String zeit) {
		this.titel = titel;
		this.lehrender = lehrender;
		this.beschreibung = beschreibung;
		this.zeit = zeit;
	}
	
	/*
	 * allgemeine setter für veranstaltungen
	 */
	/**
	 *  
	 * @param titel<String>
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	/**
	 * 
	 * @param lehrender<String>
	 */
	public void setLehrender(String lehrender) {
		this.lehrender = lehrender;
	}
	
	/**
	 * 
	 * @param pruefender<String>
	 */
	public void setPruefender(String pruefender) {
		this.pruefender = pruefender;
	}
	
	/**
	 * 
	 * @param zeit<String>
	 */
	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
	
	/**
	 * 
	 * @param beschreibung<String>
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * 
	 * @param HashMap<String, Boolean>  - Boolean 
	 * 											false, wenn angemeldet;
	 * 											true, wenn zugelassen; 
	 */
	public void setTeilnehmer(HashMap<String, Boolean> teilnehmer) {
		this.teilnehmer = teilnehmer;
	}
	
	/*
	 * allgemeine getter für veranstaltungen
	 */
	
	/**
	 * 
	 * @return pruefender<String>
	 */
	public String getPruefender() {
		return this.pruefender;
	}
	
	/**
	 * 
	 * @return zeit<String>
	 */
	public String getZeit() {
		return this.zeit;
	}
	
	/**
	 * 
	 * @return beschreibung<String>
	 */
	public String getBeschreibung() {
		return this.beschreibung;
	}
	
	/**
	 * 
	 * @return titel<String>
	 */
	public String getTitel() {
		return this.titel;
	}
	
	/**
	 * 
	 * @return lehrender<String>
	 */
	public String getLehrender() {
		return this.lehrender;
	}
	
	/**
	 * 
	 * @return HashMap<String, Boolean> Boolean -
	 * 										false, wenn angemeldet
	 * 										true, wenn zugelassen
	 */
	public HashMap<String, Boolean> getTeilnehmer() {
		return this.teilnehmer;
	}
}
