package be.uclouvain.yasmim.business;

import be.ac.ucl.tele.yasmim.util.Base64Encoder;
import be.uclouvain.yasmim.entity.UserAccount;
import be.uclouvain.yasmim.entity.UserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
@LocalBean
public class UserAccountBsn {

    @Resource(name = "mail/yasmim")
    private Session mailYasmim;

    @EJB
    private AccessGroupBsn accessGroupBsn;
    
    @PersistenceContext
    EntityManager em;

    public boolean existingAccount(UserAccount userAccount) {
        UserAccount existing = findUserAccount(userAccount.getUserId());
        return existing != null;
    }

    /** Returns true is there is no account registered in the database. */
    public boolean noAccount() {
        Long totalUserAccounts = (Long)em.createQuery("select count(u) from UserAccount u").getSingleResult();
        if(totalUserAccounts == 0)
            return true;
        else
            return false;
    }

    public UserAccount findUserAccount(String userId) {
        return em.find(UserAccount.class, userId);
    }

    public List<UserAccount> findUserAccounts() {
        return em.createQuery("select ua from UserAccount ua order by ua.firstName").getResultList();
    }

    public void register(UserAccount userAccount, String serverAddress) {
        if(noAccount()) {
            UserGroup adminGroup = accessGroupBsn.findAdministrativeGroup();
            if(adminGroup != null)
                userAccount.addUserGroup(adminGroup);
        }
        else {
            if(existingAccount(userAccount))
                throw new PersistenceException("An account with the informed email already exists.");

            UserGroup userDefaultGroup = accessGroupBsn.findUserDefaultGroup();
            if(userDefaultGroup != null)
                userAccount.addUserGroup(userDefaultGroup);
        }
        userAccount.setConfirmationCode(generateConfirmationCode());
        askForMailConfirmation(userAccount, serverAddress);
        em.persist(userAccount);
    }

    public void save(UserAccount userAccount) {
        em.merge(userAccount);
    }

    public void askForMailConfirmation(UserAccount userAccount, String serverAddress) {
        Message msg = new MimeMessage(mailYasmim);

        try {
            msg.setSubject("[yasmim] Email confirmation");
            msg.setRecipient(RecipientType.TO, new InternetAddress(userAccount.getEmailAddress(), userAccount.toString()));
            msg.setText("Hello "+ userAccount.getFirstName() +", \n\nIt seems that you have registered on the Yasmim application.\n\n" +
                        "We just would like to confirm your email address to be able to get in touch " +
                        "with you afterwards. You just have to click on the address below:\n\n" +
                        "http://"+ serverAddress + "/EmailConfirmation?code="+ userAccount.getConfirmationCode() +" (If it doesn't appear as a link on your " +
                        "favorite email client, please copy and paste this address in your web browser.)\n\n" +
                        "If you haven't registered on the Yasmim application, please ignore this message " +
                        "and accept our apologies.\n\n" +
                        "With our best regards,\n\n" +
                        "Yasmim Administration Team ;-)");
            Transport.send(msg);
        }
        catch(MessagingException me) {
            throw new PersistenceException("Error when sending the mail confirmation. The registration was not finalized.",me);
        }
        catch(UnsupportedEncodingException uee) {
            throw new PersistenceException("Error when sending the mail confirmation. The registration was not finalized.", uee);
        }
    }

    public void confirmEmail(String confirmationCode) {
        try {
            UserAccount userAccount = (UserAccount)em.createQuery("select ua from UserAccount ua where ua.confirmationCode = :code").setParameter("code", confirmationCode).getSingleResult();
            userAccount.setConfirmationCode("");
            userAccount.setPassword(encryptPassword(userAccount.getPassword()));
        }
        catch(NoResultException nre) {
            throw new IllegalArgumentException("Confirmation code does not match any existing pendent account.", nre.getCause());
        }
    }

    private String encryptPassword(String string) {
        MessageDigest md = null;
        byte stringBytes[] = null;
        try {
            md = MessageDigest.getInstance("MD5");
            stringBytes = string.getBytes("UTF8");
        }
        catch(NoSuchAlgorithmException nsae) {
            throw new SecurityException("The Requested encoding algorithm was not found in this execution platform.", nsae);
        }
        catch(UnsupportedEncodingException uee) {
            throw new SecurityException("UTF8 is not supported in this execution platform.", uee);
        }
         
        byte stringCriptBytes[] = md.digest(stringBytes);
        char[] encoded = Base64Encoder.encode(stringCriptBytes);
        return String.valueOf(encoded);
    }

    private String generateConfirmationCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public void changePassword(String userId, String newPassword) {
        UserAccount userAccount = em.find(UserAccount.class, userId);
        userAccount.setPassword(encryptPassword(newPassword));
    }

    public void remove(String userId) {
        UserAccount userAccount = em.find(UserAccount.class, userId);
        em.remove(userAccount);
    }
}