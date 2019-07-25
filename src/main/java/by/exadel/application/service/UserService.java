package by.exadel.application.service;

import by.exadel.application.dao.IDaoUser;
import by.exadel.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IService<User> {

    @Autowired
    private IDaoUser userDao;

    @Override
    public User add(User user) throws Exception {

        if (userDao.getByUserName(user.getUserName()) != null)
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
    public List<User> getAll(Integer position) throws Exception {

        return userDao.getAll(position);
    }

    @Override
    public Integer getId(User user) throws Exception {

        if (userDao.getByUserName(user.getUserName()) == null)
            return -1;
        else {
            return userDao.getByUserName(user.getUserName()).getUserId();
        }
    }

    @Override
    public Integer getSize() throws Exception {
        return userDao.getSize();
    }
}
