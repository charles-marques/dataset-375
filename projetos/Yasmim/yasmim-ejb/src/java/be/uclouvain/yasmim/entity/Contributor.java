package be.uclouvain.yasmim.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@IdClass(ContributorId.class)
@Table(name="contributor")
@XmlType(name="ContributorType", propOrder={"date","user"})
@XmlAccessorType(XmlAccessType.NONE)
public class Contributor implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement
    private UserAccount user;

    private Resource resource;

    @XmlElement
    private Date date;

    public Contributor(){}

    public Contributor(UserAccount user, Resource resource) {
        this.user = user;
        this.resource = resource;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="user_account")
    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="resource")
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contributor other = (Contributor) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        if (this.resource != other.resource && (this.resource == null || !this.resource.equals(other.resource))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 97 * hash + (this.resource != null ? this.resource.hashCode() : 0);
        return hash;
    }
}