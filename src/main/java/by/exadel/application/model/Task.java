package by.exadel.application.model;

public class Task {

    private Integer taskId;

    private Integer userId;

    private String taskName;

    private String deadline;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Task(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public Task() {
    }
}
