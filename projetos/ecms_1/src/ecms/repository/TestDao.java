package ecms.repository;

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import ecms.domain.Address;
import ecms.domain.BaseUser;
import ecms.domain.Cart;
import ecms.domain.Item;
import ecms.domain.Order;
import ecms.domain.Role;




public class TestDao {

	static Dao dao ;

	static Address a1 ;
	static Address a2 ;
	
	static BaseUser admin;
	static BaseUser sm;
	static BaseUser user;

	static Item 	i1;
	static Item 	i2;
	static Item 	i3;
	
	static Cart panier1;
	static Cart panier2;
	
	static Order userOrder1;
	@BeforeClass
	public static void beforeAll() {

		dao = new Dao();
		dao.init();

		admin=new BaseUser();
		admin.setFirstName("eric");
		admin.setLastName("scotty");
		admin.setLogin("admincms");
		admin.setPassword("pazioubd");
		admin.setStatus("femme");
		admin.setRole(Role.ROLE_ADMIN);

		sm= new BaseUser();
		sm.setFirstName("moalo");
		sm.setLastName("binguio");
		sm.setLogin("storemanager");
		sm.setPassword("pazioubd");
		sm.setStatus("femme");
		sm.setRole(Role.ROLE_SM);
		
		user= new BaseUser();
		user.setFirstName("pouet");
		user.setLastName("plop");
		user.setLogin("user");
		user.setPassword("coucou");
		user.setStatus("femme");
		user.setRole(Role.ROLE_USER);

		a1 = new Address();
		a1.setCity("marseille");
		a1.setCountry("France");
		a1.setStreetAddress1(" ch 76B , Campus Caroline");
		a1.setStreetAddress2(" 23 avenue du Rome");
		a1.setZip("1234P");

		a2 = new Address();
		a2.setCity("Rome");
		a2.setCountry("Italie");
		a2.setStreetAddress1("bat B76 , rue de la flurentine ");
		a2.setStreetAddress2("avenue de la bergere ");
		a2.setZip("2552K");

		user.setAddress(a1);
		
		sm.setAddress(a2);
		admin.setAddress(a2);
		
		
		
		i1 = new Item("Kingston 20", 19.6, 40, "disque dur d'une capacite de 40Go",  40, null, null, "informatique");
		i2 = new Item("Seagate 10", 19.6, 100, "disque dur d'une capacite de 500Go",  20, null,  null, "informatique");
		
		i3 = new Item("tablette graphique", 19.6, 100, "ipad next gen", 20, null, null, "hipster");
		
	}

	@AfterClass
	public static void afterAll() {
		dao.close();
	}

	@Test 
	public void testAddAndFindUser(){
		dao.addUser(sm);
		
		BaseUser temp = null;
		dao.addUser(user);
		temp = dao.findUserByLogin("user");
		assertNotNull(temp);
	}
	
	@Test(expected = PersistenceException.class)
	public void testAddUserTwoTimes() {
		dao.addUser(admin);
		dao.addUser(admin);
	}

	@Test
	public void testUpdateUser(){
		BaseUser temp = dao.findUserByLogin("user");

		temp.setFirstName("new firstName");
		dao.updateUser(temp);
		BaseUser isUpdateOk = dao.findUserByLogin("user");
		
		assertEquals(isUpdateOk.getFirstName(), "new firstName");
	}


	@Test(expected=NoResultException.class)
	public void testDeleteUser() {
		BaseUser temp = null;
		dao.deleteUserByLogin("user");
		temp = dao.findUserByLogin("user");
		/* never reached */
		Assert.assertNull(temp);
		

	}

	@Test
	public void testListUser() {
		Collection<Long> lstUser = null;
		lstUser = dao.listUserId();
		
		Assert.assertEquals(2, lstUser.size());
		
	}
	
	@Test
	public void testAddItem() {
		Item temp = null;
		dao.addItem(i1);
		dao.addItem(i2);
		dao.addItem(i3);
		
		temp = dao.findItemByName("Seagate 10");
		assertNotNull(temp);
	}
	
	@Test
	public void listItem() {
		Collection<Long> lstItem = null;
		lstItem = dao.listItemId();
		
		Assert.assertEquals(3, lstItem.size());
		
	}
	
	@Test
	public void testUpdateItem() {
		Item temp = null;
		temp = dao.findItemByName("Seagate 10");
		temp.setPrice(400);
		
		dao.updateItem(temp);
		Assert.assertEquals(400, dao.findItemByName("Seagate 10").getPrice(),1);
		
	}
	
