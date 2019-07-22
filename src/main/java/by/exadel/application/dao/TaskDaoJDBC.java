package by.exadel.application.dao;

import by.exadel.application.model.Task;
import org.postgresql.Driver;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDaoJDBC implements IDao<Task> {

    private static final String URL = "jdbc:postgresql://localhost:5432/myapp";

    private static final String USER = "myuser";
    private static final String PASSWORD = "thePassword";

    public TaskDaoJDBC() {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task add(Task task) throws Exception {

        PreparedStatement statement = getCreateStatement("INSERT INTO public.task(task_name, task_deadline, user_id) VALUES (?, ?, ?)", "task_id");

        statement.setString(1, task.getTaskName());

        statement.setString(2,task.getDeadline());

        statement.setInt(3, task.getUserId());

        if(statement.executeUpdate() > 0){
            ResultSet generatedKeys = statement.getGeneratedKeys();

            boolean next = generatedKeys.next();

            Integer taskId = next ? generatedKeys.getInt(1) : -1;

            if(taskId != -1){
                task.setTaskId(taskId);
                return task;
            }
        }

        throw new RuntimeException("Task was not created");
    }

    @Override
    public Integer delete(Task task) throws Exception {

        PreparedStatement statement = createStatement("DELETE FROM public.task WHERE task_name = ?");

        statement.setString(1, task.getTaskName());

        int rows = statement.executeUpdate();

        return rows;
    }

    @Override
    public List<Task> getAll() throws Exception {

        PreparedStatement statement = createStatement("SELECT task_name, task_deadline, task_id, user_id FROM public.task");

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskName(resultSet.getString("task_name"));
            task.setDeadline(resultSet.getString("task_deadline"));
            task.setTaskId(resultSet.getInt("task_id"));
            task.setUserId(resultSet.getInt("user_id"));
            tasks.add(task);
        }

        return tasks;
    }

    public List<Task> getAll(Integer userId) throws Exception{ //overloading method getAll

        PreparedStatement statement = createStatement("SELECT task_name, task_deadline, task_id, user_id FROM public.task WHERE user_id = ?");

        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Task> tasks = new ArrayList<>();

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskName(resultSet.getString("task_name"));
            task.setDeadline(resultSet.getString("task_deadline"));
            task.setTaskId(resultSet.getInt("task_id"));
            task.setUserId(resultSet.getInt("user_id"));
            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public Task get(Task task) throws Exception {

        PreparedStatement statement = createStatement("SELECT task_name, task_id, task_deadline, user_id FROM public.task WHERE task_name = ? AND user_id = ?");

        statement.setString(1, task.getTaskName());
        statement.setInt(2, task.getUserId());

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next())
            return null;
        else
            return task;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement createStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    private PreparedStatement getCreateStatement(String sql, String idFieldName) throws SQLException {
        return getConnection().prepareStatement(sql, new String[]{idFieldName});
    }
}
