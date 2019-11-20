package by.exadel.application.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.exadel.application.dao.IDaoRole;
import by.exadel.application.dao.IDaoUser;
import by.exadel.application.model.Filter;
import by.exadel.application.model.Role;
import by.exadel.application.model.User;

@Service
public class UserService implements IServiceUser {

    @Autowired
    private IDaoUser userDao;

    @Autowired
    private IDaoRole roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User add(User user) throws Exception {
        if (userDao.getByEmail(user.getEmail()) != null) {
            return null;
        } else if (userDao.add(user) == null) {
            return null;
        } else return user;
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
    }

    @Override
    public List<User> getAll(Integer position) {
        return userDao.getAll(position);
    }

    @Override
    public Integer getSize() throws Exception {
        return userDao.getSize();
    }

    @Override
    public boolean update(User user) throws Exception {
        if (userDao.getByEmail(user.getEmail()) != null)
            return false;
        else {
            userDao.update(user);
            return true;
        }
    }

    @Override
    public User getById(Integer id) {
        return userDao.getByUserID(id);
    }

    @Override
    public List<User> getByFilter(Filter filter, Integer from) {
        String type = filter.getType();
        String value = filter.getValue();
        switch (type) {
            case "name":
                return userDao.getByUserName(value, from);
            case "age":
                return userDao.getByAge(Integer.valueOf(value), from);
            case "email":
                return Collections.singletonList(userDao.getByEmail(value));
            default:
                return null;
        }
    }

    @Override
    public void save(User user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add((roleDao.getOne(1)));
        user.setRoles(roles);
        userDao.add(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
