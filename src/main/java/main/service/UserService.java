package main.service;

import main.dao.JPA;
import main.dao.UserDao;
import main.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/
@Stateless
public class UserService {

    @Inject
    @JPA
    private UserDao userDao;

    public UserService() {
    }

    public User createOrUpdate(User user) {
        return this.userDao.createOrUpdate(user);
    }

    public void delete(User user) {
        this.userDao.delete(user);
    }

    public void deleteById(Long id) {
        this.userDao.deleteById(id);
    }

    public User findById(Long id) {
        return this.userDao.findById(id);
    }

    public List<User> findAll() {
        return this.userDao.findAll();
    }

    /**
     * Find a User by its username.
     * @param username is the username of the User to be found.
     * @returns the found User or null.
     */
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    /**
     * Register a new User in the system so that the User can login using these credentials.
     * @param user is the new User to be registered.
     * @returns the created User.
     */
    public User register(User user) {
        User foundUser = this.findByUsername(user.getUsername());
        if (foundUser == null ) {
            return this.createOrUpdate(user);
        }
        return null;
    }
}
