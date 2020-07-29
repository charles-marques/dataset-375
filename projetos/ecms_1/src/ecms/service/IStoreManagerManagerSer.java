package ecms.service;

import java.util.ArrayList;
import java.util.List;

import ecms.domain.BaseUser;
import ecms.domain.Item;
import ecms.domain.Order;

public interface IStoreManagerManagerSer {

	public void addItem(Item i);
	
	public Item updateItem(Item i);
	
	public Item getItemByName(String name);
	
	public List<Item> getItemByTag(String tag);
	
	public void deleteItem(Item i);
	
	//	
	
	public void addCustomer(BaseUser c); 
	
	public BaseUser updateCustomer(BaseUser c);
	
	public BaseUser getCustomer(String login);
	
	public void deleteCustomer(BaseUser c);
	
	//
	
	public List<Order> getOrder();
	
	public Order updateOrder(Order o);
	
	public void deleteOrder(Order o);

	boolean existItemByName(String name);

	public ArrayList<Item> listAllItem();

	ArrayList<BaseUser> listAllCustomer();
	
	
}
