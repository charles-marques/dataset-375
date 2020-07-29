package be.uclouvain.yasmim.business;

import be.uclouvain.yasmim.entity.Author;
import be.uclouvain.yasmim.entity.UserAccount;
import com.hildeberto.persistence.EntitySupport;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AuthorBsn {

    @PersistenceContext
    private EntityManager em;

    public Author findAuthor(String id) {
        return em.find(Author.class, id);
    }

    public List<Author> findAuthors() {
        return em.createQuery("select a from Author a order by a.firstName, a.lastName").getResultList();
    }

    public Author transformToAuthor(UserAccount userAccount) {
        Author author = null;
        try {
            author = (Author)em.createQuery("select a from Author a where a.email = :userEmail")
                               .setParameter("userEmail", userAccount.getEmailAddress())
                               .getSingleResult();
        }
        catch(NoResultException nre) {
            author = new Author();
            author.setFirstName(userAccount.getFirstName());
            author.setLastName(userAccount.getLastName());
            author.setEmail(userAccount.getEmailAddress());
            save(author);
        }
        return author;
    }

    public void save(Author author) {
        if(author.getId() == null || author.getId().isEmpty()) {
            author.setId(EntitySupport.generateEntityId());
            em.persist(author);
        }
        else {
            em.merge(author);
        }
    }

    public void remove(String userId) {
        Author author = em.find(Author.class, userId);
        em.remove(author);
    }
}