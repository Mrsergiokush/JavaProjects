/*package by.exadel.application.service;

import by.exadel.application.dao.UserDaoHibernate;
import by.exadel.application.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class UserServiceTest {

    UserDaoHibernate dao = new UserDaoHibernate();


    public User initUser() {
        return User.builder().username("Alex")
                .email("alex@gmail.com")
                .age(12)
                .password("12345678")
                .roles()
                .build();
    }

    @Test
    public void add() {
        User user = initUser();
        dao.add(user);
        Assert.assertEquals(user, dao.getByEmail(user.getEmail()));
        dao.delete(user);
    }


    @Test
    public void add_USER_WITH_SAME_EMAIL() {
        User user = initUser();

        dao.add(user);

        Assert.assertNull(dao.add(user));

        dao.delete(user);
    }

    @Test
    public void delete() {
        User user = initUser();

        dao.add(user);

        dao.delete(user);

        Assert.assertNull(dao.getByEmail(user.getEmail()));
    }

    @Test
    public void update() {
        User user = initUser();

        dao.add(user);

        String newName = "newTest";
        user.setUserName(newName);

        dao.update(user);

        Assert.assertEquals(user.getUserName(), dao.getByEmail(user.getEmail()).getUserName());

        dao.delete(user);
    }

    @Test
    public void getById() {
        User user = initUser();
        dao.add(user);

        Assert.assertEquals(user.getEmail(), dao.getByUserID(user.getId()).getEmail());

        dao.delete(user);
    }

    @Test
    public void getByFilter() {

    }
}*/
