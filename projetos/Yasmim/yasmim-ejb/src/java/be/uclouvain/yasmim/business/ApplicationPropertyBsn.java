package be.uclouvain.yasmim.business;

import be.uclouvain.yasmim.entity.ApplicationProperty;
import be.uclouvain.yasmim.entity.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ApplicationPropertyBsn {

    @PersistenceContext
    EntityManager em;

    public Map<String, String> findApplicationProperties() {
        List<ApplicationProperty> properties = em.createQuery("select ap from ApplicationProperty ap").getResultList();

        if(properties.isEmpty()) {
            Properties[] props = Properties.values();
            ApplicationProperty appProp;
            properties = new ArrayList<ApplicationProperty>();
            for(Properties prop: props) {
                appProp = new ApplicationProperty(prop.getName(), "");
                em.persist(appProp);
                properties.add(appProp);
            }
        }

        Map propertiesMap = new HashMap<String, String>();
        for(ApplicationProperty property:properties) {
            propertiesMap.put(property.getPropertyName(), property.getPropertyValue());
        }
        return propertiesMap;
    }

    public ApplicationProperty findApplicationProperty(Properties property) {
        return em.find(ApplicationProperty.class, property.getName());
    }

    public void save(Map<String, String> properties) {
        Set entryProperties = properties.entrySet();
        Iterator iEntryProperties = entryProperties.iterator();
        Map.Entry entry;
        ApplicationProperty property;
        while(iEntryProperties.hasNext()) {
            entry = (Map.Entry)iEntryProperties.next();
            property = new ApplicationProperty((String)entry.getKey(), (String)entry.getValue());
            em.merge(property);
        }
    }

    public void update(ApplicationProperty property) {
        em.merge(property);
    }
}