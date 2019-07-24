package by.exadel.application.service;

import by.exadel.application.dao.IDaoTask;
import by.exadel.application.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements IService<Task> {

    @Autowired
    private IDaoTask taskDao;

    @Override
    public Task add(Task task) throws Exception {

        if (taskDao.getByNameAndId(task.getUserId(), task.getTaskName()) == null) {//If there not task
            return taskDao.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {

        if (taskDao.getByNameAndId(task.getUserId(), task.getTaskName()) == null)
            return false;
        else {
            taskDao.delete(task);
            return true;
        }
    }

    @Override
    public List<Task> getAll() throws Exception{
        return taskDao.getAll();
    }

    //useless (find better decision)
    @Override
    public Integer getId(Task task) throws Exception {
        return null;
    }

    public List<Task> getAll(Integer Id) throws Exception{
        return taskDao.getTaskByUserId(Id);
    }
}
