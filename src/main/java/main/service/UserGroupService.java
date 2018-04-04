package main.service;

import main.dao.JPA;
import main.dao.UserGroupDao;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Thom van de Pas on 29-3-2018
 */
@Stateless
public class UserGroupService {

    @Inject
    @JPA
    private UserGroupDao userGroupDao;

    public UserGroupService() {
    }

    /**
     * Create a new UserGroupService.
     *
     * @param userGroupDao is the UserGruopDao that should communicate to the DataBase.
     */
    public UserGroupService(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    /**
     * Create a new UserGroup.
     *
     * @param userGroup is the UserGroup that has to be created.
     * @returns the created UserGroup.
     */
    public UserGroup create(UserGroup userGroup) {
        return this.userGroupDao.create(userGroup);
    }

    /**
     * Updates a UserGroup.
     * @param userGroup is the UserGroup to be updated.
     * @returns the updated UserGroup.
     */
    public UserGroup update(UserGroup userGroup) {
        return this.userGroupDao.update(userGroup);
    }

    /**
     * Finds a group by it's groupName
     * @param groupName is the name to be found.
     * @returns the found UserGroup.
     */
    public UserGroup findByGroupName(String groupName) {
        return this.userGroupDao.findByGroupName(groupName);
    }

    /**
     * Gets the UserGroup for Regular users.
     * @returns the Regular User UserGroup.
     */
    public UserGroup getRegularUserGroup() {
        return this.userGroupDao.getRegularUserGroup();
    }

    public List<UserGroup> getAllUserGroups() {
        return this.userGroupDao.findAll();
    }
}
