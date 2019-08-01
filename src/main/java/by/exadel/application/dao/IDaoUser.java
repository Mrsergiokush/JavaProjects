package by.exadel.application.dao;

import by.exadel.application.model.User;

public interface IDaoUser extends IDao<User> {

    User getByUserName(String UserName) throws Exception;

    User getByUserID(Integer userId) throws Exception;

    User getByEmail(String email) throws Exception;
}
