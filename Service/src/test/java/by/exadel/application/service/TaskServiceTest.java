package by.exadel.application.service;

import by.exadel.application.dao.TaskDao;
import by.exadel.application.dao.UserDao;
import by.exadel.application.model.Task;
import by.exadel.application.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class TaskServiceTest {

    private UserDao userDao = new UserDao();
    private TaskDao taskDao = new TaskDao();

    private User findUser = userDao.getAll(1).get(0);

    public Task initTask() {
        return Task.builder()
                .user(findUser)
                .taskName("Test")
                .isDone(true)
                .priority("Low")
                .deadline(LocalDate.parse("2014-11-11"))
                .build();
    }

    @Test
    public void add() {
        Task task = initTask();
        taskDao.add(task);
        Assert.assertEquals(task, taskDao.getById(task.getId()));
        taskDao.delete(task);
    }

    @Test
    public void add_TASK_WITH_SAME_NAME() {
        Task task = initTask();
        taskDao.add(task);
        Assert.assertNull(taskDao.add(task));
        taskDao.delete(task);
    }

    @Test
    public void delete() {
        Task task = initTask();
        taskDao.add(task);
        taskDao.delete(task);
        Assert.assertNull(taskDao.getById(task.getId()));
    }

    @Test
    public void getTaskById() {
        Task task = initTask();
        taskDao.add(task);
        Assert.assertEquals(task.getTaskName(), taskDao.getById(task.getId()).getTaskName()); //compare task name
        taskDao.delete(task);
    }
}
