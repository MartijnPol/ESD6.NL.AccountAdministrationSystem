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

    public User create(User user) {
        return this.userDao.create(user);
    }

    public User update(User user) {
        return this.userDao.update(user);
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

    public User findbyUsername(String username) {
        return this.userDao.findByUsername(username);
    }
}
