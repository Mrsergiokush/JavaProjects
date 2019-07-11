package by.exadel.application.model;

public class Task {

    private String taskName;
    private String deadline;

    public Task(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }


    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }


}
