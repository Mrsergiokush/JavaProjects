package by.exadel.application.dao;

import by.exadel.application.model.User;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements DaoJDBC<User>{

    private static final String URL = "jdbc:postgresql://localhost:5432/myapp";

    private static final String USER = "myuser";
    private static final String PASSWORD = "thePassword";

    public List<User> getAll() throws Exception{

        Connection connection = getConnection(); //get connecion

        Statement statement = createStatement(connection);

        String sql = new String("SELECT user_id, user_name FROM public.user"); //String request to DB

        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(resultSet.getString("user_name"));
            users.add(user);
        }

        return users;
    }

    public int add(User user) throws Exception{

        Connection connection = getConnection(); //get connecion

        Statement statement = createStatement(connection);

        String sql = new String("INSERT INTO public.user(user_name) VALUES ('" + user.getUserName() +"')");

        int rows = statement.executeUpdate(sql);

        return rows;
    }

    public int delete(User user) throws Exception{ //Client cant't delete user if user is having tasks

        DriverManager.registerDriver(new Driver());

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("DELETE FROM public.user WHERE user_name = '" + user.getUserName() + "'");

        int rows = statement.executeUpdate(sql);

        return rows;
    }

    public User getUserByName(String username) throws Exception{

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("SELECT user_id FROM public.user WHERE user_name = '" + username + "'");

        ResultSet resultSet = statement.executeQuery(sql);

        User user = new User();

        while (resultSet.next()) {
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(username);
        }

        return user;

    }

    public static Connection getConnection() throws SQLException{
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Statement createStatement(Connection connection) throws SQLException{
        return connection.createStatement();
    }
}
