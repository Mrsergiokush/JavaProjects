package by.exadel.application.dao;

import by.exadel.application.model.Task;

import java.util.List;

public interface IDaoTask extends GenericDao<Task> {

    List<Task> getTaskByUserId(Integer userId, Integer position) throws Exception;

    Task getByUserAndId(Integer userId, String taskName) throws Exception;

    Task getById(Integer id) throws Exception;
}
