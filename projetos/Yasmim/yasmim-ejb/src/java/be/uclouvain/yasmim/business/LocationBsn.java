package be.uclouvain.yasmim.business;

import be.uclouvain.yasmim.entity.Location;
import be.uclouvain.yasmim.entity.Resource;
import com.hildeberto.persistence.EntitySupport;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class LocationBsn {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ResourceBsn resourceBsn;

    public Location findResourceLocation(Resource resource) {
        List<Location> locations = em.createQuery("select l from Location l where l.resource = :resource")
                                     .setParameter("resource", resource)
                                     .getResultList();
        if(locations.size() > 0)
            return locations.get(0);
        else
            return null;
    }

    public void save(Location location) {
        Location existingLocation = findResourceLocation(location.getResource());
        if(existingLocation != null) {
            location.setId(existingLocation.getId());
            em.detach(existingLocation);
        }

        if(location.getId() == null || location.getId().isEmpty()) {
            location.setId(EntitySupport.generateEntityId());
            em.persist(location);
        }
        else {
            em.merge(location);
        }
        
        resourceBsn.save(location.getResource());
    }
}