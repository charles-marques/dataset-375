/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.steeplesoft.frenchpress.beans;

import com.steeplesoft.frenchpress.model.User;
import com.steeplesoft.frenchpress.service.UserService;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;
import org.richfaces.component.UIDataTable;

/**
 *
 * @author jdlee
 */
@Model
public class UserBean {
    @Inject
    private UserService userService;
    private UIDataTable dataTable;
    private User user = new User();
    
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    public void loadUser() {
        user = userService.getUser(user.getId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String save() {
        userService.createUser(user);
        return "/admin/users/index?faces-redirect=true";
    }
    
    public String update() {
        userService.updateUser(user);
        return "/admin/users/index?faces-redirect=true";
    }
    
    public String delete() {
        userService.deleteUser(user);
        return null;
    }

    public UIDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(UIDataTable dataTable) {
        this.dataTable = dataTable;
    }
}
