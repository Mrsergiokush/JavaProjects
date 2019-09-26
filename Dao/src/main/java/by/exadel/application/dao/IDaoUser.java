package by.exadel.application.dao;

import by.exadel.application.model.User;

import java.util.List;

public interface IDaoUser extends IDao<User> {

    List<User> getByUserName(String UserName, Integer from);

    User getByUserID(Integer userId);

    User getByEmail(String email);

    List<User> getByAge(Integer age, Integer from);
}
