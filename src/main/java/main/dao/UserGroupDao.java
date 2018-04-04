package main.dao;

import main.domain.UserGroup;

/**
 * @author Thom van de Pas on 29-3-2018
 */
public interface UserGroupDao extends GenericDao<UserGroup> {

    UserGroup findByGroupName(String groupName);

    UserGroup getRegularUserGroup();
}
