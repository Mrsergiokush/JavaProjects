package by.exadel.application.Main;

import by.exadel.application.console.Console;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Please, choose implementation\n" +
                "1) Data Base\n" +
                "2) File system");

        String item = inputItem();

        switch (item) {
            case "1":

                System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "DataBase");
                break;
            case "2":

                System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "FileSystem");
                break;
        }

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
        Console console = context.getBean("console", Console.class);
        console.menu();
        System.out.println("EXIT...");
    }

    private static String inputItem() {
        String item = scanner.nextLine();
        return item;
    }
}
