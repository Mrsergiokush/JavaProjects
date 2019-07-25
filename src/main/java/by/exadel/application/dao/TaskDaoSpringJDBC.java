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
    private static final String deleteTaskSQL = "DELETE FROM public.task WHERE task_id = ?";
    private static final String getByNameAndIdSQL = "SELECT task_name, task_id, task_deadline, user_id FROM public.task WHERE task_name = ? AND user_id = ?";
    private static final String getSizeSQL = "SELECT count(*) FROM public.task";

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

        Integer rows = jdbcTemplate.update(deleteTaskSQL, task.getTaskId());

        return rows;
    }

    @Override
    public List<Task> getAll(Integer position) throws Exception {

        String getAllSQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task LIMIT 3 OFFSET " + position;

        List<Task> tasks = jdbcTemplate.query(getAllSQL, new TaskRowMapper());

        return tasks;
    }


    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) throws Exception {

        String getTasksByUserIdSQL = "SELECT task_name, task_deadline, task_id, user_id FROM public.task WHERE user_id = ? LIMIT 3 OFFSET " + position;

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

    @Override
    public Integer getSize() throws Exception {

        Integer size = jdbcTemplate.queryForObject(getSizeSQL, Integer.class);
        return size;
    }
}
