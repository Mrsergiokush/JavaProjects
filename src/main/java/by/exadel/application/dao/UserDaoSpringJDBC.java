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

    @Autowired
    public UserDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) throws Exception {

        String SQL = "INSERT INTO public.user(user_name) VALUES (?)";

        jdbcTemplate.update(SQL, user.getUserName());

        return getByUserName(user.getUserName());
    }

    @Override
    public Integer delete(User user) throws Exception {

        String SQL = "DELETE FROM public.user WHERE user_name = ?";

        Integer rows = jdbcTemplate.update(SQL, user.getUserName());

        return rows;
    }

    @Override
    public List<User> getAll() throws Exception {

        String SQL = "SELECT user_id, user_name FROM public.user";

        List<User> users = jdbcTemplate.query(SQL, new UserRowMapper());

        return users;
    }

    @Override
    public User getByUserName(String userName) {

        String SQL = "SELECT * FROM public.user WHERE user_name = ?";

        try {
            User user;
            user = jdbcTemplate.queryForObject(SQL, new Object[]{userName}, new UserRowMapper());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
