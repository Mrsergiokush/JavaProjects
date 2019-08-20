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

        if (taskDao.getByUserAndId(task.getUser().getId(), task.getTaskName()) == null) {//If there not task
            return taskDao.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {
        if (taskDao.delete(task) == 1) //if task successfully deleting
            return true; //return true
        else return false;
    }

    @Override
    public List<Task> getAll(Integer pos) throws Exception {
        return taskDao.getAll(pos);
    }

    public List<Task> getAll(Integer Id, Integer pos) throws Exception {
        return taskDao.getTaskByUserId(Id, pos);
    }

    @Override
    public Integer getSize() throws Exception {
        return taskDao.getSize();
    }

    public void update(Task task) throws Exception {
        taskDao.update(task);
    }

    public Task getById(Integer task_id) throws Exception {
        return taskDao.getById(task_id);
    }
}
