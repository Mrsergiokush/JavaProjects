package by.exadel.application.Main;

import by.exadel.application.console.Console;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception{

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        Console console = (Console) context.getBean("Console");
        console.menu();
    }
}