package lups;

/**
 * Klasse Controller des Projekts
 * @author SE2_TEAM08, Matti Hartfiel
 * @version 1.2
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;
import java.io.*;

import gui.MainFrame;

//import pdf.PDF;
//import project.*;

public class Controller {
	static String user;
	static String kuser;
	static String mail;
	static long ido;

	private static Database datenbank = new Database();
	
	private static final Logger LOG = Logger.getLogger("lups.Controller");

	/**
	 * Konstruktor
	 */
	public Controller() {
		try {
			FileHandler handler = new FileHandler("Logging.log", true);
			Formatter klartext = new SimpleFormatter();
			handler.setFormatter(klartext);
			LOG.addHandler(handler);
		} catch (IOException ex) {
			LOG.warning("Error: "+ex);
		}
		
		LOG.setLevel(Level.ALL);
		LOG.info("erzeuge Objekt der Klasse Controller.");
		HashMap<String,String> admin1 = new HashMap<String,String>();
		HashMap<String,String> pruefer1 = new HashMap<String,String>();
		HashMap<String,String> student1 = new HashMap<String,String>();
		HashMap<String,String> x = new HashMap<String,String>();
		admin1.put("email","admin");
		admin1.put("name","hans peter");
		pruefer1.put("email", "pruefer");
		pruefer1.put("name","klaus");
		student1.put("name","karsten");
		student1.put("email", "student");
		createUser(admin1,0);
		createUser(pruefer1,1);
		createUser(student1,3);
		this.mail="pruefer";
		x.put("titel", "asds");
		x.put("beschreibung", "asdfdsafads");
		x.put("lehrender",this.mail);
		x.put("zeit", "Anno 1400");
		this.createVeranstaltung(x);

	}

	/**
 	* logIn() authentifizierung des Nutzers.
 	* @param email - Benutzername
 	* @param password - ist das Password (eingabe vom Nutzer)
 	* @return 0 - 4  für art des nutzers(1-3) oder nicht vorhanden(4)
 	*/
	public int logIn(String email, String password) {
    	LOG.entering(this.getClass().getName(), "logIn",new Object[]{email, password});
    	LOG.info("Authentifizierung des Nutzers: "+email);
		try {
			String sha1pwd = sha1(password);
			if (datenbank.existsUser(email)) {
				if(	sha1pwd.equals(datenbank.getPasswort(email))) { 
			
					user = datenbank.getName(email);
					int ID = datenbank.getFlag(email);
					
					switch(ID){
						case 0:	this.kuser = "Admin";
								break;
						case 1:	this.kuser = "Prüfer";
								break;
						case 2:	this.kuser = "Lehrender";
								break;
						case 3:	this.kuser = "Student";
								break;
						}
					this.mail = email;
					LOG.info("Authentifizierung erfolgreich! Angemeldeter Nutzer: "+mail+", "+kuser);
					LOG.exiting(this.getClass().getName(), "logIn", ID);
					return ID;
				}
			}
		} catch(NoSuchAlgorithmException e) {
			LOG.warning("Error: "+e);
		}
		LOG.warning("Fehlerhafter Login.");
		LOG.exiting(this.getClass().getName(), "logIn", 4);
		return 4;
	}

	/**
 	* logOut() Meldet den angemeldeten Nutzer vom System ab.
 	*/
	public void logOut() {
		LOG.entering(this.getClass().getName(), "logOut");
		user = "user";
		kuser = "user";
		mail = "mail";
		LOG.info(mail+" hat sich abgemeldet.");
		LOG.exiting(this.getClass().getName(), "logOut");
	}

	/**
 	* setBewertung(String,String,String) Speichert eine Note für die jeweilige Lehrveranstaltung
 	* @param email - email des Nutzers
 	* @param id - ID der Lehrveranstaltung
 	* @param note - Die Bewertung der Lehrveranstaltung
 	*/
	public void setBewertung(String name, String id, String note) {
		LOG.entering(this.getClass().getName(), "setBewertung", new Object[] {name, id, note});
		String email = datenbank.getEmailToName(name);
		if (!(email == "")) {
			datenbank.setNote(email ,id,Double.parseDouble(note));
		}
		LOG.info("Für "+name+" wurde für die Veranstaltung "+id+" die Bewertung "+note+" eingetragen.");
		LOG.exiting(this.getClass().getName(), "setBewertung");
	}
	
	/**
	 * getBewertung(String,String) Hohlt die Bewertung eines Nutzers X zu einer Veranstaltung Y.
	 * @param email - email des Nutzers
	 * @param id - ID der Lehrveranstaltung
	 * @return note - Bewertung der Lehrveranstaltung
	 */
	public String getBewertung(String name, String id) {
		LOG.entering(this.getClass().getName(), "getBewertung", new Object[] {name, id});
		LOG.info("Lade Bewertung von "+name+" für "+id+".");
		String email = datenbank.getEmailToName(name);
		
		if (!(email == "")) {
			LOG.exiting(this.getClass().getName(), "getBewertung", String.valueOf(datenbank.getNote(email,id)));	
			return String.valueOf(datenbank.getNote(email,id));
		} else {
			LOG.exiting(this.getClass().getName(), "getBewertung", String.valueOf(0));
			return String.valueOf(0);	
			}

	}
	
	/**
 	* changeUserData(HashMap<String,String>) ändert/setzt die Nutzerdaten.
 	* @param data - HashMap von zu ändernden Daten
 	*/
	public void changeUserData(HashMap<String, String> userDaten) {
		LOG.entering(this.getClass().getName(), "changeUserData", userDaten);
		LOG.info("Setzte neue Nutzerdaten für "+mail+". Neue Daten: "+userDaten);
		datenbank.setUserDaten(mail, userDaten);
		LOG.exiting(this.getClass().getName(), "changeUserData");
	}

	/**
 	* getPerson() lädt Daten des angemeldeten Nutzers aus der Datenbank und gibt sie zurück.
 	* @return userdaten - Nutzerdaten
 	*/
	public HashMap<String,String> getPerson() {
		LOG.entering(this.getClass().getName(), "getPerson");
		HashMap<String,String> userdaten = new HashMap<String,String>();
		userdaten.putAll(datenbank.getUserDaten(mail));
		userdaten.put("flag",kuser);
		LOG.info("Lade Nutzerdaten von "+mail+". Daten: "+userdaten);
		LOG.exiting(this.getClass().getName(), "getPerson", userdaten);
		return userdaten;
	}

	/**
 	* anmeldenZuVeranstaltung(String) zu Lehrveranstaltung anmelden.
 	* @param id - Identifikationsnummer der Lehrveranstaltung
 	*/
	public void anmeldenZuVeranstaltung(String id) {
		LOG.entering(this.getClass().getName(), "anmeldenZuVeranstaltung", id);
		datenbank.anmeldenKurse(mail,id);
		LOG.info(mail+" hat sich zu der Lehrveranstaltung "+id+" angemeldet.");
		LOG.exiting(this.getClass().getName(), "anmeldungZuVeranstaltung");
	}

	/**
 	* abmeldenVonVeranstaltung(String) von Lehrveranstaltung abmelden.
 	* @param id - Identifikationsnummer der Lehrveranstaltung
 	*/
	public void abmeldenVonVeranstaltung(String id) {
		LOG.entering(this.getClass().getName(), "abmeldenVonVeranstaltung",id);
		datenbank.abmeldenKurse(mail, id);
		LOG.info(mail+" hat sich von der Lehrveranstaltung "+id+" abgemeldet.");
		LOG.exiting(this.getClass().getName(), "abmeldenVonVeranstaltung");
	}

	/**
 	* setPasswd(String,String) setzt ein neues Passwort
 	* @param alt - altes Passwort als String
 	* @param neu - neues Passwort als String
 	* @return true wenn altes mit gespeicherten übereinstimmt, sonst false
 	*/
	public boolean setPasswd(String alt,String neu) {
		LOG.entering(this.getClass().getName(), "setPasswd", new Object[] {alt,neu});
		String alt1;
		try {
			alt1 = sha1(alt);
			if(alt1.equals(datenbank.getPasswort(mail))) {
				String neu1 = sha1(neu);
				datenbank.setPasswort(mail,neu1);
				LOG.info("Neues Passwort für "+mail+" gespeichert");
				LOG.exiting(this.getClass().getName(), "setPasswd", true);
				return true;
			}
		} catch (NoSuchAlgorithmException ex) {
			LOG.warning("Error: "+ex);
		}
		LOG.warning("Passwort konnte für "+mail+" nicht geändert werden.");
		LOG.exiting(this.getClass().getName(), "setPasswd", false);
		return false;	
	}

	/**
 	* resetPasswd(String,String) setzt das Passwort eines Nutzers auf default zurück.
 	* @param email - email des Nutzers
 	* @param pass - Passwort das gespeichet werden soll
 	*/
	public void resetPasswd(String email,String pass) {
		LOG.entering(this.getClass().getName(), "resetPasswd",new Object[] {email,pass});
		try	{
			String pwd = sha1(pass);
			datenbank.setPasswort(email,pwd);
			LOG.info("Passwort von Nutzer: "+email+" wurde zurückgesetzt.");
		} catch(NoSuchAlgorithmException e) {
			LOG.warning("Error: "+e);
		}
		LOG.exiting(this.getClass().getName(), "resetPasswd");
	}

	/**
 	* teilnehmerZulassen(String,Vector<Vectro<String>>) Speichert Teilnehmer als zugelassen. 
 	* @param id - Identifikationsnummer der Lehrveranstaltung
 	* @param userdaten - Nutzerdaten 
 	*/
	public void teilnehmerZulassen(String id,Vector<Vector> userdaten) {
		LOG.entering(this.getClass().getName(), "teilnehmerZulassen", new Object[] {id,userdaten});
		HashMap<String,Boolean> map = new HashMap();
		for(Vector x:userdaten) {
			
			map.put((String)x.get(0), (Boolean)x.get(1));
		}
		datenbank.setVeranstaltungsTeilnehmer(id, map);
		LOG.info(mail+" wurde zur Veranstaltung "+id+" zugelassen.");
		LOG.exiting(this.getClass().getName(), "teilnehmerZulassen");
	}

	/**
 	* createUser(HashMap<String,String>) Speichert Daten eines neu eingestellten Nutzers(vom Admin)
 	* @param data - Nutzerdaten
 	* @param flag - Benutzergruppe
 	*/
	public static boolean createUser(HashMap<String,String> data,int flag) {
		LOG.entering("lups.Controller", "createUser", new Object[] {data,flag});
		String pass = "random001";
		
		if (!datenbank.existsUser(data.get("email"))) {
			try {
				String pwd = sha1(pass);
				datenbank.createUser(data, pwd, flag);
			}
			catch(NoSuchAlgorithmException e) {
				LOG.warning("Error: "+e);
			}
			LOG.info("Neuer Nutzer wurde erstellt. Daten: "+data);
			LOG.exiting("lups.Controller", "createUser", true);
			return true;
		} else {
			LOG.warning("Nutzer ist schon vorhanden.");
			LOG.exiting("lups.Controller", "createUser", false);
			return false;
		}
	}

	/**
 	* deleteUser(String) löschen eines Benutzerkontos.
 	* @param email - email des Nutzers
 	*/
	public void deleteUser(String email) {
		LOG.entering(this.getClass().getName(), "deleteUser", email);
		datenbank.deleteUsr(email);	
		LOG.info(email+" wurde von "+mail+" gelöscht.");
		LOG.exiting(this.getClass().getName(), "deleteUser");
	}

	/**
 	* createLehrveranstaltung(HashMap<String,String>) erstellt Lehrveranstalltung
 	* @param data - HashMap<Beschreibung, Daten>
 	* @return true wenn noch nicht existiert, sonst false
 	*/
	public boolean createVeranstaltung(HashMap<String,String> data) {
		LOG.entering(this.getClass().getName(), "createLehrveranstaltung", data);
		String titel = data.get("titel");
		if(datenbank.existsVeranstaltung(titel)) {
			LOG.warning("Lehrveranstaltung konnte nicht erstellt werden. Bereits vorhanden!");
			LOG.exiting(this.getClass().getName(), "createLehrveranstaltung", false);
			return false;
		} else {
			String beschreibung = data.get("beschreibung");
			String zeit = data.get("zeit");
			ido++;
			datenbank.createVeranstaltung(String.valueOf(ido),titel,mail,beschreibung,zeit);
			LOG.info("Lehrveranstaltung: "+data+" wurde erfolgreich erstellt.");
			LOG.exiting(this.getClass().getName(), "createLehrveranstaltung", true);
			return true;
		} 		
	}

	/**
 	* deleteLehrveranstaltung() löscht Lehrveranstaltung
 	* @param id - Identifikationsnummer der Lehrveranstaltung
 	*/
	public void deleteLehrveranstaltung(String id) {
		LOG.entering(this.getClass().getName(), "deleteLehrveranstaltung",id);
		datenbank.deleteVeranstaltung(id);
		LOG.info("Lehrveranstaltung: "+id+" wurde von "+mail+" gelöscht.");
		LOG.exiting(this.getClass().getName(), "deleteLehrveranstaltung");
	}
	
	/**
	 * getVeranstaltung(String) hohlt eien spezielle Lehrveranstaltung und gibt sie zurück
	 * @param ID,  Identifikationsnummer der Vorlesung
	 */
	public HashMap<String,String> getVeranstaltung(String ID) {
		LOG.entering(this.getClass().getName(), "getVeranstaltung", ID);
		LOG.info("Lade alle Veranstaltungen.");
		LOG.exiting(this.getClass().getName(), "getVeranstaltung", datenbank.getVorlesung(ID));
		return datenbank.getVorlesung(ID);
	}

	/**
 	* prueferAbmelden(String) Prüfer von Lehrveranstalltung abmelden.
 	* @param id - Identifikationsnummer der Lehrveranstaltung
 	*/
	public void prueferAbmelden(String id) {

		LOG.entering(this.getClass().getName(), "prueferAbmelden", id);
		datenbank.prueferAbmelden(mail, id);
		LOG.info("Prüfer: "+mail+" hat sich von Lehrveranstaltung: "+id+" abgemeldet.");
		LOG.exiting(this.getClass().getName(), "prueferAbmelden");
	}
	
	public void prueferAnmelden(String id) {
		//System.out.println(id);
		//System.out.println(mail);
		
		LOG.entering(this.getClass().getName(), "prueferAnmelden", id);
		datenbank.setVeranstaltungsPruefender(id,mail);
		LOG.info("Prüfer: "+mail+" hat sich von Lehrveranstaltung: "+id+" abgemeldet.");
		LOG.exiting(this.getClass().getName(), "prueferAnmelden");	
	}

	/**
 	* pruefenderListe() Listet alle Lehrveranstaltungen eines Prüfers 
 	* @return Vector<Vector<String>> - Liste der Lehrveranstaltungen
 	*/
	public Vector<Vector<String>> pruefenderListe() {
		LOG.entering(this.getClass().getName(), "pruefenderListe");
		LOG.info("Lade Lehrveranstaltungen des Prüfers "+mail+".");
		LOG.exiting(this.getClass().getName(), "pruefenderListe", datenbank.getPruefenderliste(mail));
		return datenbank.getPruefenderliste(mail);
	}

	/**
 	* listeZugelasseneTeilnehmer(String) Alle zugelassenen Studenten einer Lehrveranstaltung
 	* @param id - Identifikationsnummer der Vorlesung
 	* @return Liste aller zugelassenen Studenten
 	*/
	public Vector<String> listeZugelasseneTeilnehmer(String id) {
		LOG.entering(this.getClass().getName(), "listeZugelasseneTeilnehmer", id);
		LOG.info("Lade Zugelassene Teilnehmer von "+id);
		Vector<String> tmpVector=new Vector<String>();
		for(Vector<String> itr:datenbank.getTeilnehmerlisteGUI(id,true)){
			tmpVector.add(itr.get(0));
		}
		LOG.exiting(this.getClass().getName(), "listeZugelasseneTeilnehmer", tmpVector);
		return tmpVector;
	}

	/**
 	* leistungsUeberbersichtGUI() Leistungsübersicht des angemeldeten Studenten 
 	* @return Vector<Vector<String>>  -Leistunsübersicht
 	*/
	public Vector<Vector<String>> leistungsUebersichtGUI() {
		LOG.entering(this.getClass().getName(), "leistungsUebersichtGUI");
		LOG.info("Leistungsübersicht von "+mail+" an GUI weitergereicht.");
		LOG.exiting(this.getClass().getName(), "leistungsUebersichtGUI", datenbank.getLeistungsuebersichtGUI(mail));
		return datenbank.getLeistungsuebersichtGUI(mail);
	}

	/**
 	* leistungsUebersichtPDF() Leistungsübersicht des angemeldetan Studenten
 	* @return List<String> von Strings (Lehrveranstaltungen und Note)
 	*/
	public List<String> leistungsUebersichtPDF() {
		LOG.entering(this.getClass().getName(), "leistungsUebersichtPDF");
		LOG.info("Leistungsübersicht von "+mail+" an PDF weitergereicht.");
		LOG.exiting(this.getClass().getName(), "leistungsUebersichtPDF", datenbank.getLeistungsuebersichtPDF(mail));
		return datenbank.getLeistungsuebersichtPDF(mail);
	}

	/**
 	* kurslisteGUI() Alle Kurse bei denen ein Studenten angemeldet ist
 	* @return Vector<Vector<String>> 
 	*/
	public Vector<Vector<String>> kurslisteGUI() {
		LOG.entering(this.getClass().getName(),"kurslisteGUI");
		LOG.info("Alle Kurse von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "kurslisteGUI", datenbank.getKurslisteGUI(mail));
		return datenbank.getKurslisteGUI(mail);
	}

	/**
 	* logAuslesen() Stellt auf der GUI die Loggs dar.
 	* @ return list: List<String>
 	*/
	public List<String> logAuslesen() {
		LOG.entering(this.getClass().getName(),"logAuslesen");
		List<String> list = new ArrayList(20);
		try {
			BufferedReader in = new BufferedReader(new FileReader("lups.log"));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				list.add(zeile);
			}
		} catch (IOException e) {
			LOG.warning("Error: "+e);
		}
		LOG.info("Logs wurden von "+mail+" eingesehen.");
		// auskommentieren?
		LOG.exiting(this.getClass().getName(), "logAuslesen", list);
		return list;
	}

	/**
 	* getMailList() Gibt Liste aller Nutzer zurück
 	* @return Vector<String> Nutzerliste
 	*/
	public Vector<String> getMailList() {
		LOG.entering(this.getClass().getName(), "getMailList");
		LOG.info("Alle Nutzer werden ausgegeben.");
		LOG.exiting(this.getClass().getName(), "getMailList", datenbank.getUserList());
		return datenbank.getUserList();
	}

	/**
 	* isStudent() Ist Nutzer student?
 	* @return true wenn ja sonst nein
 	*/
	public boolean isStudent() {
		LOG.entering(this.getClass().getName(), "isStudent");
		LOG.info("Prüfe ob Nutzer Student ist.");
		if(kuser.equals("Student")) {
			LOG.info("Nutzer "+mail+" ist Student.");
			LOG.exiting(this.getClass().getName(), "isStudent", true);
			return true;
		} else {
			LOG.info("Nutzer "+mail+" ist kein Student.");
			LOG.exiting(this.getClass().getName(), "isStudent", false);
			return false;
		}
	}

	/**
	 * isAngemeldet(String) Ist Nutzer angemeldet?
	 * @param id
	 * @return true wenn Nutzer angemeldet ist, sonst false.
	 */
	public boolean isAngemeldet(String id) {
		LOG.entering(this.getClass().getName(), "isAngemeldet", id);
		List<String> lola = new ArrayList<String>();
		lola = datenbank.getTeilnehmerlistePDF(id);
		LOG.info("Prüfe ob Nutzer für die Veranstaltung "+id+" angemeldet ist.");
		LOG.exiting(this.getClass().getName(), "isAngemeldet", lola.contains(user));
		return lola.contains(user);
	}

	/**
	 * getVorlesungsverzeichnis() hohlt kommplettes Vorleseungsverzeichniss
	 * @return Vector<Vector<String>> - Vorlesungen
	 */
	public Vector<Vector<String>> getVorlesungsverzeichnis() {
		LOG.entering(this.getClass().getName(), "getVorlesungsverzeichnis");
		LOG.info("Lade komplettes Vorlesungsverzeichnis.");
		LOG.exiting(this.getClass().getName(), "getVorlesungsverzeichnis", datenbank.getVorlesungsverzeichnis());
		return datenbank.getVorlesungsverzeichnis();
	}

	/**
	 * getLehrenderliste() Gibt die Liste von Lehrveranstaltungen eines Prüfenden/Lehrenden zurück
	 * @return Liste von Lehrveranstaltungen (Vector<Vector<String>>>)
	 */
	public Vector<Vector<String>> getLehrenderliste() {
		LOG.entering(this.getClass().getName(), "getLehrenderliste");
		LOG.info("Lade Lehrveranstaltungen von "+mail+".");
		LOG.exiting(this.getClass().getName(), "getLehrenderliste", datenbank.getLehrenderliste(mail));
		return datenbank.getLehrenderliste(mail);
	}

	/**
	 * getTeilnehmerliste(String,boolean) Gibt Liste von Namen(Studenten) einer Lehrveranstaltung zurück
	 * @param id - String id der Lehrveranstaltung
	 * @param a - Zugelassen oder Angemeldet.
	 * @return Vector<Vector<String>> Teilnehmerliste
	 */
	public Vector<Vector> getTeilnehmerliste(String id,boolean a) {
		LOG.entering(this.getClass().getName(), "getTeilnehmerliste", new Object[] {id,a});
		LOG.info("Lade Teilnehmerliste von "+id+".");
		LOG.exiting(this.getClass().getName(), "getTeilnehmerliste", datenbank.getTeilnehmerlisteGUI(id,a));
		return datenbank.getTeilnehmerlisteGUI(id,a);
	}

	/**
	 * getBewertungen() Gibt Veranstaltungen und Noten von user zurück.
	 * @return Vector vom Vektor
	 */
	public Vector<Vector<String>> getBewertungen() {
		LOG.entering(this.getClass().getName(), "getBewertungen");
		LOG.info("Lade Bewertungen und Noten von "+mail+".");
		LOG.exiting(this.getClass().getName(), "getBewertungen", datenbank.getLeistungsuebersichtGUI(mail));
		return datenbank.getLeistungsuebersichtGUI(mail);
	}

	/**	
 	* studenplan() Gibt den Stundenplan des angemeldetan Nutzers in String-Listenform zurück
 	* @return Liste von Strings
 	*/
	public List<String> stundenplan() {
		LOG.entering(this.getClass().getName(), "stundenplan");
		LOG.info("Stundenplan von "+mail+" ausgegeben.");
		LOG.exiting(this.getClass().getName(), "stundenplan", datenbank.getKurslistePDF(mail));
		return datenbank.getKurslistePDF(mail);
	}

	/**
 	* print(Int) Druckt je nach id das gewünschte Dokument
 	* @param id - legt fest was gedruckt werden soll.
 	*/
	public void print(int id) {
		LOG.entering(this.getClass().getName(), "print", id);
		switch(id){
			case 1:	{ 	
				PDF.leistungsuebersicht(datenbank, mail);
				LOG.info("drucke Leistungsübersicht von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
				break;
			}
			case 2:	{
				PDF.studienbescheinigung(datenbank, mail);
				LOG.info("drucke Studienbescheinigung von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
				break;
			}
			case 3:	{
				PDF.stundenplan(datenbank, mail);
				LOG.info("drucke Stundenplan von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
				break;
			}
			case 4:	{
				PDF.studienverlaufsbescheinigung(datenbank, mail);
				LOG.info("drucke Studienverlaufsbescheinigung von "+mail);
				LOG.exiting(this.getClass().getName(), "print");
				break;
			}
		}
	}

	/**
 	* printTeilnehmerliste(String) Druckt Teilnehmerliste einer Lehrveranstaltung
 	* @param id - Identifikationsnummer der Vorlesung
 	*/
	public void printTeilnehmerliste(String id) {
		LOG.entering(this.getClass().getName(), "printTeilnehmerliste",id);
		LOG.info("Drucke Teilnehmerliste der Vorlesung "+id);
		PDF.teilnehmerliste(datenbank, id);
		LOG.exiting(this.getClass().getName(), "printTeilnehmerliste");
	}

	/**
 	* sha1(String) Gibt einen String verschlüsselt zurück.
 	* @param input - zu verschlüsselnder String (Passwort)
 	* @return sha1 verschlüsselter String
 	*/
	static String sha1(String input) throws NoSuchAlgorithmException {
		LOG.entering("lups.Controller", "sha1", input);
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		LOG.info("Passwort ist nun verschlüsselt!");
		LOG.exiting("lups.Controller", "sha1", sb.toString()); 
		return sb.toString();
	}
	
}
