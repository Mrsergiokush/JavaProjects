package by.exadel.application.console;

import by.exadel.application.model.User;
import by.exadel.application.service.TaskService;
import by.exadel.application.service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printout();
        try {
            menu();
        } catch (IOException e) {
            System.out.println("File has not be openned...");
        }
        System.out.println("Exit...");
    }


    private static void printout() {
        System.out.println("Please choose an action. Press : \n"
                + "1 - Add new user\n"
                + "2 - Show list of users\n"
                + "3 - Delete user by index\n"
                + "4 - Add new task\n"
                + "press 0 for exit\n\n"
                + "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }

    private static void menu() throws IOException { //Menu for users
        UserService service = new UserService(); //service for users
        TaskService taskService = new TaskService(); //service for tasks
        int item = scanner.nextInt();
        while (item != 0) {
            switch (item) {

                case 1:
                    scanner.nextLine();
                    System.out.println("Please, enter a username");
                    String username = inputUsername();
                    System.out.println("Please, enter a name of Task");
                    String taskname = inputTaskname();
                    System.out.println("Please, enter a deadline");
                    String deadline = inputDeadLine();

                    if (!service.addByUsername(new User(taskname, deadline, username))) //If user is in list
                        System.out.println("User is already in list");
                    else
                        System.out.println("User was succesfully added...\n");
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;

                case 2:
                    if (service.isEmpty()) //if list is empty
                        System.out.println("List is empty");
                    else {
                        for (int i = 0; i < service.getQuantity(); i++) { //output users and their tasks
                            System.out.printf("%-20s %-20s %-20s\n\n", "Username", "Taskname", "Deadline");

                            for (int j = 0; j < taskService.getTaskQuantity(i); j++)
                                System.out.printf("%-20s %-20s %-20s\n", service.getUserByIndex(i).getUsername(), service.getUserByIndex(i).getTasks().get(j).getTaskName(),
                                        service.getUserByIndex(i).getTasks().get(j).getDeadline());
                            System.out.println("\n\n");
                        }
                    }
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;

                case 3:
                    System.out.println("Please, choose index of element to delete");
                    int index;
                    if (!service.deleteData(index = scanner.nextInt())) //delete user with all his tasks
                        System.out.println("Incorrect index to delete");
                    else
                        System.out.println("Element was deleted\n");
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;

                case 4:
                    System.out.println("Please, enter name of user to add the task");
                    scanner.nextLine();
                    String userName = inputUsername(); //Add task to user
                    if (service.thereIsUser(userName)) {     //if user is in list
                        System.out.println("Please, enter a name of task and deadline to add the task");
                        String newTask = inputTaskname();
                        System.out.println("Please, enter a deadline");
                        String newDeadline = inputDeadLine();
                        taskService.AddTaskToUser(userName, newTask, newDeadline);
                    } else {  //If user is not in list we can't add new task for him
                        System.out.println("There is not user in the List");
                    }
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;

                    default:
                        item = 0;
            }
        }
    }

    private static String inputDeadLine() { //input methods
        String deadLine = scanner.nextLine();
        return deadLine;
    }

    public static String inputUsername(){
        String username = scanner.nextLine();
        return username;
    }

    public static String inputTaskname(){
        String taskname = scanner.nextLine();
        return taskname;
    }
}