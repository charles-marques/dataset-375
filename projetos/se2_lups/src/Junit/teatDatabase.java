import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import lups.Database;


public class teatDatabase {
	Database a = new Database();
	HashMap<String,Boolean> la = new HashMap<String,Boolean>();
	HashMap<String,String> s11 = new HashMap<String,String>();
	HashMap<String,String> s12 = new HashMap<String,String>();
	HashMap<String,String> admin1 = new HashMap<String,String>();
	HashMap<String,String> pruefer1 = new HashMap<String,String>();
	HashMap<String,String> student1 = new HashMap<String,String>();
	HashMap<String,String> student2 = new HashMap<String,String>();
	HashMap<String, String> vorlesung = new HashMap<String, String>();
	Vector<Vector> b = new Vector<Vector>();
	Vector<Vector> d = new Vector<Vector>();
	Vector<Vector> test1 = new Vector<Vector>();
	Vector<Vector> test2 = new Vector<Vector>();
	Vector<Vector<String>> liste = new Vector<Vector<String>>();
	Vector<Vector<String>> liste1 = new Vector<Vector<String>>();
	Vector<Vector<String>> lügui = new Vector<Vector<String>>();
	Vector<Vector<String>> klist = new Vector<Vector<String>>();
	Vector<Vector<String>> k = new Vector<Vector<String>>();
	Vector<String> ksp = new Vector<String>();
	Vector<String> sp = new Vector<String>();
	Vector<String> spalten = new Vector<String>();
	Vector<String> spalten1 = new Vector<String>();
	Vector c = new Vector();
	Vector e = new Vector();
	Vector l = new Vector();
	Vector c1 = new Vector();
	Vector e1 = new Vector();
	Vector test11 = new Vector();
	Vector test21 = new Vector();
	List<String> liste2 = new ArrayList<String>();
	List<String> ea = new ArrayList<String>();
	List<String> lüpdf = new ArrayList<String>();
	List<String> lüpdf1 = new ArrayList<String>();
	List<String> lola = new ArrayList<String>();
	List<String> pdf = new ArrayList<String>();
	
	
	
	
		@Before
		public void setUp() throws Exception {
			test11.add("2");
			test11.add("2");
			test11.add("Noch kein Prüfender vorhanden");
			test1.add(test11);
			test21.add("9");
			test21.add("2");
			test21.add("Noch kein Prüfender vorhanden");
			test1.add(test21);
			l.add("9");
			l.add("2");
			l.add("pruefer");
			k.add(l);
			pdf.add("2");
			la.put("A", true);
			la.put("B", false);
			vorlesung.put("titel","2");
			vorlesung.put("beschreibung","4");
			vorlesung.put("lehrender","3");
			vorlesung.put("zeit","5");
			vorlesung.put("pruefender","pruefer");
			c.add("A");
			c.add(true);
			b.add(c);
			e.add("B");
			e.add(false);
			d.add(e);
			ea.add("B");
			ea.add("A");
			s11.put("name", "A");
			s11.put("straße", "A");
			s11.put("ort", "A");
			s11.put("matnr", "A");
			s11.put("email", "student1");
			s11.put("flag", "3");
			s12.put("name", "B");
			s12.put("straße", "A");
			s12.put("ort", "A");
			s12.put("matnr", "A");
			s12.put("email", "student2");
			s12.put("flag", "3");
			admin1.put("email","admin");
			pruefer1.put("email", "pruefer");
			student1.put("email", "student1");
			student2.put("email", "student2");
			a.createUser(admin1,"a",0);
			a.createUser(pruefer1,"a",1);
			a.createUser(student1,"a",3);
			a.createUser(student2, "a", 3);
			a.setUserDaten("student1", s11);
			a.setUserDaten("student2",s12);
			a.setPasswort("student1", "lol");
			a.setSemester("student1", 1);
			a.setFach("student1","pimpern");
			spalten.add("Es sind keine Vorlesungen eingetragen.");
			liste.add(spalten);
			spalten1.add("9");
			spalten1.add("2");
			spalten1.add("3");
			liste1.add(spalten1);
			liste2.add("admin");
			sp.add("9");
			sp.add("nicht Bestanden");
			sp.add("5.0");
			lügui.add(sp);
			ksp.add("9");
			ksp.add("2");
			ksp.add("3");
			ksp.add("angemeldet");
			klist.add(ksp);
			lüpdf.add("9");
			lüpdf.add("nicht Bestanden");
			lüpdf.add("5.0");
			
			
			
			
		}	
		
