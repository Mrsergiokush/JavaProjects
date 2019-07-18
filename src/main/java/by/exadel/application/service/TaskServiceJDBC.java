package by.exadel.application.service;

import by.exadel.application.dao.TaskDaoJDBC;
import by.exadel.application.model.Task;

import java.util.List;

public class TaskServiceJDBC {

    TaskDaoJDBC taskDaoJDBC = new TaskDaoJDBC();

    public Task add(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null) {//If there not task
            return taskDaoJDBC.add(task);
        } else
            return null;
    }

    public boolean delete(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null)
            return false;
        else {
            taskDaoJDBC.delete(task);
            return true;
        }
    }

    public List<Task> getAll() throws Exception{
        return taskDaoJDBC.getAll();
    }
}
