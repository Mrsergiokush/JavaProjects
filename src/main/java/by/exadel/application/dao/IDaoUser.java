package by.exadel.application.dao;

import by.exadel.application.model.User;

public interface IDaoUser extends IDao<User> {

    User getByUserName(String UserName) throws Exception;
}
