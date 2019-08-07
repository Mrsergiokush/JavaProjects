package by.exadel.application.dao.mapper;

import by.exadel.application.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setAge(rs.getInt("user_age"));
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("user_name"));
        user.setEmail(rs.getString("user_email"));
        return user;
    }

}
