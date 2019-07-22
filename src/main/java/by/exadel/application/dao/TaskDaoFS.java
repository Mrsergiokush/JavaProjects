package by.exadel.application.dao;

import by.exadel.application.model.Task;
import by.exadel.application.store.TaskStore;

import java.io.IOException;
import java.util.ArrayList;

public class TaskDaoFS implements IDao<Task>{

    private TaskStore taskStore = new TaskStore();

    @Override
    public Task add(Task task) throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(taskStore.getAll());
        tasks.add(task);
        taskStore.setAll(tasks);
        return task;
    }

    @Override
    public Integer delete(Task task) throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(taskStore.getAll());
        tasks.remove(task);
        taskStore.setAll(tasks);
        return 1;
    }

    @Override
    public ArrayList<Task> getAll() throws IOException {
        return taskStore.getAll();
    }
}
