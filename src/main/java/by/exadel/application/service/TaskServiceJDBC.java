package by.exadel.application.service;

import by.exadel.application.dao.TaskDaoJDBC;
import by.exadel.application.model.Task;

public class TaskServiceJDBC {

    TaskDaoJDBC taskDaoJDBC = new TaskDaoJDBC();

    public boolean add(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null) {//If there not task
            taskDaoJDBC.add(task);
            return true;
        } else
            return false;
    }

    public boolean delete(Task task) throws Exception {

        if (taskDaoJDBC.getByNameAndId(task) == null)
            return false;
        else {
            taskDaoJDBC.delete(task);
            return true;
        }
    }
}
