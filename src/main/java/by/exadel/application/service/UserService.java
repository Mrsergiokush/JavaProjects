package by.exadel.application.service;

import by.exadel.application.dao.IDao;
import by.exadel.application.dao.UserDaoJDBC;
import by.exadel.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements IService<User> {

    @Autowired
    private IDao<User> userDao;

    public UserService(UserDaoJDBC userDaoJDBC) {
        this.userDao = userDaoJDBC;
    }

    @Override
    public User add(User user) throws Exception {

        if (userDao.get(user) != null)
            return null;
        else
            return userDao.add(user);
    }

    @Override
    public boolean delete(User user) throws Exception {

        if (userDao.delete(user) == 1)
            return true;
        else return false;
    }

    @Override
    public List<User> getAll() throws Exception {

        return userDao.getAll();
    }

    @Override
    public Integer getId(User user) throws Exception {

        if (userDao.get(user) == null)
            return -1;
        else {
            return userDao.get(user).getUserId();
        }
    }
}
