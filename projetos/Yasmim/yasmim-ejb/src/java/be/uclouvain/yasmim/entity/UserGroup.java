package be.uclouvain.yasmim.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represents a group of users.
 * @author Hildeberto
 */
@Entity
@Table(name="access_group")
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private String groupId;
    private String description;
    private Boolean userDefault;
    private List<UserAccount> userAccounts;

    public UserGroup() {}

    public UserGroup(String groupId, String description) {
        this.groupId = groupId;
        this.description = description;
    }

    @Id
    @Column(name="group_id")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy="userGroups")
    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public void addUserAccount(UserAccount userAccount) {
        if(this.userAccounts == null)
            this.userAccounts = new ArrayList<UserAccount>();
        this.userAccounts.add(userAccount);
    }

    @Column(name="user_default")
    public Boolean getUserDefault() {
        return userDefault;
    }

    public void setUserDefault(Boolean userDefault) {
        this.userDefault = userDefault;
    }

    @Override
    public String toString() {
        return groupId;
    }
}