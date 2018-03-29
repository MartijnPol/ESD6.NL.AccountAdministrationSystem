package main.service;

import main.dao.JPA;
import main.dao.UserGroupDao;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

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

}
