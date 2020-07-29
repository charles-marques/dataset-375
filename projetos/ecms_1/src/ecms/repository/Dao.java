package ecms.repository;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
//import javax.persistence.Query;

import ecms.domain.BaseUser;
import ecms.domain.Cart;
import ecms.domain.Item;
import ecms.domain.Order;
import ecms.domain.Role;

public class Dao {

	private EntityManagerFactory factory = null;


	public void init() {
		factory = Persistence.createEntityManagerFactory("myBase");
	}


	public void close() {
		if (factory != null) {
			factory.close();
		}
	}


	private EntityManager newEntityManager() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		return (em);
	}

	private void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				EntityTransaction t = em.getTransaction();
				if (t.isActive()) {
					try {
						t.rollback();
					} catch (PersistenceException e) {
					}
				}
				em.close();
			}
		}
	}	


	public <T> T add(T entity) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			entity=em.merge(entity) ;
			em.persist(entity);
			em.getTransaction().commit();
			return (entity);
		} finally {
			closeEntityManager(em);
		}
	}

	public <T> T update(T entity ) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			entity = em.merge(entity);
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em);
		}
		return entity;
	}




	public <T> T find(Class<T> clazz, Object id) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			return em.find(clazz, id);
		} finally {
			closeEntityManager(em);
		}
	}

	/*@SuppressWarnings("unchecked")
	public <T> Collection<T> findAll(Class<T> clazz) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			Query query = em.createQuery("SELECT a FROM :clazz a ");
			return query.getResultList();
		} finally {
			closeEntityManager(em);
		}
	}*/



	public <T> void remove(Class<T> clazz, Object pk) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			T entity = em.find(clazz, pk);
			if (entity != null) {
				em.remove(entity);
			}
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em);
		}
	}
	


	@SuppressWarnings("unchecked")
	public List<Long> listUserId(){
		/*methode permettant de retourner la liste des stores manager de BaseUtilisateur*/
		EntityManager em = null;

		List<Long> list=null ;
		try{
			em = newEntityManager();
			list = em.createQuery("select u.id from ecms.domain.BaseUser u").getResultList();

			return list;
		}finally{
			closeEntityManager(em);
		}
		
	}


	public void addUser(BaseUser u) {
		this.add(u);
	}
	

	public BaseUser findUserByLogin(String login) {
		EntityManager em = null;
		BaseUser findUser = null;
		try {
			em = newEntityManager();
			findUser = (BaseUser) em.createQuery("select u from ecms.domain.BaseUser u where u.login=?1").setParameter(1, login).getSingleResult();
			
			return findUser;
		} finally {
			closeEntityManager(em);
		}		
	}

	public void deleteUser(Long id)
	{
		remove(BaseUser.class,id);
	}
	public void deleteUserByLogin(String login) {
		EntityManager em = null;
		try {
			em = newEntityManager();
			BaseUser u = (BaseUser) em.createQuery("select u from ecms.domain.BaseUser u where u.login=?1").setParameter(1, login).getSingleResult();
			if (u != null) {
				em.remove(u);
			}
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em);
		}
	}
	
	public BaseUser updateUser(BaseUser u){
		return this.update(u);
	} 

	
	


	public void deleteItem(Item i){
		remove(Item.class,i.getId());
	}

	public Item updateItem(Item i){
		return this.update(i);
	} 

	public Item addItem(Item i){
		return this.add(i);
	}


	public Item findItem(long id ) {
		return find(Item.class, id);
	} 

	public  Item findItemByName(String name){

		EntityManager em = null;

		Item produit =null ;
		try{
			em = newEntityManager();
			produit = (Item) em.createQuery("select c from ecms.domain.Item c where c.name=?1").setParameter(1,name).getSingleResult();

			return produit;

		}finally{
			closeEntityManager(em);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> listItemId(){
		
		EntityManager em = null;

		List<Long> list=null ;
		try{
			em = newEntityManager();
			list = em.createQuery("select i.id from ecms.domain.Item i").getResultList();

			return list;
		}finally{
			closeEntityManager(em);
		}
		
	}
	
	public void addCart(Cart c) {
		this.add(c);
	}
	
	public Cart updateCart(Cart c) {
		return this.update(c);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cart> findCartByUser(String login) {
		
		EntityManager em = null;

		ArrayList<Cart> lstCart = null ;
		try{
			em = newEntityManager();
			lstCart= (ArrayList<Cart>) em.createQuery("select c from ecms.domain.Cart c where c.owner.login=?1").setParameter(1,login).getResultList();

			return lstCart;
		}finally{
			closeEntityManager(em);
		}		
	}
	
	public Cart findCartbyId(Long id) {
		return this.find(Cart.class, id);
	}
	
	
	public void addItemToCart(Cart c, Item t) {
		EntityManager em = null;
		
		try{
			em = newEntityManager();
			
			Cart cart = em.find(Cart.class, c.getId());
			Item item = em.find(Item.class, t.getId());
			//Cart cart = em.merge(c);
			//Item item = em.merge(t);
			
			item.getCarts().add(cart);
			cart.getItems().add(item);
		
			
			em.persist(cart);
			em.persist(item);
			em.getTransaction().commit();

		}finally{
			closeEntityManager(em);
		}	
		
		
	}
	
	
	/*public void updateItemToCart(Cart c, Item i) {

		EntityManager em = null;
		
		try{
			em = newEntityManager();
			
			i.getCarts().add(c);
			
			c.getItems().add(i);
		
			
			em.merge(c);
			em.merge(i);
			

			
			//em.persist(cart);
			//em.persist(item);
			em.getTransaction().commit();

		}finally{
			closeEntityManager(em);
		}	
	}*/
	
	public void deleteCart(Cart c) {
		
		this.remove(Cart.class, c.getId());
		
	}
	
	public void addOrder(Order o) {
		this.add(o);
	}
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByUser(String login) {
		EntityManager em = null;

		List<Order> lstOrder= null ;
		try{
			em = newEntityManager();
			lstOrder= em.createQuery("select o from ecms.domain.Order o where o.buyer.login=?1").setParameter(1,login).getResultList();

			return lstOrder;
		}finally{
			closeEntityManager(em);
		}			
		
	}
	
	public Order updateOrder(Order o) {
		return this.update(o);
	}
	
	public void deleteOrder(Order o) {
		this.remove(Order.class, o.getId());
	}
/*
	@SuppressWarnings("unchecked")
	public ArrayList<Item> findItemFromCart(Long id) {
	
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session sess = sessionFactory.openSession();

		Query query = 
			sess.createSQLQuery("select i.* from TItem i where i.price = :itemPrice").addEntity(Item.class).setParameter("itemPrice", "400");
		ArrayList<Item> result = (ArrayList<Item>) query.list();
		
		return result;
		
	}
		*/


	@SuppressWarnings("unchecked")
	public List<BaseUser> findAllStoreManager() {
		EntityManager em = null;

		ArrayList<BaseUser> lstSm = null ;
		try{
			em = newEntityManager();
			lstSm = (ArrayList<BaseUser>) em.createQuery("select u from ecms.domain.BaseUser u where u.role=?1").setParameter(1,Role.ROLE_SM).getResultList();
			
			return lstSm;

			
		}finally{
			closeEntityManager(em);
		}
	}
	
	public BaseUser findOneStoreManager(String login) {
		EntityManager em = null;

		try{
			em = newEntityManager();
			return (BaseUser) em.createQuery("select u from ecms.domain.BaseUser u where u.role=?1 and u.login=?2").setParameter(1,Role.ROLE_SM).setParameter(2, login).getSingleResult();
			
		}finally{
			closeEntityManager(em);
		}
	}

	public Item findOneItem(String name) {
		EntityManager em = null;

		Item i = null ;
		try{
			em = newEntityManager();
			i = (Item) em.createQuery("select i from ecms.domain.Item i where i.name=?1").setParameter(1,name).getSingleResult();
			
			return i;

			
		}finally{
			closeEntityManager(em);
		}
	}


	@SuppressWarnings("unchecked")
	public ArrayList<Item> findAllItem() {
		EntityManager em = null;

		try{
			em = newEntityManager();
			return (ArrayList<Item>) em.createQuery("select i from ecms.domain.Item i").getResultList();
		
		}finally{
			closeEntityManager(em);
		}
	}


	@SuppressWarnings("unchecked")
	public ArrayList<BaseUser> findAllCustomer() {
		EntityManager em = null;

		try{
			em = newEntityManager();
			return (ArrayList<BaseUser>) em.createQuery("select u from ecms.domain.BaseUser u where u.role=?1").setParameter(1,Role.ROLE_USER).getResultList();
		}finally{
			closeEntityManager(em);
		}
	}


	public BaseUser findOneCustomer(String login) {
		EntityManager em = null;

		try{
			em = newEntityManager();
			return (BaseUser) em.createQuery("select u from ecms.domain.BaseUser u where u.role=?1 and u.login=?2").setParameter(1,Role.ROLE_USER).setParameter(2, login).getSingleResult();
						
		}finally{
			closeEntityManager(em);
		}
		
	}
}

