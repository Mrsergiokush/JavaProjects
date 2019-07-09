package by.exadel.application.console;

import by.exadel.application.model.Task;
import by.exadel.application.model.User;
import by.exadel.application.service.Service;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Service service = new Service();
        printout();
        menu();
        System.out.println("Exit...");

    }


    private static void printout() {
        System.out.println("Please choose an action. Press : \n"
                + "1 to add new user\n"
                + "2 to show the list of users\n"
                + "3 to delete the user by index\n"
                + "press 0 for exit\n\n"
                + "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }


    private static void menu() {
        Service service = new Service();
        int item = scanner.nextInt();
        while (item != 0) {
            switch (item) {
                case 1:
                    scanner.nextLine();
                    if (!service.addByUsername(new User(new Task(scanner.nextLine(), scanner.nextLine()), scanner.nextLine())))
                        System.out.println("User is already in list");
                    else
                        System.out.println("User was succesfully added...\n");
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                case 2:
                    if (service.isEmpty())
                        System.out.println("List is empty");
                    else {

                    }
                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;
                case 3:
                    System.out.println("Please, choose index of element to delete");
                    int index;

                    if (!service.deleteData(index = scanner.nextInt()))
                        System.out.println("Incorrect index to delete");
                    else
                        System.out.println("Element was deleted\n");

                    System.out.println("Please, choose the next action");
                    item = scanner.nextInt();
                    break;

                default:
                    item = 0;
            }
        }
    }
}