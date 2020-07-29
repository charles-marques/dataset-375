package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.UserAccountBsn;
import be.uclouvain.yasmim.entity.UserAccount;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class SecurityBackingBean {
    @EJB
    private UserAccountBsn userAccountBsn;

    @ManagedProperty(value="#{facesContext}")
    private FacesContext facesContext;

    @ManagedProperty(value="#{sessionScope}")
    private Map<String, Object> sessionMap;

    public UserAccount getSignedUser() {
        return (UserAccount) sessionMap.get("signedUser");
    }

    public void setSignedUser(UserAccount signedUser) {
        sessionMap.remove("signedUser");
        if(null != signedUser) {
            sessionMap.put("signedUser", signedUser);
        }
    }

    public boolean isUserSignedIn() {
        return sessionMap.containsKey("signedUser");
    }

    public String login() {
        if(userAccountBsn.noAccount()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You are the first user of the system. Please, register the administrator user."));
            return "registration";
        }
        else
            return "login";
    }

    public String logout() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        try {
            request.logout();
            session.invalidate();
        }
        catch(ServletException se) {
            return "index";
        }
        return "/login";
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
}