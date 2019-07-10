package by.exadel.application.model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private Task task;

    public User(Task task, String username) {
        this.username = username;
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public Task getTask() {
        return task;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
