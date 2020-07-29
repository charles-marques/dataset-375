package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.ApplicationPropertyBsn;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ApplicationPropertiesBean {

    @EJB
    private ApplicationPropertyBsn applicationPropertyBsn;

    private Map applicationProperties;

    public Map getApplicationProperties() {
        if(applicationProperties == null)
             applicationProperties = applicationPropertyBsn.findApplicationProperties();
        return applicationProperties;
    }

    public void setApplicationProperties(Map applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String save() {
        applicationPropertyBsn.save(applicationProperties);
        return "index";
    }
}