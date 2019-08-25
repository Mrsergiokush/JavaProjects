package by.exadel.application.service;

import by.exadel.application.dao.TaskDaoHibernate;
import by.exadel.application.dao.UserDaoHibernate;
import by.exadel.application.model.Task;
import by.exadel.application.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class TaskServiceTest {

    UserDaoHibernate userDao = new UserDaoHibernate();
    TaskDaoHibernate taskDao = new TaskDaoHibernate();

    List<User> users = userDao.getAll(1);

    public Task initTask() {
        Task task = new Task();

        task.setUser(users.get(0));
        task.setTaskName("Test");
        task.setDone(true);
        task.setPriority("Low");
        task.setDeadline(LocalDate.parse("2014-11-11"));

        return task;
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

    @Test
    public void update() {

    }
}