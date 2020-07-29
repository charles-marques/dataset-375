package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.AuthorBsn;
import be.uclouvain.yasmim.business.ResourceBsn;
import be.uclouvain.yasmim.business.UserAccountBsn;
import be.uclouvain.yasmim.entity.Author;
import be.uclouvain.yasmim.entity.Authorship;
import be.uclouvain.yasmim.entity.Contributor;
import be.uclouvain.yasmim.entity.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class ImageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private UserAccountBsn userAccountBsn;

    @EJB
    private AuthorBsn authorBsn;

    @EJB
    private ResourceBsn resourceBsn;

    @ManagedProperty(value="#{request}")
    private HttpServletRequest request;

    private String id;
    private Image image;
    private List<Author> authors;
    private Author selectedAuthor;
    private Author newAuthor;

    public ImageBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("imageBean");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Image> getImages() {
        return resourceBsn.findImages(image.getMainContributor().getUser());
    }

    public List<Author> getAuthors() {
        if(authors == null)
            authors = new ArrayList<Author>();
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<SelectItem> getExistingAuthors() {
        List<Author> existingAuthors = authorBsn.findAuthors();
        SelectItem selectItem = null;
        List<SelectItem> selectItens = new ArrayList<SelectItem>();
        boolean alreadyAdded = false;
        for(Author author:existingAuthors) {
            alreadyAdded = false;
            if(authors != null) {
                for(Author addedAuthor:authors) {
                    if(author.equals(addedAuthor)) {
                        alreadyAdded = true;
                        break;
                    }
                }
            }
            if(!alreadyAdded) {
                selectItem = new SelectItem(author.getId(), author.getCompleteName());
                selectItens.add(selectItem);
            }
        }
        return selectItens;
    }

    public String getSelectedAuthor() {
        if(selectedAuthor == null)
            return null;
        
        return selectedAuthor.getId();
    }

    public void setSelectedAuthor(String selectedAuthor) {
        if(selectedAuthor == null || selectedAuthor.isEmpty())
            return;
        
        this.selectedAuthor = authorBsn.findAuthor(selectedAuthor);
    }

    public Author getNewAuthor() {
        if(newAuthor == null)
            newAuthor = new Author();
        return newAuthor;
    }

    public void setNewAuthor(Author newAuthor) {
        this.newAuthor = newAuthor;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @PostConstruct
    public void load() {
        image = new Image();

        if(request != null) {
            Contributor contributor = new Contributor();
            contributor.setDate(Calendar.getInstance().getTime());
            contributor.setResource(image);
            String userId = request.getRemoteUser();
            contributor.setUser(userAccountBsn.findUserAccount(userId));
            image.addContributor(contributor);
        }
    }

    private void addAuthor(Author author) {
        if(authors == null)
            authors = new ArrayList<Author>();
        authors.add(author);
    }

    public String addExistingAuthor() {
        addAuthor(selectedAuthor);
        selectedAuthor = null;
        return "image";
    }

    public String addContributorAsAuthor() {
        Author contributorAuthor = authorBsn.transformToAuthor(image.getMainContributor().getUser());
        boolean alreadyAdded = false;
        for(Author addedAuthor:authors) {
            if(contributorAuthor.equals(addedAuthor)) {
                alreadyAdded = true;
                break;
            }
        }
        if(!alreadyAdded)
            addAuthor(contributorAuthor);
        return "image";
    }

    public String addNewAuthor() {
        if(newAuthor.getFirstName() == null || newAuthor.getFirstName().isEmpty())
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("First Name of the new author required."));

        if(newAuthor.getLastName() == null || newAuthor.getLastName().isEmpty())
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Last Name of the new author required."));

        if(FacesContext.getCurrentInstance().getMessageList().size() <= 0) {
            authorBsn.save(newAuthor);
            addAuthor(newAuthor);
            newAuthor = null;
        }
        return "image";
    }

    public String save() {
        if(image.getName() == null || image.getName().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Name of the image required."));
            return "image";
        }

        Authorship authorship = null;
        List<Authorship> authorships = new ArrayList<Authorship>();
        int order = 1;
        for(Author author: authors) {
            authorship = new Authorship(author, image);
            authorship.setAuthorshipOrder(order++);
            authorships.add(authorship);
        }
        image.setAuthorships(authorships);
        resourceBsn.save(image);

        return "image_annotation";
    }

    public String finish() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("imageBean");
        return "images";
    }

    public String cancel() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("imageBean");
        return "images";
    }

    public String open(String id) {
        this.setId(id);
        
        this.image = resourceBsn.findImage(getId());
        this.authors = image.getAuthors();

        return "image";
    }

    public String remove(String id) {
        resourceBsn.remove(id);
        return "images";
    }
}