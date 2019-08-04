package by.exadel.application.service;

import by.exadel.application.dao.IDaoUser;
import by.exadel.application.model.Filter;
import by.exadel.application.model.User;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IService<User> {

    @Autowired
    private IDaoUser userDao;

    @Override
    public User add(User user) throws Exception {

        if (userDao.getByEmail(user.getEmail()) != null)
            return null;
        else
            return userDao.add(user);
    }

    @Override
    public boolean delete(User user) throws Exception {
        try {
            userDao.delete(user);
        } catch (DataIntegrityViolationException e) {
            return false;
        } catch (PSQLException e) {
            return false;
        }
        return true;
        /*if (userDao.delete(user) == 1)
            return true;
        else return false;*/
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

    public void update(User user) throws Exception {
        userDao.update(user);
    }

    public User getById(Integer id) throws Exception {
        return userDao.getByUserID(id);
    }

    public List<User> getByFilter(Filter filter) throws Exception {

        String type = filter.getType();
        String value = filter.getValue();

        switch (type) {
            case "name":
                return Collections.singletonList(userDao.getByUserName(value));
            case "age":
                return Collections.singletonList(userDao.getByUserID(Integer.valueOf(value)));
            case "email":
                return Collections.singletonList(userDao.getByEmail(value));
            default:
                return null;
        }
    }
}
