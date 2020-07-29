package ecms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TOrder", uniqueConstraints = { @UniqueConstraint(columnNames = {"id"})})
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;

	@Column(name = "status",length=100,nullable = false)
	private String status;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "orderDate",nullable = false)
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name = "buyer")
	private BaseUser buyer;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Cart cartContent;


	public Order() {
		super();
	}

	public Order(BaseUser buyer, String status, Date dateCreate) {
		super();
		this.buyer = buyer;
		this.status = status;
		this.orderDate = dateCreate;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}


	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	/**
	 * @return the buyer
	 */
	public BaseUser getBuyer() {
		return buyer;
	}


	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(BaseUser buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the cart
	 */
	public Cart getCartContent() {
		return cartContent;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cartContent) {
		this.cartContent = cartContent;
	}
	
}
