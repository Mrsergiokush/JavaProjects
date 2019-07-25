package by.exadel.application.dao;

import by.exadel.application.model.Task;
import by.exadel.application.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Repository
@Profile("FileSystem")
public class TaskDaoFS implements IDaoTask {

    @Autowired
    private TaskStore taskStore;

    @Override
    public Task add(Task task) throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(taskStore.getAll());

        task.setTaskId(taskStore.scanID());

        tasks.add(task);

        taskStore.setAll(tasks);

        return task;
    }

    @Override
    public Integer delete(Task task) throws Exception {

        ArrayList<Task> tasks = new ArrayList<>(taskStore.getAll());

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getUserId().equals(task.getUserId()) && tasks.get(i).getTaskId().equals(task.getTaskId()))
                task.setTaskName(tasks.get(i).getTaskName()); //set taskName
        }

        task.setDeadline(getByNameAndId(task.getUserId(), task.getTaskName()).getDeadline()); //set task deadline

        boolean isDelete = tasks.remove(task);

        if (isDelete) {
            taskStore.setAll(tasks);
            return 1;
        } else return 0;
    }

    @Override
    public ArrayList<Task> getAll(Integer pos) throws IOException {

        ArrayList<Task> allTasks = new ArrayList<>(taskStore.getAll());

        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = pos; i < pos + 3; i++) {
            if (i >= allTasks.size())
                break;
            else
                tasks.add(allTasks.get(i));
        }

        return tasks;
    }

    @Override
    public Task getByNameAndId(Integer userId, String taskName) throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(taskStore.getAll());

        for (int i = 0; i < tasks.size(); i++) {
            if (taskName.equals(tasks.get(i).getTaskName()) && userId.equals(tasks.get(i).getUserId())) //if user has a task
                return tasks.get(i);
        }
        return null;
    }

    @Override
    public ArrayList<Task> getTaskByUserId(Integer userId, Integer pos) throws IOException {

        ArrayList<Task> allTasks = new ArrayList<>(taskStore.getAll());

        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < allTasks.size(); i++) { //deleting tasks of another user
            if (allTasks.get(i).getUserId() != userId)
                allTasks.remove(i);
        }
        for (int i = pos; i < pos + 3; i++) //get only 3 tasks
            tasks.add(allTasks.get(i));

        return tasks;
    }

    @Override
    public Integer getSize() throws Exception {
        return taskStore.getAll().size();
    }
}
