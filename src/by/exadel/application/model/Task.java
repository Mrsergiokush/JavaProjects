package by.exadel.application.model;

import java.util.Scanner;

public class Task {
    private static Scanner scanner = new Scanner(System.in);
    private int taskId;
    private String taskName;
    private String deadline;

    public Task() {
        this.taskId = generateId();
        System.out.println("Please, enter the taskname:");
        this.taskName = scanner.nextLine();
        System.out.println("Please, enter the deadline");
        this.deadline = scanner.nextLine();
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
    public static int generateId(){

        int id = (int) (Math.random()*1000);
        return id;
    }

}
