package by.exadel.application.model;

public class Task {
    private int taskId;
    private String taskName;
    private String deadline;

    public Task(String taskName, String deadline) {
        this.taskId = generateId();
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public static int generateId() {

        int id = (int) (Math.random() * 1000);
        return id;
    }

}
