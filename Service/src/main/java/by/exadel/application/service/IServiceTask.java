package by.exadel.application.service;

import java.util.List;

import by.exadel.application.model.Task;

public interface IServiceTask extends IService<Task> {
    List<Task> getAll(Integer id, Integer position);
}
