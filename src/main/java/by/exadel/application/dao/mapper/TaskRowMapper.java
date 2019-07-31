package by.exadel.application.dao.mapper;

import by.exadel.application.model.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setTaskId(rs.getInt("task_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setUserId(rs.getInt("user_id"));
        task.setDeadline(rs.getDate("task_deadline").toLocalDate());
        return task;
    }
}
