package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.UserAccountBsn;
import be.uclouvain.yasmim.entity.UserAccount;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class UserAccountBean {

    @EJB
    private UserAccountBsn userAccountBsn;

    @ManagedProperty(value="#{request}")
    private HttpServletRequest request;

    @ManagedProperty(value="#{param.userId}")
    private String userId;

    private String firstName;
    private String lastName;
    private int sex;
    private Date birthDate;
    private String password;
    private String confirmPassword;
    private String confirmationCode;

    private List<UserAccount> userAccounts;
    
    public UserAccountBean() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAccount> getUserAccounts() {
        if(userAccounts == null)
            userAccounts = userAccountBsn.findUserAccounts();
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isConfirmed() {
        if(confirmationCode == null || confirmationCode.isEmpty())
            return true;

        return false;
    }

    public void validateUserId(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String usrId = (String) value;
        if(-1 == usrId.indexOf("@")) {
            throw new ValidatorException(new FacesMessage("Invalid email address."));
        }
    }

    @PostConstruct
    public void load() {
        if(userId != null && !userId.isEmpty()) {
            UserAccount userAccount = userAccountBsn.findUserAccount(this.userId);
            this.firstName = userAccount.getFirstName();
            this.lastName = userAccount.getLastName();
            this.sex = userAccount.getSex();
            this.birthDate = userAccount.getBirthDate();
            this.confirmationCode = userAccount.getConfirmationCode();
        }
    }

    public String register() {
        if(!confirmPassword.equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The password confirmation does not confer."));
            return "registration";
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(this.userId);
        userAccount.setFirstName(this.firstName);
        userAccount.setLastName(this.lastName);
        userAccount.setSex(this.sex);
        userAccount.setBirthDate(this.birthDate);
        userAccount.setPassword(this.password);

        FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String serverAddress = serverName + (serverPort != 80?":"+ serverPort:"") + (contextPath.equals("")?"":contextPath);
        try {
            userAccountBsn.register(userAccount, serverAddress);
        }
        catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(userId, new FacesMessage(e.getCause().getMessage()));
            return "registration";
        }
        FacesContext.getCurrentInstance().addMessage(userId, new FacesMessage("An email confirmation was sent to "+ this.userId +". Please, check your inbox and confirm your email address before the initial sign in."));
        return "login";
    }

    public String save() {
        UserAccount userAccount = userAccountBsn.findUserAccount(this.userId);
        userAccount.setUserId(this.userId);
        userAccount.setFirstName(this.firstName);
        userAccount.setLastName(this.lastName);
        userAccount.setSex(this.sex);
        userAccount.setBirthDate(this.birthDate);
        userAccountBsn.save(userAccount);
        return "users";
    }

    public String remove() {
        userAccountBsn.remove(this.userId);
        return "users";
    }

    public String changePassword() {
        if(!confirmPassword.equals(password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The password confirmation does not confer."));
            return "user";
        }
        userAccountBsn.changePassword(this.userId, this.password);
        return "users";
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}