package by.exadel.application.dao;

import by.exadel.application.model.User;

import java.util.List;

public interface IDaoUser extends IDao<User> {

    List<User> getByUserName(String UserName, Integer from) throws Exception;

    User getByUserID(Integer userId) throws Exception;

    User getByEmail(String email) throws Exception;

    List<User> getByAge(Integer age, Integer from) throws Exception;
}