	@Test
	public void testAddCart() {
		
		BaseUser custom = dao.findUserByLogin("storemanager");
		//ArrayList<Item> lstItemCart = new ArrayList<>();		
		
		panier1 = new Cart("Mes nouveaux composantes infos", custom, null);
		dao.addCart(panier1);
		
		ArrayList<Cart> lstCart = dao.findCartByUser("storemanager");
		panier1 = lstCart.get(0);
		
		dao.addItemToCart(panier1, dao.findItemByName("Seagate 10"));
		dao.addItemToCart(panier1, dao.findItemByName("Kingston 20"));
		
	//	lstCart = dao.findCartByUser("storemanager");
		
		
		/* query */
		
		
		/**
		 * 
		 * select i.* from TItem i, TCart c, Item_Cart ic 
		 * where i.id = ic.Item_id 
		 * and c.id = ic.Cart_id 
		 * and c.id = 1;

		 * 
		 * 
		 * 
		 */
		
	//	ArrayList<Item> itemCart = dao.findItemFromCart(panier1.getId());
	//	System.out.println("Taille liste : " + itemCart.size());
//		System.out.println("Nbre item : " + lstCart.get(0).getItems().size());
		
		//dao.updateItemToCart(panier1, dao.findItemByName("Kingston 20"));
		
		//ArrayList<Cart> lstCart = dao.findCartByUser("storemanager");
		
	//	lstItemCart = dao.findItemFromCart(lstCart.get(0).getId());
		
	//	System.out.println("Cart : " + lstItemCart.size());
		
		
		
		
//		
	
		//System.out.println("size item list : " + lstCart.get(0).getItems().get(0).getName());
		//System.out.println("item :  " + panier1.getItems().get(0).getName());
		
		
//		/* thecustomer prepare an order */
//		userOrder1 = new Order(user, "en cours", new Date("31/08/1988"));
//		userOrder1.setCart(panier1);
		
//		
//		
//		ArrayList<Cart> lstCart = null;
//		dao.addCart(panier1);
//		lstCart = dao.findCartByUser("storemanager");
////		
//		System.out.println("Objet " + lstCart.get(0).getItems().size());
//		
//		Assert.assertEquals(1, lstCart.size());		
	}
	
	@Test
	public void testFindSm() {
		ArrayList<BaseUser> lstSm = (ArrayList<BaseUser>) dao.findAllStoreManager();
		Assert.assertEquals(lstSm.size(), 1);
	}
//	
//	@Test
//	public void updateCart() {
//		ArrayList<Cart> lstCart = null;
//		Cart myCart = null;
//		
//		lstCart = (ArrayList<Cart>) dao.findCartByUser("storemanager");
//		myCart = lstCart.get(0);
//		
//		System.out.println("Taille du panier : " + lstCart.size());
//		
//		
//		Item i = myCart.getItems().get(0);
//		
//		System.out.println("Cart : " + i.getName());
//		System.out.println("pouet");
//		myCart.getItems().add(dao.findItemByName("tablette graphique"));
//		dao.updateCart(myCart);
//		
//		lstCart = (ArrayList<Cart>) dao.findCartByUser("storemanager");
//		
//		Assert.assertEquals(2, lstCart.size());
//
//	}
	
//	@Test
//	public void testRemoveCart() {
//		
//		Cart c = dao.findCartByUser("user").get(0);
//		dao.deleteCart(c);
//		
//		
//	}
//
	@Test(expected = NoResultException.class)
	public void testRemoveItem() {
		Collection<Long> lstItem = null;
		lstItem = dao.listItemId();
		
		for(Long  i : lstItem) {
			dao.deleteItem(dao.findItem(i));
		}
		
		/* pick one to raise the exception */
		dao.findItemByName("Seagate 10");
		
	}
	
	@Test
	public void testRemoveUser() {
		Collection<Long> lstUser = null;
		lstUser = dao.listUserId();
		
		for(Long i : lstUser) {
			dao.deleteUser(i);
		}
		
	}
	

//
//	 @Test(expected=NoResultException.class)
//	 public void updateItem(){
//		 Item tmp =dao.findItemByName(item.getItemName());
//		 tmp.setItemName("COLAGATE yue661");
//		 tmp.setPrice(200);
//		 dao.updateItem(tmp);
//		 assertNotNull(dao.findItemByName(tmp.getItemName()));
//		 assertEquals("COLAGATE yue661",(dao.findItemByName(tmp.getItemName())).getItemName());
//	 }



}
