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
public class UserDaoSpringJDBC implements IDao<User> {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User add(User user) throws Exception {

        String SQL = "INSERT INTO public.user(user_name) VALUES (?)";

        jdbcTemplate.update(SQL, user.getUserName());

        return user;
    }

    @Override
    public Integer delete(User user) throws Exception {

        String SQL = "DELETE FROM public.user WHERE user_name = ?";

        jdbcTemplate.update(SQL, user.getUserName());

        return null;
    }

    @Override
    public List<User> getAll() throws Exception {

        String SQL = "SELECT user_id, user_name FROM public.user";

        List<User> users = jdbcTemplate.query(SQL, new UserRowMapper());

        return users;
    }

    @Override
    public User get(User user) {

        String SQL = "SELECT user_id FROM public.user WHERE user_name = ?";

        try {
            user = jdbcTemplate.queryForObject(SQL, new UserRowMapper());
        } catch (DataAccessException e) {
            return null;
        }

        return user;
    }
}
