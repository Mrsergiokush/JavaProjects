/*package by.exadel.application.dao;

import by.exadel.application.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public class TaskDaoSpringJDBC implements IDaoTask {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Task> taskMapper;

    private static final String addTaskSQL = "INSERT INTO public.task(task_name, task_deadline, \"task_isDone\", task_priority, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String deleteTaskSQL = "DELETE FROM public.task WHERE task_id = ?";
    private static final String getByNameAndIdSQL = "SELECT task_name, task_id, task_deadline, user_id, task_priority, \"task_isDone\" FROM public.task WHERE task_name = ? AND user_id = ?";
    private static final String getSizeSQL = "SELECT count(*) FROM public.task";
    private static final String updateSQL = "update public.task set task_name = ?, task_deadline = ?, task_priority = ?, \"task_isDone\" = ? where task_id = ?";
    private static final String getByIdSQL = "SELECT * FROM public.task WHERE task_id = ?";

    @Autowired
    public TaskDaoSpringJDBC(JdbcTemplate jdbcTemplate, RowMapper<Task> taskMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task add(Task task) throws Exception {

        jdbcTemplate.update(addTaskSQL, task.getTaskName(), task.getDeadline(), task.isDone(), task.getPriority(),task.getUserId());

        Integer taskId = getByUserAndId(task.getUserId(), task.getTaskName()).getTaskId(); //get task id

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

        String getAllSQL = "SELECT task_name, task_deadline, task_id, user_id, task_priority, \"task_isDone\" FROM public.task LIMIT 3 OFFSET " + position;

        List<Task> tasks = jdbcTemplate.query(getAllSQL, taskMapper);

        return tasks;
    }


    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) throws Exception {

        String getTasksByUserIdSQL = "SELECT task_name, task_deadline, task_id, user_id, task_priority, \"task_isDone\" FROM public.task WHERE user_id = ? LIMIT 3 OFFSET " + position;

        List<Task> tasks = jdbcTemplate.query(getTasksByUserIdSQL, new Object[]{userId}, taskMapper);

        return tasks;
    }

    @Override
    public Task getByUserAndId(Integer userId, String taskName) throws Exception {

        try {
            Task task = jdbcTemplate.queryForObject(getByNameAndIdSQL, new Object[]{taskName, userId}, taskMapper);
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

    @Override
    public Integer update(Task task) throws Exception {

        return jdbcTemplate.update(updateSQL, task.getTaskName(), task.getDeadline(), task.getPriority(), task.isDone(),task.getTaskId());//ДОБАВИТЬ ПРИОРИТЕТ

    }

    @Override
    public Task getById(Integer id) throws Exception {
        try {
            Task task = jdbcTemplate.queryForObject(getByIdSQL, new Object[]{id}, taskMapper);
            return task;
        } catch (DataAccessException e) {
            return null;
        }
    }
}*/
