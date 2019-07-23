package by.exadel.application.store;

import by.exadel.application.model.User;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class UserStore implements IStore<User>{

    private final String USER_NAME = "Users.txt"; //name of file

    @Override
    public ArrayList<User> getAll() throws IOException{

        ArrayList<User> users = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(USER_NAME));

        while((reader.read()) != -1){
            String[] string = reader.readLine().split(","); //devide string

            User user = new User();
            user.setUserId(Integer.parseInt(string[0]));
            user.setUserName(string[1]);

            users.add(user);
        }

        return users;
    }

    @Override
    public void setAll(ArrayList<User> users) throws IOException {

        FileWriter writer = new FileWriter(USER_NAME);

        for (int i = 0; i < users.size(); i++){
            writer.write(" " + users.get(i).getUserId());
            writer.write("," + users.get(i).getUserName());
            writer.write("\n");
        }
        writer.close();
    }

    @Override
    public Integer scanID() throws IOException{

        ArrayList<User> users = new ArrayList<>(getAll());

        Integer id = -1;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserId() > id)
                id = users.get(i).getUserId();
        }
        id++;
        return id;
    }
}
