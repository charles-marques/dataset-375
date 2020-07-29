package be.uclouvain.yasmim.business;

import be.uclouvain.yasmim.entity.Image;
import be.uclouvain.yasmim.entity.Resource;
import be.uclouvain.yasmim.entity.UserAccount;
import com.hildeberto.persistence.EntitySupport;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ResourceBsn {

    @PersistenceContext
    EntityManager em;

    public Resource findResource(String id) {
        return em.find(Resource.class, id);
    }

    public Image findImage(String id) {
        return em.find(Image.class, id);
    }

    public List<Image> findImages(UserAccount userAccount) {
        List<Image> images = em.createQuery("select c.resource from Contributor c where type(c.resource) = Image and c.user = :user")
                               .setParameter("user", userAccount)
                               .getResultList();
        return images;
    }

    public void save(Resource resource) {
        if(resource.getId() == null || resource.getId().isEmpty()) {
            resource.setId(EntitySupport.generateEntityId());
            resource.setPending(true);
            em.persist(resource);
        }
        else {
            em.merge(resource);
        }
    }

    public void remove(String resourceId) {
        Resource resource = em.find(Resource.class, resourceId);
        em.remove(resource);
    }
}