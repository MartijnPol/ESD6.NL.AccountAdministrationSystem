package main.dao;

import main.domain.User;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/
public interface UserDao extends GenericDao<User> {

    User findByUsername(String username);
}
