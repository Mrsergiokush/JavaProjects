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
public class TaskDaoSpringJDBC implements IDaoTask {

    private final JdbcTemplate jdbcTemplate;

    private static final String addTaskSQL = "INSERT INTO public.task(task_name, task_deadline, user_id) VALUES (?, ?, ?)";
    private static final String deleteTaskSQL = "DELETE FROM public.task WHERE task_name = ?";
    private static final String getAllSQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task";
    private static final String getTasksByUserIdSQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task WHERE user_id = ?";
    private static final String getByNameAndIdSQL = "SELECT task_name, task_id, task_deadline, user_id FROM public.task WHERE task_name = ? AND user_id = ?";

    @Autowired
    public TaskDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task add(Task task) throws Exception {

        jdbcTemplate.update(addTaskSQL, task.getTaskName(), task.getDeadline(), task.getUserId());

        Integer taskId = getByNameAndId(task.getUserId(), task.getTaskName()).getTaskId(); //get task id

        task.setTaskId(taskId); //set task id

        return task;
    }

    @Override
    public Integer delete(Task task) throws Exception {

        Integer rows = jdbcTemplate.update(deleteTaskSQL, task.getTaskName());

        return rows;
    }

    @Override
    public List<Task> getAll() throws Exception {

        List<Task> tasks = jdbcTemplate.query(getAllSQL, new TaskRowMapper());

        return tasks;
    }


    @Override
    public List<Task> getTaskByUserId(Integer userId) throws Exception {

        List<Task> tasks = jdbcTemplate.query(getTasksByUserIdSQL, new Object[]{userId}, new TaskRowMapper());

        return tasks;
    }

    @Override
    public Task getByNameAndId(Integer userId, String taskName) throws Exception {

        try {
            Task task = jdbcTemplate.queryForObject(getByNameAndIdSQL, new Object[]{taskName, userId}, new TaskRowMapper());
            return task;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
