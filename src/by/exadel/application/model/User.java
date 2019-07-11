package by.exadel.application.model;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<Task> tasks = new ArrayList<>();

    public User(String taskname, String deadline, String username) {
        this.username = username;
        Task task = new Task(taskname, deadline);
        tasks.add(task);
        }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksSize() {
        return tasks.size();
    }
    public void addTask(String taskName, String deadline){
        tasks.add(new Task(taskName, deadline));
    }


}
