package web.bean.profile;

import main.domain.Car;
import main.domain.CarTracker;
import main.domain.User;
import main.domain.UserGroup;
import main.service.UserGroupService;
import main.service.UserService;
import org.primefaces.event.SelectEvent;
import web.core.helper.FrontendHelper;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Thom van de Pas on 29-3-2018
 */
@Named
@ViewScoped
public class ProfileOverviewBean implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private UserGroupService userGroupService;

    private String username;
    private String password;
    private String emailAddress;
    private List<UserGroup> userGroups;
    private List<User> users;
    private List<User> filteredUsers;
    private User selectedUser;

    @PostConstruct
    public void init() {
        users = userService.findAll();
    }

    public void onRowSelect(SelectEvent event) {
        User selectedUser = (User) event.getObject();
        RedirectHelper.redirect("/pages/profile/profile.xhtml?userId=" + selectedUser.getId());
    }

    public void register() {
        if (!this.username.isEmpty() && !this.password.isEmpty() && !this.emailAddress.isEmpty()) {
            User newUser = new User(this.username, this.password, this.emailAddress);
            this.userService.register(newUser);

            if (this.userGroups != null) {
                for (UserGroup userGroup : this.userGroups) {
                    userGroup.addUser(newUser);
                    this.userService.createOrUpdate(newUser);
                }
            } else {
                UserGroup regularUserGroup = this.userGroupService.getRegularUserGroup();
                regularUserGroup.addUser(newUser);
                this.userGroupService.update(regularUserGroup);
            }
            RedirectHelper.redirect("/pages/profile/dashboard.xhtml");
            FrontendHelper.displaySuccessSmallMessage("Succes! Gebruiker: " + newUser.getUsername() + " is toegevoegd!");
        } else {
            FrontendHelper.displayErrorSmallMessage("Er is iets mis gegaan bij het registreren van een nieuwe " +
                    "gebruiker, probeer het opnieuw.");
        }
    }

    //<editor-fold defaultState="collapsed" desc="Getters/Setters">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    //</editor-fold>
}
