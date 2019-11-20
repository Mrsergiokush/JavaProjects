package by.exadel.application.service;

import java.util.List;

import by.exadel.application.model.Filter;
import by.exadel.application.model.User;

public interface IServiceUser extends IService<User> {
    List<User> getAll(Integer position);

    List<User> getByFilter(Filter filter, Integer from);

    void save(User user) throws Exception;

    User getByEmail(String email);
}
