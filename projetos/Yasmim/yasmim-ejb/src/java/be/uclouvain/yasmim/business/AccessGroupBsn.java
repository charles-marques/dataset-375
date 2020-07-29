package be.uclouvain.yasmim.business;

import be.uclouvain.yasmim.entity.UserGroup;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AccessGroupBsn {

    @PersistenceContext
    EntityManager em;

    public UserGroup findUserDefaultGroup() {
        UserGroup userGroup = null;
        try {
            userGroup = (UserGroup) em.createQuery("select g from UserGroup g where g.userDefault = true").getSingleResult();
        }
        catch(NoResultException nre) {
            userGroup = new UserGroup("users","Default User Group");
            userGroup.setUserDefault(Boolean.TRUE);
            em.persist(userGroup);
        }
        return userGroup;
    }

    public UserGroup findAdministrativeGroup() {
        UserGroup userGroup = em.find(UserGroup.class, "admins");
        if(userGroup == null) {
            userGroup = new UserGroup("admins","Administrative Group");
            em.persist(userGroup);
        }
        return userGroup;
    }
}