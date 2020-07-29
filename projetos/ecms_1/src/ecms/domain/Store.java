package ecms.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TStore", uniqueConstraints = { @UniqueConstraint(columnNames = {"id"})})
public class Store  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Basic(optional = false)
	@Column(name = "storeName",length=200, nullable = false)
	private String  storeName ;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "storeManager")
	private BaseUser storeManager;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "store")
	private Collection<Item> items;
	
	
	public Store() {
		super();
	}
	
	public Store(String storeName, BaseUser storeManager, Collection<Item> items) {
		super();
		this.storeName= storeName;
		this.storeManager = storeManager;
		this.items = items;
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
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}


	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	/**
	 * @return the storeManager
	 */
	public BaseUser getStoreManager() {
		return storeManager;
	}


	/**
	 * @param storeManager the storeManager to set
	 */
	public void setStoreManager(BaseUser storeManager) {
		this.storeManager = storeManager;
	}


	/**
	 * @return the items
	 */
	public Collection<Item> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(Collection<Item> items) {
		this.items = items;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

/*	
	@ManyToOne(optional = true)
	@JoinColumn(name = "OWNERSTORE")
	private StoreType ownerStore;*/

}
