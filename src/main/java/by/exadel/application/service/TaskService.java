package by.exadel.application.service;

import by.exadel.application.dao.TaskDaoJDBC;
import by.exadel.application.model.Task;

import java.util.List;

public class TaskService implements IService<Task> {

    //TaskDaoFS taskDaoFS = new TaskDaoFS();

    private TaskDaoJDBC taskDaoJDBC;

    public TaskService(TaskDaoJDBC taskDaoJDBC) {
        this.taskDaoJDBC = taskDaoJDBC;
    }

    @Override
    public Task add(Task task) throws Exception {

        if (taskDaoJDBC.get(task) == null) {//If there not task
            return taskDaoJDBC.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {

        if (taskDaoJDBC.get(task) == null)
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

    //useless (find better decision)
    @Override
    public Integer getId(Task task) throws Exception {
        return null;
    }

    /*public List<Task> getAll(Integer userId) throws Exception{
        return taskDaoFS.getAll(userId);
    }*/
}
