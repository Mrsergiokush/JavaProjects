package by.exadel.application.service;

import by.exadel.application.dao.IDao;
import by.exadel.application.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements IService<Task> {

    @Autowired
    private IDao<Task> taskDao;

    @Override
    public Task add(Task task) throws Exception {

        if (taskDao.get(task) == null) {//If there not task
            return taskDao.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {

        if (taskDao.get(task) == null)
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
}
