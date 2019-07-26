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

        int item = inputInteger();

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
                    item = inputInteger();
                    break;
                }

                case 2: {
                    scanner.nextLine();
                    System.out.println("Enter UserId of user to delete");

                    Integer Id = inputInteger();
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
                    item = inputInteger();
                    break;
                }

                case 3: {
                    if (userService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else
                        printOutUsers();

                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
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
                        item = inputInteger();
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
                    item = inputInteger();
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
                        item = inputInteger();
                        break;
                    }

                    Integer userId = userService.getId(user);
                    System.out.println("Please, enter a taskId to delete");
                    Integer taskId = inputInteger();

                    Task task = new Task();
                    task.setTaskId(taskId);
                    task.setUserId(userId);

                    if (taskService.delete(task))
                        System.out.println("Task was successfully deleted");
                    else
                        System.out.println("Task with this name does not exist");

                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 6: {
                    scanner.nextLine();

                    if (taskService.getSize() == 0)
                        System.out.println("List of tasks if empty");

                    else
                        printOutTasks();

                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 7: { //Come up with the another idea
                    scanner.nextLine();

                    System.out.println("Enter Username of user to show his task");
                    String userName = inputUsername();
                    User user = new User();
                    user.setUserName(userName);

                    if (userService.getId(user) == -1) {
                        System.out.println("There isn't user with this name\n");
                        System.out.println("Please, choose the next action");
                        item = inputInteger();
                        break;
                    }

                    Integer userId = userService.getId(user);

                    if (taskService.getSize() == 0)
                        System.out.println("List of tasks of user + " + userName + "is empty");
                    else
                        printOutTasksOfUser(userId);

                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
                    break;
                }
                default:
                    item = 0;
            }
        }
    }

    private void printOutTasks() throws Exception {

        Integer page = 0;
        Integer counter = taskService.getSize();
        Integer pageCounter = counter / 3;  //Всего старниц

        while (page != -1) { //-1 - for exit of output

            ArrayList<Task> tasks = new ArrayList<>(taskService.getAll(page * 3));

            System.out.printf("%-20s %-20s %-20s\n\n", "ID", "Taskname", "Deadline");

            for (int i = 0; i < tasks.size(); i++)
                System.out.printf("%-20s %-20s %-20s\n", tasks.get(i).getTaskId(), tasks.get(i).getTaskName(), tasks.get(i).getDeadline());

            outputPageInfo(page, pageCounter);
            page = choosePage(page, pageCounter);

        }

    }

    private void printOutTasksOfUser(Integer userId) throws Exception {
        Integer page = 0;
        Integer counter = taskService.getSize();
        Integer pageCounter = counter / 3;  //Всего старниц

        while (page != -1) {

            ArrayList<Task> tasks = new ArrayList<>(taskService.getAll(userId, page * 3));

            System.out.printf("%-20s %-20s %-20s\n\n", "ID", "Taskname", "Deadline");

            for (int i = 0; i < tasks.size(); i++)
                System.out.printf("%-20s %-20s %-20s\n", tasks.get(i).getTaskId(), tasks.get(i).getTaskName(), tasks.get(i).getDeadline());

            outputPageInfo(page, pageCounter);
            page = choosePage(page, pageCounter);

        }
    }

    private void printOutUsers() throws Exception {

        Integer page = 0;
        Integer counter = userService.getSize();
        Integer pageCounter = counter / 3;  //Всего старниц

        while (page != -1) {

            ArrayList<User> users = new ArrayList<>(userService.getAll(page * 3));

            for (int i = 0; i < users.size(); i++)
                System.out.println("User : " + users.get(i).getUserName());

            outputPageInfo(page, pageCounter);
            page = choosePage(page, pageCounter);

        }
    }

    private void outputPageInfo(Integer page, Integer pageCounter) {
        System.out.println("************************************");
        System.out.println("You now on " + page + " of " + pageCounter);
        System.out.println("To go to next page enter 2");
        System.out.println("To go to prev page enter 1");
        System.out.println("To exit enter 0");
        System.out.println("*************************************");
    }

    private Integer choosePage(Integer page, Integer pageCounter) {
        Integer item = inputInteger();
        switch (item) {
            case 1:
                if (page == 0)
                    break;
                page--;
                break;

            case 2:
                if (page.equals(pageCounter))
                    break;
                page++;
                break;

            case 0:
                page = -1;
                break;
        }
        return page;
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

        while (username.length() == 0) {
            System.out.println("String can't be empty");
            username = scanner.nextLine();
        }
        username = username.replace(",", " ");
        return username;
    }

    public static String inputTaskname() {

        String taskname = scanner.nextLine();
        while (taskname.length() == 0) {
            System.out.println("String can't be empty");
            taskname = scanner.nextLine();
        }
        taskname = taskname.replace(",", " ");
        return taskname;
    }

    public static Integer inputInteger() { //checking on input INTEGER
        Integer Id;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Input data must be a number");
                scanner.next();
            }
            Id = scanner.nextInt();
        } while (Id < 0);
        return Id;
    }
}
//limit offset (количество юзеров) (10 макисмум)