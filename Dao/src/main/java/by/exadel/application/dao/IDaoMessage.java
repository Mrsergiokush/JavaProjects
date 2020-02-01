package by.exadel.application.dao;

import java.util.List;

import by.exadel.application.model.Message;
import by.exadel.application.model.User;

public interface IDaoMessage {
    Message send(Message message);

    List<Message> getDialog(User firstUser, User secondUser);
}
