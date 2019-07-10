package by.exadel.application.store;

import by.exadel.application.model.Task;
import by.exadel.application.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private final String FILE_NAME = "Users.txt";

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public List<User> getAllUsers() throws IOException{
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        while((reader.read()) != -1){
            users.add(new User(new Task(reader.readLine(), reader.readLine()), reader.readLine()));
        }
        return users;
    }

    public void setAllUsers(List<User> users) throws IOException {

        FileWriter writer = new FileWriter(FILE_NAME);
        for (int i = 0; i < users.size(); i++){
            writer.write(users.get(i).getTask().getTaskName() + "\n" + users.get(i).getTask().getDeadline() + "\n" + users.get(i).getUsername() + "\n");
        }
        writer.close();
    }
}
