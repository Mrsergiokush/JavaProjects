package by.exadel.application.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class Task {

    private Integer taskId;

    private Integer userId;

    private String taskName;

    private LocalDate deadline;

    private String priority;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    private boolean isDone;

    private String date;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate date) {
        this.deadline = date;
    }

    public String getDate() {
        return date;
    }

    public String setDate(String date) {
        return this.date = date;
    }

    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId.equals(task.taskId) &&
                userId.equals(task.userId) &&
                taskName.equals(task.taskName) &&
                deadline.equals(task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, taskName, deadline);
    }
}
