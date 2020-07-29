package ecms.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
 

@Embeddable
public class Address implements Serializable {

 
	private static final long serialVersionUID = 1L;
	
	@Column(length = 100) // the next column is 50 characters in size
    private String streetAddress1;

	@Column(length = 100)
    private String streetAddress2;
    
	@Column(length = 100)
    private String city;
    /**
     * Code postal au format String (pour prendre en charge les CEDEX)
     */
    @Column(length = 9)
    private String zip;
    /**
     * Pays
     */
    @Column(length = 100)
    private String country;
 
    /**
     * Constructeur sans argument du JavaBean Address
     */
    public Address() {
    	super();
    }
 
    /**
     * Constructeur avec argument de la classe Address
     * @param sa1
     * @param sa2
     * @param city
     * @param country
     * @param zip
     */
    public Address(final String sa1, final String sa2, final String city,
            final String country, final String zip) {
    	super();
        setStreetAddress1(sa1);
        setStreetAddress2(sa2);
        setCity(city);
        setCountry(country);
        setZip(zip);
    }
    
    @Override
    public String toString() {
    	return "Address("+streetAddress1+","+streetAddress2+","+zip+","+city+","+country+")";
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(final String city) {
        this.city = city;
    }
 
    public String getStreetAddress1() {
        return streetAddress1;
    }
 
    public void setStreetAddress1(final String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }
 
    public String getStreetAddress2() {
        return streetAddress2;
    }
 
    public void setStreetAddress2(final String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }
 
    public String getZip() {
        return zip;
    }
 
    public void setZip(final String zip) {
        this.zip = zip;
    }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
 

}