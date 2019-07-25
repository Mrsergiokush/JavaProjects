package by.exadel.application.dao;

import by.exadel.application.model.Task;

import java.util.List;

public interface IDaoTask extends IDao<Task> {

    List<Task> getTaskByUserId(Integer userId, Integer position) throws Exception;

    Task getByNameAndId(Integer userId, String taskName) throws Exception;
}
