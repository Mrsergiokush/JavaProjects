package by.exadel.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.exadel.application.dao.MessageDao;
import by.exadel.application.dao.UserDao;
import by.exadel.application.model.Message;
import by.exadel.application.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class MessageTest {
    private MessageDao messageDao = new MessageDao();
    private UserDao userDao = new UserDao();

    private User initSender() {
        return userDao.getByUserID(5);
    }

    private User initReceiver() {
        return userDao.getByUserID(6);
    }

    private Message initMessage() {
        return Message.builder()
                .sender(initReceiver())
                .receiver(initSender())
                .createdDate(LocalDateTime.now())
                .isRead(false)
                .message("Hi, how are you?")
                .build();
    }

    @Test
    public void SEND_MESSAGE() {
        Message message = messageDao.send(initMessage());
        Assert.assertNotNull(message);
    }

    @Test
    public void GET_DIALOG() {
        List<Message> messages = messageDao.getDialog(initSender(), initReceiver());
        Assert.assertNotNull(messages);
    }
}
