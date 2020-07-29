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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TStoreType", uniqueConstraints = { @UniqueConstraint(columnNames = {"storeTypeName"})})
public class StoreType implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Basic(optional = false)
	@Column(name = "storeTypeName", length=200, nullable = false)
	private String storeTypeName;

	@OneToMany( cascade = CascadeType.ALL,  mappedBy = "templateOwner")
	private Collection<Template> templates;
/*
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
			fetch = FetchType.LAZY, mappedBy = "ownerStore")
	private Collection<Store> stores;
	*/
	
	public StoreType(){
		super();
	}

	public StoreType(String storeTypeName, Collection<Template> templates) {
		super();
		this.storeTypeName = storeTypeName;
		this.templates = templates;
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
	 * @return the storeTypeName
	 */
	public String getStoreTypeName() {
		return storeTypeName;
	}

	/**
	 * @param storeTypeName the storeTypeName to set
	 */
	public void setStoreTypeName(String storeTypeName) {
		this.storeTypeName = storeTypeName;
	}

	/**
	 * @return the templates
	 */
	public Collection<Template> getTemplates() {
		return templates;
	}

	/**
	 * @param templates the templates to set
	 */
	public void setTemplates(Collection<Template> templates) {
		this.templates = templates;
	}

//	/**
//	 * @return the stores
//	 */
//	public Collection<Store> getStores() {
//		return stores;
//	}
//
//	
//	/**
//	 * @param stores the stores to set
//	 */
//	public void setStores(Collection<Store> stores) {
//		this.stores = stores;
//	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
