package by.exadel.application.service;

import by.exadel.application.dao.UserDaoJDBC;
import by.exadel.application.model.User;

import java.util.List;

public class UserService implements IService<User> {

    private UserDaoJDBC userDaoJDBC;

    public UserService(UserDaoJDBC userDaoJDBC) {
        this.userDaoJDBC = userDaoJDBC;
    }

    @Override
    public User add(User user) throws Exception {

        if (userDaoJDBC.get(user) != null)
            return null;
        else
            return userDaoJDBC.add(user);
    }

    @Override
    public boolean delete(User user) throws Exception {

        if (userDaoJDBC.delete(user) == 1)
            return true;
        else return false;
    }

    @Override
    public List<User> getAll() throws Exception {

        return userDaoJDBC.getAll();
    }

    @Override
    public Integer getId(User user) throws Exception {

        if (userDaoJDBC.get(user) == null)
            return -1;
        else {
            return userDaoJDBC.get(user).getUserId();
        }
    }
}
