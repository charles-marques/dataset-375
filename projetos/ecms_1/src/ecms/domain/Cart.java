package ecms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TCart", uniqueConstraints = { @UniqueConstraint(columnNames = {"id","description"})})
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "description",length=200,nullable = false)
	private String description ;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private BaseUser owner;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "cart" )
//	private List<Item> items ;
	
//	@ManyToMany(fetch = FetchType.LAZY,
//		cascade = { CascadeType.MERGE, CascadeType.PERSIST }
//		)
//	@JoinTable(
//		name = "Cart_Item",
//		joinColumns = { @JoinColumn(name = "Cart.id") },
//		inverseJoinColumns = { @JoinColumn(name = "Item.id") }
//	)
	
//	@ManyToMany(mappedBy = "carts")
//	private List<Item> items;
	
	@ManyToMany(
	//	cascade={CascadeType.PERSIST, CascadeType.MERGE},
		fetch=FetchType.LAZY,
		mappedBy="carts",
		targetEntity=Item.class
	)
	private List<Item> items;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cartContent")
	@PrimaryKeyJoinColumn
	private Order order;
 
	public Cart(){
		super();
		items = new ArrayList<>();
	}
		
	public Cart(String desc, BaseUser owner, List<Item> items){
		super();
		this.description = desc;
		if(items != null) {
			this.items = items;
		} else {
			this.items = new ArrayList<>();
		}
		this.owner = owner;
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
	 * @return the owner
	 */
	public BaseUser getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(BaseUser owner) {
		this.owner = owner;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}



}
