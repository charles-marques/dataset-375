package ecms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ecms.domain.BaseUser;
import ecms.domain.Item;
import ecms.domain.Order;
import ecms.repository.Dao;

@Service
public class StoreManagerManagerSer implements IStoreManagerManagerSer {

	
	private Dao ecmsDao;
	
	public void setEcmsDao(Dao ecmsDao) {
		this.ecmsDao = ecmsDao;
	}
	public Dao getEcmsDao() {
		return this.ecmsDao;
	}
	
	@PostConstruct
	public void init() { 
		ecmsDao = new Dao();
		ecmsDao.init();
		
	}
	
	@PreDestroy
	public void close() {
		ecmsDao.close();
		
	}

	@Override
	public void addItem(Item i) {
		ecmsDao.addItem(i);
		
	}

	@Override
	public Item updateItem(Item i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean existItemByName(String name) {
		
		try {
			ecmsDao.findOneItem(name);
			return true;
		
		} catch(NoResultException e) {
			return false;
		}
	}

	@Override
	public ArrayList<Item> getItemByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(Item i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCustomer(BaseUser c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseUser updateCustomer(BaseUser c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseUser getCustomer(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(BaseUser c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrder(Order o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Order o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Item> listAllItem() {
		return ecmsDao.findAllItem();
	}
	@Override
	public ArrayList<BaseUser> listAllCustomer() {
		return ecmsDao.findAllCustomer();
	}
	


}
