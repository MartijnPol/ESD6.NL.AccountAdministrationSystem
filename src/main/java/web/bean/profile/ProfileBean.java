package web.bean.profile;

import main.domain.*;
import main.service.*;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("profileBean")
@ViewScoped
public class ProfileBean extends BaseBean {

    @Inject
    private UserService userService;
    @Inject
    private UserGroupService userGroupService;

    private Long userId;
    private User user;
    private List<UserGroup> userGroups;
    private UserGroup selectedUserGroup;

    @Override
    public void init() {
        this.userGroups = this.userGroupService.findAll();
        this.user = this.userService.findById(this.userId);
    }

    public void onRoleChange(UserGroup selectedUserGroup) {
        this.selectedUserGroup = selectedUserGroup;
    }

    public void update() {
        if (this.user != null && this.selectedUserGroup != null) {
            if (!user.getUserGroups().get(0).equals(this.selectedUserGroup)) {
                for (UserGroup usergroup: userGroups)
                {
                    if(usergroup.getUsers().contains(this.user)) {
                        usergroup.getUsers().remove(this.user);
                        user.getUserGroups().remove(usergroup);
                        this.userGroupService.update(usergroup);
                        this.userService.createOrUpdate(user);
                    }
                }

                UserGroup foundUserGroup = this.userGroupService.findByGroupName(this.selectedUserGroup.getGroupName());
                if (!foundUserGroup.getUsers().contains(this.user)) {
                    foundUserGroup.addUser(this.user);
                }

                this.userGroupService.update(foundUserGroup);
                this.userService.createOrUpdate(this.user);

            } else {
                this.userService.createOrUpdate(this.user);
            }
            FrontendHelper.displaySuccessSmallMessage("De gebruiker is succesvol geüpdatet.");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public UserGroup getSelectedUserGroup() {
        return selectedUserGroup;
    }

    public void setSelectedUserGroup(UserGroup selectedUserGroup) {
        this.selectedUserGroup = selectedUserGroup;
    }


    //</editor-fold>
}
