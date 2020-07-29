package ecms.service;

import java.util.ArrayList;
import java.util.List;

import ecms.domain.BaseUser;
import ecms.domain.Cart;
import ecms.domain.Item;
import ecms.domain.Order;

public interface IStoreManagerSer {
	
	
	public void addCustomerOrder(Order o);
	
	public List<Order> getCustomerOrder();
	
	public Order updateCustomerOrder(Order o);
	
	//
	
	public void addCustomerCart(Cart c);
	
	public Cart getCustomerCart(BaseUser c);
	
	public Cart updateCustomerCart(Cart c);
	
	public void deleteCustomerCart(Cart c);

	public ArrayList<Item> listAllItem();

	public void addCustomer(BaseUser customer);

	public boolean existCustomerByLogin(String login);
	
	
}
