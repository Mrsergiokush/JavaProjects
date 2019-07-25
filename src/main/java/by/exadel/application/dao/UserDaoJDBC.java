package by.exadel.application.dao;

import by.exadel.application.model.User;
import org.postgresql.Driver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("DataBase")
public class UserDaoJDBC implements IDaoUser {

    private static final String URL = "jdbc:postgresql://localhost:5432/myapp";

    private static final String USER = "myuser";
    private static final String PASSWORD = "thePassword";

    public UserDaoJDBC() {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() throws Exception {

        PreparedStatement statement = createStatement("SELECT user_id, user_name FROM public.user");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(resultSet.getString("user_name"));
            users.add(user);
        }

        return users;
    }

    @Override
    public User add(User user) throws Exception {

        PreparedStatement statement = getCreateStatement("INSERT INTO public.user(user_name) VALUES (?)", "user_id");

        statement.setString(1, user.getUserName());

        if (statement.executeUpdate() > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();

            boolean next = generatedKeys.next();

            int userId = next ? generatedKeys.getInt(1) : -1;

            if (userId != -1) {
                user.setUserId(userId);
                return user;
            }
        }

        throw new RuntimeException("User was not created");
    }

    @Override
    public Integer delete(User user) throws Exception { //Client cant't delete user if user is having tasks

        PreparedStatement statement = createStatement("DELETE FROM public.user WHERE user_id = ?");

        statement.setInt(1, user.getUserId());

        int rows = statement.executeUpdate();

        return rows;
    }

    @Override
    public User getByUserName(String userName) throws Exception {

        PreparedStatement statement = createStatement("SELECT user_id FROM public.user WHERE user_name = ?");

        statement.setString(1, userName);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) //if there not user in DB
            return null;

        User user = new User();

        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(userName);

        return user;
    }

    @Override
    public User getByUserID(Integer userId) throws Exception {

        PreparedStatement statement = createStatement("SELECT FROM public.user WHERE user_id = ?");

        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) //if there not user in DB
            return null;

        User user = new User();

        user.setUserId(userId);
        user.setUserName(resultSet.getString("user_name"));

        return user;
    }

    private PreparedStatement getCreateStatement(String sql, String idFieldName) throws SQLException {
        return getConnection().prepareStatement(sql, new String[]{idFieldName});
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement createStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