		@Test
		public void testVeranstaltung() {

			assertEquals(a.getVorlesungsverzeichnis(),liste);
		
			a.createVeranstaltung("9","2","3","4","5");
			
			assertEquals(a.getVeranstaltungsTitel("9"), "2");
			assertTrue(a.existsVeranstaltung("2"));
			assertFalse(a.existsVeranstaltung("3"));

			a.anmeldenKurse("student1", "9");
			a.anmeldenKurse("student2", "9");
			a.setVeranstaltungsTeilnehmer("9", la);

			assertEquals(a.getVeranstaltungsTitel("9"),"2");
			assertEquals(a.getTeilnehmerlisteGUI("9", true),b);
			assertEquals(a.getTeilnehmerlisteGUI("9", false),d);
			assertEquals(a.getTeilnehmerlistePDF("9"),ea);
			
			ea.remove("B");
			a.abmeldenKurse("student2", "9");

			assertEquals(a.getTeilnehmerlistePDF("9"),ea);
			
			a.setVeranstaltungsPruefender("9", "pruefer");

			assertEquals(a.getPruefenderliste("pruefer"),k);
			assertEquals(a.getVorlesung("9"),vorlesung);
			assertEquals(a.getVorlesungsverzeichnis(), liste1);
			
			vorlesung.remove("pruefender");
			a.prueferAbmelden("pruefer", "9");
			
			assertEquals(a.getVorlesung("9"),vorlesung);
			
			a.createVeranstaltung("2", "2", "2", "2", "2");
			HashMap<String,Boolean> la = new HashMap<String,Boolean>();
			la.put("s1", true);
			la.put("s2", false);
			
			assertEquals(a.getPruefenderliste("pruefer"),test1);
			
			a.deleteVeranstaltung("9");
			a.deleteVeranstaltung("2");
			
			assertFalse(a.existsVeranstaltung("9"));
			
			test11.clear();
			
			assertEquals(a.getPruefenderliste("pruefer"), test11);

		}
		
		@Test
		public void testUser(){
			
			
			assertTrue(a.existsUser("student1"));
			assertTrue(a.existsUser("student2"));
			assertEquals(a.getUserDaten("student1"),s11);
			assertEquals(a.getPasswort("student1"),"lol");
			assertEquals(a.getSemester("student1"), 1);
			assertEquals(a.getFach("student1"), "pimpern");
			assertEquals(a.getName("student1"),"A");
			a.createVeranstaltung("9","2","3","4","5");
			a.anmeldenKurse("student1", "9");
			assertEquals(a.getKurslisteGUI("student1"),klist);
			assertEquals(a.getKurslistePDF("student1"), pdf);
			a.zulassenKurse("student1", "9");
			a.setNote("student1", "9", (double)5);
			double i = a.getNote("student1", "9");
			assertEquals(i,(double)5,(double)0);
			assertEquals(a.getLeistungsuebersichtGUI("student1"),lügui);
			assertEquals(a.getLeistungsuebersichtPDF("student2"),lola);
			assertEquals(a.getLeistungsuebersichtPDF("student1"), lüpdf);
			a.deleteUsr("student1");
			a.deleteUsr("student2");
			a.deleteUsr("pruefer");
			a.deleteUsr("admin");
			test11.clear();
			assertFalse(a.existsUser("student1"));
			assertEquals(a.getUserList(), test11);
			
			
		}
		
		
	}



