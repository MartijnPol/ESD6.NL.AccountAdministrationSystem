package dao.implementation;

import dao.JPAAccountAdministration;
import dao.UserDao;
import domain.User;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 22-03-18
 **/
@Stateless
@JPAAccountAdministration
public class UserDaoImpl extends GenericDaoJPAImpl<User> implements UserDao {

    public UserDaoImpl() {

    }

}