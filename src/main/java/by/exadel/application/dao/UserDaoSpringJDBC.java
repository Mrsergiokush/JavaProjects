package by.exadel.application.dao;

import by.exadel.application.dao.mapper.UserRowMapper;
import by.exadel.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoSpringJDBC implements IDaoUser {

    private final JdbcTemplate jdbcTemplate;

    private static final String addUserSQL = "INSERT INTO public.user(user_name, user_age, user_email) VALUES (?, ?, ?)"; //requests to DB
    private static final String deleteSQL = "DELETE FROM public.user WHERE user_id = ?";
    private static final String getByIdSQL = "SELECT * FROM public.user WHERE user_id = ?";
    private static final String getSizeSQL = "SELECT count(*) FROM public.user";
    private static final String updateSQL = "UPDATE public.user SET user_name = ?, user_age = ?, user_email = ? WHERE user_id = ?";
    private static final String getByEmailSQL = "SELECT * FROM public.user WHERE user_email = ?";
    
    @Autowired
    public UserDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) throws Exception {

        jdbcTemplate.update(addUserSQL, user.getUserName(), user.getAge(), user.getEmail());

        user.setUserId(getByEmail(user.getEmail()).getUserId());

        return user;
    }

    @Override
    public Integer delete(User user) throws Exception {

        Integer rows = jdbcTemplate.update(deleteSQL, user.getUserId());

        return rows;
    }

    @Override
    public List<User> getAll(Integer pos) throws Exception {

        String getAllSQL = "SELECT user_id, user_name, user_age, user_email FROM public.user LIMIT 3 OFFSET " + pos;

        List<User> users = jdbcTemplate.query(getAllSQL, new UserRowMapper());

        return users;
    }

    @Override
    public List<User> getByUserName(String userName, Integer from) {

        String getByUserNameSQL = "SELECT * FROM public.user WHERE user_name = ? LIMIT 3 OFFSET " + from;

        try {
            List<User> users = jdbcTemplate.query(getByUserNameSQL, new Object[]{userName}, new UserRowMapper());
            return users;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public User getByUserID(Integer userId) throws Exception {
        try {
            User user;
            user = jdbcTemplate.queryForObject(getByIdSQL, new Object[]{userId}, new UserRowMapper());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer getSize() throws Exception {

        Integer size = jdbcTemplate.queryForObject(getSizeSQL, Integer.class);
        return size;
    }

    @Override
    public Integer update(User user) throws Exception {
        return jdbcTemplate.update(updateSQL, user.getUserName(), user.getAge(), user.getEmail(), user.getUserId());
    }

    @Override
    public User getByEmail(String email) throws Exception {
        try {
            User user;
            user = jdbcTemplate.queryForObject(getByEmailSQL, new Object[]{email}, new UserRowMapper());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getByAge(Integer age, Integer from) {

        String getByAgeSQL = "SELECT * FROM public.user WHERE user_age = ? LIMIT 3 OFFSET " + from;

        try {
            List<User> users = jdbcTemplate.query(getByAgeSQL, new Object[]{age}, new UserRowMapper());
            return users;
        } catch (DataAccessException e) {
            return null;
        }
    }
}