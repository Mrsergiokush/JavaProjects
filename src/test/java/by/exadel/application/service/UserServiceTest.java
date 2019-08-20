package by.exadel.application.service;

import by.exadel.application.dao.UserDaoHibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class UserServiceTest {

    UserDaoHibernate userDaoHibernate = new UserDaoHibernate();

    @Test
    public void add() {
        /*User user = new User();
        user.setUserName("ExadelProject");
        user.setAge(23);
        user.setEmail("exadelprmg@gmail.com");

        user = userDaoHibernate.add(user);

        Assert.assertEquals(user, userDaoHibernate.getByUserID(user.getId()));*/

    }

    @Test
    public void delete() {

    }

    @Test
    public void getAll() {
    }

    @Test
    public void getSize() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByFilter() {
    }
}