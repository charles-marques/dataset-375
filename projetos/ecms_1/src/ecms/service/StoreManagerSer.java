package ecms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ecms.domain.BaseUser;
import ecms.domain.Cart;
import ecms.domain.Item;
import ecms.domain.Order;
import ecms.domain.Role;
import ecms.repository.Dao;

@Service
public class StoreManagerSer implements IStoreManagerSer {

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
	public void addCustomerOrder(Order o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getCustomerOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateCustomerOrder(Order o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCustomerCart(Cart c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cart getCustomerCart(BaseUser c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart updateCustomerCart(Cart c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomerCart(Cart c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Item> listAllItem() {
		return ecmsDao.findAllItem();
	}

	@Override
	public boolean existCustomerByLogin(String login) {
		try {
			ecmsDao.findOneCustomer(login);
			return true;
		
		} catch(NoResultException e) {
			return false;
		}
	}

	@Override
	public void addCustomer(BaseUser customer) {
		customer.setRole(Role.ROLE_USER);
		ecmsDao.addUser(customer);
	}

}
