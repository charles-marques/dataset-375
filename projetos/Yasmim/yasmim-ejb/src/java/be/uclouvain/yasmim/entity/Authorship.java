package be.uclouvain.yasmim.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@IdClass(AuthorshipId.class)
@Table(name="authorship")
@XmlType(name="AuthorshipType")
@XmlAccessorType(XmlAccessType.NONE)
public class Authorship implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement
    private Author author;
    
    private Resource resource;

    @XmlElement(name="order")
    private Integer authorshipOrder;

    public Authorship() {}

    public Authorship(Author author, Resource resource) {
        this.author = author;
        this.resource = resource;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="author")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    @Column(name="authorship_order", nullable=false)
    public Integer getAuthorshipOrder() {
        return authorshipOrder;
    }

    public void setAuthorshipOrder(Integer order) {
        this.authorshipOrder = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Authorship other = (Authorship) obj;
        if (this.author != other.author && (this.author == null || !this.author.equals(other.author))) {
            return false;
        }
        if (this.resource != other.resource && (this.resource == null || !this.resource.equals(other.resource))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.author != null ? this.author.hashCode() : 0);
        hash = 11 * hash + (this.resource != null ? this.resource.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.author.getFirstName();
    }
}