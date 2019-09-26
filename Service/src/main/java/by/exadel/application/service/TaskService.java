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

//    public static final Logger logger = Logger.getLogger(TaskService.class);

    @Override
    public Task add(Task task) throws Exception {

//        logger.info("Trying add Task");

        if (taskDao.getByUserAndId(task.getUser().getId(), task.getTaskName()) == null) {//If there not task
            return taskDao.add(task);
        } else
            return null;
    }

    @Override
    public boolean delete(Task task) throws Exception {
//        logger.info("Trying delete Task");

        if (taskDao.delete(task) == 1) //if task successfully deleting
            return true; //return true
        else return false;
    }

    @Override
    public List<Task> getAll(Integer pos) throws Exception {
//        logger.info("Trying get all tasks");

        return taskDao.getAll(pos);
    }

    public List<Task> getAll(Integer Id, Integer pos) throws Exception {
//        logger.info("Trying get all tasks by user Id");
        
        return taskDao.getTaskByUserId(Id, pos);
    }

    @Override
    public Integer getSize() throws Exception {
        return taskDao.getSize();
    }

    public void update(Task task) throws Exception {
//        logger.info("Trying update task");
        
        taskDao.update(task);
    }

    public Task getById(Integer task_id) throws Exception {
        return taskDao.getById(task_id);
    }
}
