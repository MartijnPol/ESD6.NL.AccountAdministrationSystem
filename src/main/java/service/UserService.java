package service;

import dao.JPAAccountAdministration;
import dao.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/
@Stateless
public class UserService {

    @Inject
    @JPAAccountAdministration
    private UserDao userDao;

    public UserService() {
    }

    public User create(User user) { return this.userDao.create(user); }

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

}
