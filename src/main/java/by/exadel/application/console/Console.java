package by.exadel.application.console;

import by.exadel.application.model.Task;
import by.exadel.application.model.User;
import by.exadel.application.service.TaskService;
import by.exadel.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Console {

    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("taskService")
    private TaskService taskService;

    public void menu() throws Exception { //Menu for users

        printout();

        int item = scanner.nextInt();

        while (item != 0) {

            switch (item) {

                case 1: {
                    scanner.nextLine();
                    System.out.println("Please, enter a Username");

                    String userName = inputUsername();
                    User user = new User();
                    user.setUserName(userName);

                    if (userService.add(user) == null)
                        System.out.println("User is already in DataBase");
                    else System.out.println("User was successfully added with id " + user.getUserId());

                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 2: {
                    scanner.nextLine();
                    System.out.println("Enter UserId of user to delete");

                    Integer Id = inputId();
                    User userToDelete = new User();
                    userToDelete.setUserId(Id);
                    try {
                        if (userService.delete(userToDelete))
                            System.out.println("User was successfully deleted");
                        else System.out.println("There isn't user in DataBase");
                    } catch (SQLException e) {
                        System.out.println("You can't delete user with tasks");
                    }
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 3: {
                    ArrayList<User> users = new ArrayList<>(userService.getAll());

                    if (users.isEmpty())
                        System.out.println("DataBase is empty");
                    else
                        for (int i = 0; i < users.size(); i++) {
                            System.out.println("User Name: " + users.get(i).getUserName());
                        }

                    System.out.println("\nPlease, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 4: {
                    scanner.nextLine();
                    System.out.println("Please, enter UserName of User to add the task");
                    String name = inputUsername();

                    User user = new User(name);

                    if (userService.getId(user) == -1) {
                        System.out.println("There isn't user with this name\n");
                        System.out.println("Please, choose the next action");
                        item = scanner.nextInt();
                        break;
                    }

                    Integer userId = userService.getId(user);
                    System.out.println("Please, enter a taskName");
                    String taskName = inputTaskname();
                    System.out.println("Please, enter a deadline");
                    String deadLine = inputDeadLine();

                    Task task = new Task();
                    task.setUserId(userId);
                    task.setTaskName(taskName);
                    task.setDeadline(deadLine);

                    if (taskService.add(task) != null)
                        System.out.println("Task was successfully added with id " + task.getTaskId());
                    else System.out.println("User is already has this task");

                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 5: {
                    scanner.nextLine();
                    System.out.println("Enter Username of user to delete his task");
                    String username = inputUsername();

                    User user = new User(username);

                    if (userService.getId(user) == -1) {
                        System.out.println("There isn't user with this name\n");
                        System.out.println("Please, choose the next action");
                        item = scanner.nextInt();
                        break;
                    }

                    Integer userId = userService.getId(user);
                    System.out.println("Please, enter a taskName to delete");
                    String taskNameToDelete = inputTaskname();

                    Task task = new Task();
                    task.setTaskName(taskNameToDelete);
                    task.setUserId(userId);

                    if (taskService.delete(task))
                        System.out.println("Task was successfully deleted");
                    else
                        System.out.println("Task with this name does not exist");

                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 6: {
                    scanner.nextLine();

                    if (taskService.getAll().isEmpty())
                        System.out.println("List of tasks if empty");

                    else {
                        ArrayList<Task> tasks = new ArrayList<>(taskService.getAll());

                        System.out.printf("%-20s %-20s %-20s\n\n", "ID", "Taskname", "Deadline");

                        for (int i = 0; i < tasks.size(); i++)
                            System.out.printf("%-20s %-20s %-20s\n", tasks.get(i).getTaskId(), tasks.get(i).getTaskName(), tasks.get(i).getDeadline());
                    }

                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                }

                case 7: { //Come up with the another idea
                    scanner.nextLine();

                    System.out.println("Enter Username of user to show his task");
                    String userNameToShowTask = inputUsername();
                    User user = new User();
                    user.setUserName(userNameToShowTask);

                    if (userService.getId(user) == -1) {
                        System.out.println("There isn't user with this name\n");
                        System.out.println("Please, choose the next action");
                        item = scanner.nextInt();
                        break;
                    }

                    Integer userId = userService.getId(user);
                    ArrayList<Task> tasks = new ArrayList<>(taskService.getAll(userId));

                    if (tasks.isEmpty())
                        System.out.println("List of tasks of user + " + userNameToShowTask + "is empty");
                    else {
                        System.out.printf("%-20s %-20s %-20s\n\n", "ID", "Taskname", "Deadline");
                        for (int i = 0; i < tasks.size(); i++)
                            System.out.printf("%-20s %-20s %-20s\n", tasks.get(i).getTaskId(), tasks.get(i).getTaskName(), tasks.get(i).getDeadline());
                    }
                    System.out.println("\nPlease, choose the next action");
                    item = scanner.nextInt();
                    break;
                }
                default:
                    item = 0;
            }
        }

    }


    private static void printout() {

        System.out.println("Please choose an action. Press : \n"
                + "1 - Add new user\n"
                + "2 - Delete user\n"
                + "3 - Show list of user\n"
                + "4 - Add new task\n"
                + "5 - Delete task\n"
                + "6 - Show list of tasks\n"
                + "7 - Show list of tasks of User\n"
                + "press 0 for exit\n\n"
                + "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }

    private static String inputDeadLine() { //input methods

        String deadLine = scanner.nextLine();
        return deadLine;
    }

    public static String inputUsername() {

        String username = scanner.nextLine();
        username = username.replace(",", " ");
        return username;
    }

    public static String inputTaskname() {

        String taskname = scanner.nextLine();
        taskname = taskname.replace(",", " ");
        return taskname;
    }

    public static Integer inputId(){

        Integer Id = scanner.nextInt();
        return Id;
    }
}
//limit offset (количество юзеров) (10 макисмум)
//Удаление по индексу
//Накинуть проверки на вводимые значения
