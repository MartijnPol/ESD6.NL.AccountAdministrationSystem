package web.bean.register;

import main.domain.User;
import main.domain.UserGroup;
import main.service.UserGroupService;
import main.service.UserService;
import web.core.helper.FrontendHelper;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Thom van de Pas on 29-3-2018
 */
@Named("registrationBean")
@ViewScoped
public class RegistrationBean implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private UserGroupService userGroupService;

    private String username;
    private String password;
    private String emailAddress;
    private List<UserGroup> userGroups;


    public void register() {
        if (!this.username.isEmpty() && !this.password.isEmpty() && !this.emailAddress.isEmpty()) {
            User newUser = new User(this.username, this.password, this.emailAddress);
            this.userService.register(newUser);

            if (this.userGroups != null) {
                for (UserGroup userGroup : this.userGroups) {
                    userGroup.addUser(newUser);
                    this.userService.update(newUser);
                }
            } else {
                UserGroup regularUserGroup = this.userGroupService.getRegularUserGroup();
                regularUserGroup.addUser(newUser);
                this.userGroupService.update(regularUserGroup);
            }

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

    public List<UserGroup> getAllUserGroups() {
        return this.userGroupService.getAllUserGroups();
    }

    //</editor-fold>
}
