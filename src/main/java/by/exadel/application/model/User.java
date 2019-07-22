package by.exadel.application.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class User {

    private Integer userId;

    private String userName;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    //for work with files
    private ArrayList<Task> tasks = new ArrayList<>();

    public User(String taskname, String deadline, String username) {
        this.userName = username;
        Task task = new Task(taskname, deadline);
        tasks.add(task);
    }

    public User(String username) {
        this.userName = username;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public void addTask(String taskName, String deadline) { //add new task in list of task of user
        tasks.add(new Task(taskName, deadline));
    }
}
