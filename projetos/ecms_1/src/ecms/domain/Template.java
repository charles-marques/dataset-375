package ecms.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "TTemplate", uniqueConstraints = { @UniqueConstraint(columnNames = {"id"})})
public class Template implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Basic(optional = false)
	@Column(name = "name",length=200,nullable = false)
	private String name;

	@Basic(optional = false)
	@Column(name ="description",length=200,nullable = true)
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL , optional = true)
	@JoinColumn(name = "templateOwner",unique=false)
	private StoreType templateOwner ;

	
	public Template() {
		super();
	}
	
	public Template(String name, String description, StoreType templateOwner) {
		super();
		this.name = name;
		this.description = description;
		this.templateOwner = templateOwner;
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
	 * @return the templateOwner
	 */
	public StoreType getTemplateOwner() {
		return templateOwner;
	}


	/**
	 * @param templateOwner the templateOwner to set
	 */
	public void setTemplateOwner(StoreType templateOwner) {
		this.templateOwner = templateOwner;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
