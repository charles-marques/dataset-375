package ecms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TItem", uniqueConstraints = { @UniqueConstraint(columnNames = {"id","name"})})
public class Item implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;

	@Column(name = "name",length=200,nullable = false)
	private String name;

	@Column(name = "tva")
	private double tva; 

	@Column(name = "stock",length=200,nullable = false)
	private int stock;

	@Column(name = "description" ,length=200,nullable = false)
	private String description ;

	@Column(name = "price")
	private double price ;

	@ManyToOne
	@JoinColumn(name = "store")
	private Store store;

	@ManyToMany(
		targetEntity=ecms.domain.Cart.class,
		fetch=FetchType.EAGER
		//cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinTable(
		name="Item_Cart",
		joinColumns=@JoinColumn(name="Item_id"),
		inverseJoinColumns=@JoinColumn(name="Cart_id")
	)
	private List<Cart> carts;
	
//	@ManyToMany
//	@JoinColumn(name="toCart")
//	private  Cart cart ;

	@Column(name = "categorie")
	private String categorie;

	public Item(){
		super();
		carts = new ArrayList<>();
		
	}
	
	public Item(String name, double tva, int stock, String description,
				double price, Store store, List<Cart> carts, String categorie) {
		super();
		this.name = name; 
		this.tva  = tva;
		this.stock = stock; 
		this.description = description;
		this.price = price;
		this.store = store;
		if(carts != null) {
			this.carts = carts;
		} else {
			this.carts = new ArrayList<>();
			
		}
		this.categorie = categorie;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stock;
		temp = Double.doubleToLongBits(tva);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (stock != other.stock)
			return false;
		if (Double.doubleToLongBits(tva) != Double.doubleToLongBits(other.tva))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tva
	 */
	public double getTva() {
		return tva;
	}

	/**
	 * @param tva the tva to set
	 */
	public void setTva(double tva) {
		this.tva = tva;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the carts
	 */
	public List<Cart> getCarts() {
		return carts;
	}

	/**
	 * @param carts the carts to set
	 */
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	
}
