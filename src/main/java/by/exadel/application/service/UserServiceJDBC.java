package by.exadel.application.service;

import by.exadel.application.dao.UserDaoJDBC;
import by.exadel.application.model.User;

import java.util.List;

public class UserServiceJDBC {

    private UserDaoJDBC userDaoJDBC = new UserDaoJDBC();

    public User add(User user) throws Exception {

        if (userDaoJDBC.getByName(user.getUserName()) != null)
            return null;
        else
            return userDaoJDBC.add(user);
    }

    public int delete(User user) throws Exception {

        if (userDaoJDBC.delete(user) == 1)
            return 1;
        else return 0;
    }

    public List<User> getAll() throws Exception {

        return userDaoJDBC.getAll();
    }

    public Integer getId(String userName) throws Exception {

        if (userDaoJDBC.getByName(userName) == null)
            return -1;
        else {
            Integer id = userDaoJDBC.getByName(userName).getUserId();
            return userDaoJDBC.getByName(userName).getUserId();
        }
    }
}
