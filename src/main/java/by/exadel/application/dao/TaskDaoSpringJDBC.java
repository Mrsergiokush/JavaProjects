package by.exadel.application.dao;

import by.exadel.application.dao.mapper.TaskRowMapper;
import by.exadel.application.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("SpringJDBC")
public class TaskDaoSpringJDBC implements IDao<Task> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task add(Task task) throws Exception {

        String SQL = "INSERT INTO public.task(task_name, task_deadline, user_id) VALUES (?, ?, ?)";

        jdbcTemplate.update(SQL, task.getTaskName(), task.getDeadline(), task.getUserId());

        return task;
    }

    @Override
    public Integer delete(Task task) throws Exception {

        String SQL = "DELETE FROM public.task WHERE task_name = ?";

        jdbcTemplate.update(SQL, task.getTaskName());

        return 1;
    }

    @Override
    public List<Task> getAll() throws Exception {

        String SQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task";

        List<Task> tasks = jdbcTemplate.query(SQL, new TaskRowMapper());

        return tasks;
    }

    @Override
    public Task get(Task task) {

        String SQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task WHERE user_id = ?";

        try {
            task = jdbcTemplate.queryForObject(SQL, new TaskRowMapper());
        } catch (DataAccessException e) {
            return null;
        }

        return task;
    }
}
