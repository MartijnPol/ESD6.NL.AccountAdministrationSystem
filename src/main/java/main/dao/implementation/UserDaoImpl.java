package main.dao.implementation;

import main.dao.JPA;
import main.dao.UserDao;
import main.domain.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/
@Stateless
@JPA
public class UserDaoImpl extends GenericDaoJPAImpl<User> implements UserDao {

    public UserDaoImpl() {

    }

    public User findByCredentials(String username, String password) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("user.findByCredentials", User.class)
                .setParameter("username", username)
                .setParameter("password", password);

        return oneResult(query);
    }
}