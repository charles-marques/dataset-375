package ecms.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import ecms.domain.BaseUser;
import ecms.repository.Dao;


public class TestAdministrationManagerSer {

	
	static AdministrationManagerSer adminManager;
	static Dao d;
	static BaseUser u1;
	
	static BaseUser sm2;
	
	@BeforeClass
	public static void beforeAll() {
		adminManager = new AdministrationManagerSer();
//		adminManager.init();
		d = new Dao();
		d.init();
		
		adminManager.setEcmsDao(d);
		
		/*sm1.setFirstName("coucou prenom");
		sm1.setLastName("coucou nom");
		sm1.setLogin("login coucou");*/
		
	
		
		
		sm2 = new BaseUser();
		
	
	}
	
	@AfterClass
	public static void afterAll() {
		adminManager.getEcmsDao().close();
		
	}
	
	@Test
	public void testAddStoreManager() {
		
	}
	
	@Test
	public void testAddStoreManager2() {
	//	adminManager.addStoreManager(sm2);
		
	}	
	
}
