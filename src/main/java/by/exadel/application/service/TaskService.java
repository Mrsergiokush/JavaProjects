package by.exadel.application.service;

import by.exadel.application.dao.TaskDaoJDBC;
import by.exadel.application.model.Task;

import java.util.List;

public class TaskService implements IService<Task> {

    private TaskDaoJDBC taskDaoJDBC;

    public TaskService(TaskDaoJDBC taskDaoJDBC) {
        this.taskDaoJDBC = taskDaoJDBC;
    }

    @Override
    public Task add(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null) {//If there not task
            return taskDaoJDBC.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null)
            return false;
        else {
            taskDaoJDBC.delete(task);
            return true;
        }
    }

    @Override
    public List<Task> getAll() throws Exception{
        return taskDaoJDBC.getAll();
    }

    @Override
    public Integer getId(String name) throws Exception {
        return null;
    }

    public List<Task> getAll(Integer userId) throws Exception{
        return taskDaoJDBC.getAll(userId);
    }
}
