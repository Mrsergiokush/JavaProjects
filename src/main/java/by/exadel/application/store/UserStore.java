package by.exadel.application.store;

import by.exadel.application.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStore {

    private final String USER_NAME = "Users.txt"; //name of file

    public List<User> getAllUsers() throws IOException{
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(USER_NAME));
        while((reader.read()) != -1){
            String[] string = reader.readLine().split(","); //devide string
            int i = 0;
            String username = string[i++];
            User user = new User(username);
            while (i < string.length){
                String taskname = string[i++];
                String deadline = string[i++];
                user.addTask(taskname, deadline);
            }
            users.add(user);
        }
        return users;
    }

    public void setAllUsers(List<User> users) throws IOException {
        FileWriter writer = new FileWriter(USER_NAME);
        for (int i = 0; i < users.size(); i++){
            writer.write(" " + users.get(i).getUserName());
            for (int j = 0; j < users.get(i).getTasksSize(); j++){
                writer.write("," + users.get(i).getTasks().get(j).getTaskName() + "," + users.get(i).getTasks().get(j).getDeadline());
            }
            writer.write("\n");
        }
        writer.close();
    }
}
