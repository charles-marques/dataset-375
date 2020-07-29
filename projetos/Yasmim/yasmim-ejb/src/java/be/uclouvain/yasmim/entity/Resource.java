package be.uclouvain.yasmim.entity;

import com.hildeberto.persistence.EntitySupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type")
@Table(name="resource")
@XmlTransient
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute(required=true)
    private String id;

    @XmlElement(name="full_name",required=true)
    private String name;

    @XmlElementWrapper(name="authorships")
    @XmlElement(name="authorship")
    private List<Authorship> authorships;

    @XmlElementWrapper(name="contributors")
    @XmlElement(name="contributor")
    private List<Contributor> contributors;

    @XmlElement
    private Boolean pending;

    @Id
    public String getId() {
        if(id == null)
            id = EntitySupport.generateEntityId();
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="resource")
    public List<Authorship> getAuthorships() {
        return authorships;
    }

    public void setAuthorships(List<Authorship> authorships) {
        this.authorships = authorships;
    }

    @Transient
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<Author>();
        for(Authorship authorship: authorships) {
            authors.add(authorship.getAuthor());
        }
        return authors;
    }

    @OneToMany(mappedBy="resource")
    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    @Transient
    public Contributor getMainContributor() {
        return this.contributors.get(0);
    }

    public void addContributor(Contributor contributor) {
        if(contributors == null)
            contributors = new ArrayList<Contributor>();
        contributors.add(contributor);
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}