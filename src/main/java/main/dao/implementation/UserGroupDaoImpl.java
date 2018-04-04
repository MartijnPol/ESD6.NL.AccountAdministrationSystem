package main.dao.implementation;

import main.dao.JPA;
import main.dao.UserGroupDao;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author Thom van de Pas on 29-3-2018
 */
@Stateless
@JPA
public class UserGroupDaoImpl extends GenericDaoJPAImpl<UserGroup> implements UserGroupDao {

    public UserGroupDaoImpl() {
    }

    @Override
    public UserGroup findByGroupName(String groupName) {
        TypedQuery<UserGroup> query = getEntityManager().createNamedQuery("userGroup.findByGroupName", UserGroup.class)
                .setParameter("groupName", groupName);

        return oneResult(query);
    }

    @Override
    public UserGroup getRegularUserGroup() {
        TypedQuery<UserGroup> query = getEntityManager().createNamedQuery("userGroup.getRegularUserGroup", UserGroup.class);

        return oneResult(query);
    }
}
