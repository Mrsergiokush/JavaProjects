package by.exadel.application.dao;

import by.exadel.application.model.Task;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;

public class TaskDaoJDBC implements DaoJDBC<Task>{

    private static final String URL = "jdbc:postgresql://localhost:5432/myapp";

    private static final String USER = "myuser";
    private static final String PASSWORD = "thePassword";

    @Override
    public int add(Task task) throws Exception{

        DriverManager.registerDriver(new Driver());

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("INSERT INTO public.task(task_name, task_deadline, user_id) VALUES ('" + task.getTaskName() +"', '"+ task.getDeadline() +"', "+ task.getUserId() + ")");

        int rows = statement.executeUpdate(sql);

        return rows;
    }

    @Override
    public int delete(Task task) throws Exception{

        DriverManager.registerDriver(new Driver());

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("DELETE FROM public.task WHERE task_name = '" + task.getTaskName() + "' AND user_id = " + task.getUserId());

        int rows = statement.executeUpdate(sql);

        return rows;
    }

    @Override
    public ArrayList<Task> getAll() throws Exception{

        DriverManager.registerDriver(new Driver());

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("SELECT task_name, task_deadline, task_id, user_id FROM public.task");

        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskName(resultSet.getString("task_name"));
            task.setDeadline(resultSet.getString("task_deadline"));
            task.setTaskId(resultSet.getInt("task_id"));
            task.setTaskId(resultSet.getInt("user_id"));
            tasks.add(task);
        }

        return tasks;
    }

    public Task getByNameAndId(Task task) throws Exception{

        DriverManager.registerDriver(new Driver());

        Connection connection = getConnection();

        Statement statement = createStatement(connection);

        String sql = new String("SELECT task_name, task_id, task_deadline, user_id FROM public.task WHERE task_name = '" + task.getTaskName() + "' AND user_id = " + task.getUserId());

        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next())
            return null;
        else
            return task;


    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Statement createStatement(Connection connection) throws SQLException{
        return connection.createStatement();
    }
}
