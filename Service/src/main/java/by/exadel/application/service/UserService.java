package by.exadel.application.service;

import by.exadel.application.dao.IDaoUser;
import by.exadel.application.dao.RoleDao;
import by.exadel.application.model.Filter;
import by.exadel.application.model.Role;
import by.exadel.application.model.User;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IService<User> {

    @Autowired
    private IDaoUser userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public static final Logger logger = Logger.getLogger(UserService.class);

    @Override
    public User add(User user) throws Exception {

//        logger.info("Trying add user");

        if (userDao.getByEmail(user.getEmail()) != null) {
//            logger.info("User is already exist");
            return null;
        } else if (userDao.add(user) == null) {
//            logger.info("Unique constraint user");
            return null;
        } else return user;
    }

    @Override
    public boolean delete(User user) throws Exception {
        try {
//            logger.info("Trying delete user");
            userDao.delete(user);
        } catch (DataIntegrityViolationException e) {
            return false;
        } catch (PSQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll(Integer position) throws Exception {

//        logger.info("Getting all user");
        return userDao.getAll(position);
    }

    @Override
    public Integer getSize() throws Exception {
        return userDao.getSize();
    }

    public void update(User user) throws Exception {

//        logger.info("update user");
        userDao.update(user);
    }

    public User getById(Integer id) {
//        logger.info("Get by Id User");
        return userDao.getByUserID(id);
    }

    public List<User> getByFilter(Filter filter, Integer from) { //from - элемент паггинации(если нацденных знаений много, то извекать будем по частям)

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

    public void save(User user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add((roleDao.getOne(1)));
        user.setRoles(roles);
        userDao.add(user);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
