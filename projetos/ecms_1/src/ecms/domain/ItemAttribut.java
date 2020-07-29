package ecms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TItemAttribute", uniqueConstraints = { @UniqueConstraint(columnNames = {"id","name"})})
public class ItemAttribut implements Serializable {


	private static final long serialVersionUID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Column(name = "name",length=100,nullable = false)
	private  String name;
	
	@Column(name = "value",nullable = false)
	private  long  value;
	
	@ManyToOne
	@JoinColumn(name = "storeType")
	private StoreType storeOwner ;


	@ManyToOne
	@JoinColumn(name = "itemOwner" )
	private Item item ;

	public ItemAttribut() {
		super();
	}

	public ItemAttribut(String name, long value, StoreType storeOwner, Item item) {
		super();
		this.name = name;
		this.value = value;
		this.storeOwner = storeOwner;
		this.item = item;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * @return the storeOwner
	 */
	public StoreType getStoreOwner() {
		return storeOwner;
	}

	/**
	 * @param storeOwner the storeOwner to set
	 */
	public void setStoreOwner(StoreType storeOwner) {
		this.storeOwner = storeOwner;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}