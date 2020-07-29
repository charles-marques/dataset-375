package lups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class Database {

	/**
	 * HashMap hält immer eine ID zu einem Veranstaltungsobjekt
	 */
	private HashMap<String, Veranstaltung> veranstaltungen = new HashMap<String, Veranstaltung>();

	/**
	 * HashMap hält immer eine email zu einem User
	 * email muss immer eingeutig sein
	 */
	private HashMap<String, User> userlist = new HashMap<String, User>();

	/**
	 * createVeranstaltung(String,String,String,String,String)
	 * 	erstellt eine Lehrveranstaltung(id,titel,beschreibung,zeit) und weißt ihr einen Lehrenden zu.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @param titel - Titel der Lehrveranstaltung
	 * @param lehrender - Lehrender der Lehrveranstaltung
	 * @param beschreibung - Beschreibung der Lehrveranstaltung
	 * @param zeit - Zeit an dem die Lehrveranstaltung stattfindet
	 */
	public void createVeranstaltung(String id, String titel,
			String lehrender, String beschreibung, String zeit) {
		veranstaltungen.put(id, new Veranstaltung(titel, lehrender, beschreibung, zeit));
	}

	/**
	 * existsVeranstaltung(String) Prüft ob eien Lehrveranstaltung existiert.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @return true wenn Veranstaltung existiert, sonst false.
	 */
	public boolean existsVeranstaltung(String titel) {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		
		while(itr.hasNext()) {
			if (veranstaltungen.get(itr.next()).getTitel().equals(titel)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * deleteVeranstaltung(String) Löscht eine Lehrveranstaltung aus der Datenbank
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 */
	public void deleteVeranstaltung(String id) {
		veranstaltungen.remove(id);
	}
	
	/**
	 * setVeranstaltungsPruefender(String,String) Setzt einen Pruefer zu einer Veranstaltung.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @param pruefender - Name des Prüfenden
	 */
	public void setVeranstaltungsPruefender(String id, String pruefender) {
		veranstaltungen.get(id).setPruefender(pruefender);
	}

	/**
	 * prueferAbmelden(String,String) Pruefer von Veranstaltung X abmelden.
	 * @param email - Eindeutige email des Nutzers
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 */
	public void prueferAbmelden(String email, String id) {
		Iterator<String> itr = veranstaltungen.keySet().iterator();

		while (itr.hasNext()) {
			String aktuellesElement = itr.next();
			if (veranstaltungen.get(aktuellesElement).getPruefender() == email) {
				veranstaltungen.get(aktuellesElement).setPruefender("");
			}
		}
	}

	public void setVeranstaltungsTeilnehmer(String IDfuckPMD,
			HashMap<String, Boolean> teilnehmer) {
		Iterator<String> itr = userlist.keySet().iterator();
		HashMap old = new HashMap();
		
		while (itr.hasNext()) {
			String aktuellesElement = itr.next();
			if (teilnehmer.containsKey(userlist.get(aktuellesElement).getUserDaten().get("name"))) {
				old.put(aktuellesElement, teilnehmer.get(userlist.get(aktuellesElement).getUserDaten().get("name")));
			}
		}
		veranstaltungen.get(IDfuckPMD).getTeilnehmer().putAll(old);
	}

	/**
	 * getTeilnehmerlisteGUI(String,Boolean) Hohlt die Teilnehmerliste der zugelassenen oder angemeldeten Nutzer.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @param flag - Legt fest ob Angemeldete(true) oder Zugelassene(false) Teilnehmer ausgegeben werden.
	 * @return teilnehmerliste - erster Wert name, zweiter flag
	 */
	public Vector<Vector> getTeilnehmerlisteGUI(String id,
			Boolean flag) {
		Iterator<String> itr = veranstaltungen.get(id).getTeilnehmer()
				.keySet().iterator();
		Vector<Vector> teilnehmerliste = new Vector<Vector>();
		
		while (itr.hasNext()) {
			Vector spalten = new Vector();
			String aktuellesElement = itr.next();
			if (veranstaltungen.get(id).getTeilnehmer().get(aktuellesElement) == flag) {
				spalten.add(userlist.get(aktuellesElement).getUserDaten().get("name"));
				spalten.add(flag);
				teilnehmerliste.add(spalten);
				
			}
		}
		return teilnehmerliste;
	}

	/**
	 * getTeilnehmerlistePDF(String) Hohlte die Teilnehmerliste einer Veranstaltung.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @return teilnehmerliste
	 */
	public List<String> getTeilnehmerlistePDF(String id) {
		Iterator<String> itr = veranstaltungen.get(id).getTeilnehmer()
				.keySet().iterator();
		List<String> teilnehmerliste = new ArrayList<String>();

		while (itr.hasNext()) {
			teilnehmerliste.add(userlist.get(itr.next()).getUserDaten().get("name"));
		}
		return teilnehmerliste;
	}

	/**
	 * getPruefenderliste(String) Hohlt die Liste vorhandener Prüfungen. Wo der Prüfer angemeldet oder kein Prüfer eingetragen ist.
	 * @param Pruefender - Name des Pruefenden
	 * @return liste
	 */
	public Vector<Vector<String>> getPruefenderliste(String pruefender) {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();

		while (itr.hasNext()) {
			Vector<String> spalten = new Vector<String>();
			String aktuellesElement = itr.next();
			if (veranstaltungen.get(aktuellesElement).getPruefender()
					.equals(pruefender)) {
				spalten.add(aktuellesElement);
				spalten.add(veranstaltungen.get(aktuellesElement).getTitel());
				spalten.add(pruefender);
				liste.add(spalten);
			} else if (veranstaltungen.get(aktuellesElement).getPruefender()
					.isEmpty()) {
				spalten.add(aktuellesElement);
				spalten.add(veranstaltungen.get(aktuellesElement).getTitel());
				spalten.add("Noch kein Prüfender vorhanden");
				liste.add(spalten);
			}

		}
		return liste;
	}

	/**
	 * getLehrenderliste(String) Hohlt die Liste vorhandener Lehrveranstaltungen in die der Lehrende eingetragen ist.
	 * @param lehrender - Name des Lehrenden
	 * @return liste
	 */
	public Vector<Vector<String>> getLehrenderliste(String lehrender) {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();
		
		while (itr.hasNext()) {
			Vector<String> spalten = new Vector<String>();
			String aktuellesElement = itr.next();
			if (veranstaltungen.get(aktuellesElement).getLehrender().equals(lehrender)){
				spalten.add(aktuellesElement);
				spalten.add(veranstaltungen.get(aktuellesElement).getTitel());
				liste.add(spalten);
			}
		}
		return liste;	
	}
	
	/**
	 * getVorlesungsverzeichnis() Hohlt komplettes Vorlesungsverzeichnis.
	 * @return liste - Liste des Vorlesungsverzeichnisses.
	 */
	public Vector<Vector<String>> getVorlesungsverzeichnis() {
		Iterator<String> itr = veranstaltungen.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();

		if (!veranstaltungen.isEmpty()) {
			while (itr.hasNext()) {
				Vector<String> spalten = new Vector<String>();
				String aktuellesElement = itr.next();
				spalten.add(aktuellesElement);
				spalten.add(veranstaltungen.get(aktuellesElement).getTitel());
				spalten.add(veranstaltungen.get(aktuellesElement).getLehrender());
				liste.add(spalten);
			}
		} else {
			Vector<String> spalten = new Vector<String>();
			spalten.add("Es sind keine Vorlesungen eingetragen.");
			liste.add(spalten);
		}
		return liste;
	}

	/**
	 * getVorlesung(String) Hohlt eine spezielle Vorlesung.
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @return vorlesung
	 */
	public HashMap<String, String> getVorlesung(String id) {
		HashMap<String, String> vorlesung = new HashMap<String, String>();
		vorlesung.put("titel", veranstaltungen.get(id).getTitel());
		vorlesung.put("beschreibung", veranstaltungen.get(id)
				.getBeschreibung());
		vorlesung.put("lehrender", veranstaltungen.get(id)
				.getLehrender());
		vorlesung.put("zeit", veranstaltungen.get(id).getZeit());
		if (veranstaltungen.get(id).getPruefender() != "") {
			vorlesung.put("pruefender", veranstaltungen.get(id)
					.getPruefender());
		}
		return vorlesung;
	}

	public String getVeranstaltungsTitel(String id) {
		return veranstaltungen.get(id).getTitel();

	}

	/**
	 * createUser(HashMap<String,String>,String,int) erstellt einen neuen Nutzer.
	 * @param userDaten - Variable in dem die Nutzerdaten gespeichert werden.
	 * @param passwort - Passwort des Nutzers
	 * @param flag - Nutzergruppe
	 */
	public void createUser(HashMap<String, String> userDaten, String passwort,
			int flag) {
		userlist.put(userDaten.get("email"), new User(userDaten, passwort, flag));
	}

	/**
	 * existsUser(String) Prüft ob Nutzer existiert.
	 * @param email - Eindeutige email des Nutzers
	 * @return true wenn Nutzer vorhanden, sonst false.
	 */
	public boolean existsUser(String email) {
		return userlist.containsKey(email);
	}

	/**
	 * deleteUsr(String) Löscht einen Nutzer.
	 * @param email - Eindeutige email des Nutzers
	 */
	public void deleteUsr(String email) {
		userlist.remove(email);
	}

	/**
	 * getUserList() Hohlt die komplette Nutzerliste.
	 * @return liste
	 */
	public Vector<String> getUserList() {
		Iterator<String> itr = userlist.keySet().iterator();
		Vector<String> liste = new Vector<String>();

		while (itr.hasNext()) {
			liste.add(itr.next());
		}
		return liste;
	}

	/**
	 * setUserDaten(String,HashMap<String,String>) Setze Daten für einen Nutzer.
	 * @param email - Eindeutige email des Nutzers
	 * @param userDaten - Nutzerdaten.
	 */
	public void setUserDaten (String email, HashMap<String, String> userDaten) {
		userlist.get(email).setUserDaten(userDaten);
	}
	
	/**
	 * setPasswort(String,String) Setzt Passwort für einen Nutzer.
	 * @param email - Eindeutige email des Nutzers
	 * @param pass - Passwort des Nutzers
	 */
	public void setPasswort(String email, String pass) {
		userlist.get(email).setPass(pass);
	}

	/**
	 * setSemester(String,int) Setzt das aktuelle Semester des Studenten.
	 * @param email - Eindeutige email des Nutzers
	 * @param semeseter - aktuelles Semester
	 */
	public void setSemester(String email, int semester) {
		User usr = userlist.get(email);
		usr.setSemester(semester);
	}

	/**
	 * setFach(String,String) Setzt Veranstaltung für Nutzer.
	 * @param email - Eindeutige email des Nutzers
	 * @param fach - Fach das eingetragen werden soll
	 */
	public void setFach(String email, String fach) {
		userlist.get(email).setFach(fach);
	}
	
	/**
	 * setNote(String,String,Double) Setzt die Bewertung für Nutzer zu Veranstaltung X.
	 * @param email - Eindeutige email des Nutzers
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @param note - Bewertung
	 */
	public void setNote(String email, String id, Double note) {
		userlist.get(email).getLeistung().put(id, note);
	}

	/**
	 * anmeldenKurse(String,String) Nutzer zur Veranstaltung X anmelden.
	 * @param email - Eindeutige email des Nutzers
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 */
	public void anmeldenKurse(String email, String id) {
		userlist.get(email).getKurse().put(id, false);
		veranstaltungen.get(id).getTeilnehmer().put(email, false);
	}

	/**
	 * abmeldenKurse(String,String) Nutzer von Veranstaltung X abmelden.
	 * @param email - Eindeutige email des Nutzers
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 */
	public void abmeldenKurse(String email, String id) {
		userlist.get(email).getKurse().remove(id);
		veranstaltungen.get(id).getTeilnehmer().remove(email);
	}

	/**
	 * getter für spezifischen user
	 * 
	 */
	
	/**
	 * getName(String) Hohlt den Namen einen Nutzers.
	 * @param email - Eindeutige email des Nutzers
	 * @return Name des Nutzers
	 */
	public String getName(String email) {
		return userlist.get(email).getUserDaten().get("name");
	}
	
	/**
	 * getUserDaten(String) Hohlt Nutzerdaten.
	 * @param email - Eindeutige email des Nutzers
	 * @return Daten des Nutzers (Name,Email,Straße,PLZ,Haus-Nr.,MATNR)
	 */
	public HashMap<String, String> getUserDaten(String email) {
		return userlist.get(email).getUserDaten();
	}
	
	/**
	 * getPasswort(String) Hohlt das Passwort des Nutzers
	 * @param email - Eindeutige email des Nutzers
	 * @return Passwort des Nutzers
	 */
	public String getPasswort(String email) {
		return userlist.get(email).getPass();
	}

	/**
	 * getMatNr(String) Hohlt die Matrikelnr des Nutzers
	 * @param email - Eindeutige email des Nutzers
	 * @return matnr - MatrikelNummer
	 */
	public String getMatNr(String email) {
		return userlist.get(email).getUserDaten().get("matnr");
	}
	
	/**
	 * getSemester(String) Hohlt aktuelles Semester des Nutzers
	 * @param email - Eindeutige email des Nutzers
	 * @return semester - aktuelles Semester
	 */
	public int getSemester(String email) {
		return userlist.get(email).getSemester();
	}

	/**
	 * getFach(String) Hohlt Veranstaltungen des Nutzers.
	 * @param email - Eindeutige email des Nutzers
	 * @return fach - Veranstaltungen
	 */
	public String getFach(String email) {
		return userlist.get(email).getFach();
	}

	/**
	 * getFlag(String) Hohlt die Gruppe des Nutzers.
	 * @param email - Eindeutige email des Nutzers
	 * @return flag - Nutzergruppe
	 */
	public int getFlag(String email) {
		return userlist.get(email).getFlag();
	}

	/**
	 * getNote(String,String) Hohlt die Note zu einer Veranstaltung X für Nutzer Y.
	 * @param email - Eindeutige email des Nutzers
	 * @param id - Identifikationsnummer der Lehrveranstaltung
	 * @return note - Note der Veranstaltung
	 */
	public Double getNote(String email, String id) {
		return userlist.get(email).getLeistung().get(id);
	}

	/**
	 * getLeistungsuebersichtGUI(String) Hohlt die Leistungsübersicht für die Klasse GUI
	 * @param email - Eindeutige email des Nutzers
	 * @return liste - vector fuer gui table-model; bestend aus id und note
	 */
	public Vector<Vector<String>> getLeistungsuebersichtGUI(String email) {
		HashMap<String, Double> noten = userlist.get(email).getLeistung();
		Iterator<String> itr = noten.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();

		while (itr.hasNext()) {
			Vector<String> spalten = new Vector<String>();
			String aktuellesElement = itr.next();
			spalten.add(aktuellesElement);
			
			if (noten.get(aktuellesElement) <= 4.0) {
				spalten.add("Bestanden");
			} else {
				spalten.add("nicht Bestanden");
			}
			spalten.add(noten.get(aktuellesElement).toString());
			liste.add(spalten);
		}
		return liste;
	}

	/**
	 * getLeistungsuebersichtPDF(String) Hohlt Leistungsübersicht für Klasse PDF
	 * @param email - Eindeutige email des Nutzers
	 * @return liste - stringliste fuer den pdfdruck; bestehend aus fach, wertung und note
	 */
	public List<String> getLeistungsuebersichtPDF(String email) {
		HashMap<String, Double> noten = userlist.get(email).getLeistung();
		Iterator<String> itr = noten.keySet().iterator();
		List<String> liste = new ArrayList<String>();

		while (itr.hasNext()) {
			String aktuellesElement = itr.next();
			liste.add(aktuellesElement);
			if (noten.get(aktuellesElement) <= 4.0) {
				liste.add("Bestanden");
			} else {
				liste.add("nicht Bestanden");
			}
			liste.add(noten.get(aktuellesElement).toString());

		}
		return liste;
	}

	/**
	 * getKurslistePDF(String) Hohlt Kursliste für die Klasse PDF
	 * @param email - Eindeutige email des Nutzers
	 * @return liste - stringliste fuer den pdfdruck; bestehnd aus faechernamen
	 */
	public List<String> getKurslistePDF(String email) {
		Iterator<String> itr = userlist.get(email).getKurse().keySet()
				.iterator();
		List<String> liste = new ArrayList<String>();

		while (itr.hasNext()) {
			String aktuellesElement = itr.next();
			if (!userlist.get(email).getLeistung()
					.containsKey(aktuellesElement)) {
				liste.add(veranstaltungen.get(aktuellesElement).getTitel());
			}
		}
		return liste;
	}

	/**
	 * getKurslisteGUI(String) Hohlt Kursliste für die Klasse GUI
	 * @param email - Eindeutige email des Nutzers
	 * @return liste - vector fuer gui table-model; bestehend aus id, titel, lehrender und status
	 */
	public Vector<Vector<String>> getKurslisteGUI(String email) {
		HashMap<String, Boolean> kurse = userlist.get(email).getKurse();
		Iterator<String> itr = kurse.keySet().iterator();
		Vector<Vector<String>> liste = new Vector<Vector<String>>();

		while (itr.hasNext()) {
			Vector<String> spalten = new Vector<String>();
			String aktuellesElement = itr.next(); 
			if (!userlist.get(email).getLeistung()
					.containsKey(aktuellesElement)) {
				spalten.add(aktuellesElement);
				spalten.add(veranstaltungen.get(aktuellesElement).getTitel());
				spalten.add(veranstaltungen.get(aktuellesElement)
						.getLehrender());
				if (kurse.get(aktuellesElement)) {
					spalten.add("zugelassen");
				} else {
					spalten.add("angemeldet");
				}
				liste.add(spalten);
			}
		}
		return liste;
	}

	public String getEmailToName(String name) {
		Iterator<String> itr = userlist.keySet().iterator();

		while (itr.hasNext()) {
			String aktuellesElement = itr.next();
			if (userlist.get(aktuellesElement).getUserDaten().get("name").equals(name)) {
				return userlist.get(aktuellesElement).getUserDaten().get("email");
			}
		}
		return "";
	}
}
