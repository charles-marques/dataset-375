/**
 * Unit Test von PDF
 * 
 * 
 * @author SE_Team08
 * @version 1.0
 */
package lups;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

public class PDF_Unit_Test {
	database.Database datenbank;
	//Database datenbank;
	String nutzer;
	String kurs;
	HashMap<String,String> s11 = new HashMap<String,String>();
	HashMap<String,String> student1 = new HashMap<String,String>();
	@Before
	public void setUp() throws Exception {
		datenbank = new database.Database();
		student1.put("email", "Max Mustermann");
		datenbank.createUser(student1,"a",3);
		//datenbank = new Database();
		nutzer="Max Mustermann";
		kurs="1234";
	}

	@Test
	public void testLeistungsuebersicht() {
		assertTrue(pdf.PDF.leistungsuebersicht(datenbank, nutzer));
	}
	
	@Test
	public void testStudienbescheinigung() {
		assertTrue(pdf.PDF.studienbescheinigung(datenbank, nutzer));
	}
	
	@Test
	public void testStundenplan() {
		assertTrue(pdf.PDF.stundenplan(datenbank, nutzer));
	}
	
	@Test
	public void testStudienverlaufsbescheinigung() {
		assertTrue(pdf.PDF.studienverlaufsbescheinigung(datenbank, nutzer));
	}
	
	@Test
	public void testTeilnehmerliste() {
		assertTrue(pdf.PDF.teilnehmerliste(datenbank, kurs));
	}
	
}