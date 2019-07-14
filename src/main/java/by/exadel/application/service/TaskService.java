package by.exadel.application.service;

import by.exadel.application.dao.TaskDao;

import java.io.IOException;

public class TaskService {

    private TaskDao taskDao = new TaskDao();

    public boolean addTaskToUser(String username, String taskname, String newDeadline) throws IOException {
        taskDao.addTask(username, taskname, newDeadline);
        return true;
    }

    public int getTaskQuantity(int index) throws IOException { //get list size of tasks of user
        return taskDao.getTaskSize(index);
    }
}
