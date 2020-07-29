package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.AuthorBsn;
import be.uclouvain.yasmim.entity.Author;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AuthorBean {

    @EJB
    private AuthorBsn authorBsn;

    @ManagedProperty(value="#{param.id}")
    private String id;

    private Author author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Author> getAuthors() {
        return authorBsn.findAuthors();
    }
    
    @PostConstruct
    public void load() {
        if(id != null && !id.isEmpty()) {
            author = authorBsn.findAuthor(this.id);
        }
        else {
            author = new Author();
        }
    }

    public String save() {
        authorBsn.save(author);
        return "authors";
    }

    public String remove(String id) {
        authorBsn.remove(id);
        return "authors";
    }
}