package by.exadel.application.dao;

import by.exadel.application.dao.mapper.UserRowMapper;
import by.exadel.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("SpringJDBC")
public class UserDaoSpringJDBC implements IDaoUser {


    private final JdbcTemplate jdbcTemplate;

    private static final String addUserSQL = "INSERT INTO public.user(user_name) VALUES (?)"; //requests to DB
    private static final String deleteSQL = "DELETE FROM public.user WHERE user_id = ?";
    private static final String getByUserNameSQL = "SELECT * FROM public.user WHERE user_name = ?";
    private static final String getById = "SELECT FROM public.user WHERE user_id = ?";
    private static final String getSizeSQL = "SELECT count(*) FROM public.user";

    @Autowired
    public UserDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) throws Exception {

        jdbcTemplate.update(addUserSQL, user.getUserName());

        user.setUserId(getByUserName(user.getUserName()).getUserId());

        return user;
    }

    @Override
    public Integer delete(User user) throws Exception {

        Integer rows = jdbcTemplate.update(deleteSQL, user.getUserId());

        return rows;
    }

    @Override
    public List<User> getAll(Integer pos) throws Exception {

        String getAllSQL = "SELECT user_id, user_name FROM public.user LIMIT 3 OFFSET "+ pos;

        List<User> users = jdbcTemplate.query(getAllSQL, new UserRowMapper());

        return users;
    }

    @Override
    public User getByUserName(String userName) {

        try {
            User user;
            user = jdbcTemplate.queryForObject(getByUserNameSQL, new Object[]{userName}, new UserRowMapper());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public User getByUserID(Integer userId) throws Exception {
        try {
            User user;
            user = jdbcTemplate.queryForObject(getByUserNameSQL, new Object[]{userId}, new UserRowMapper());
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
}
