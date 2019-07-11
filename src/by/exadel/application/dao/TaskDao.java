package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.store.UserStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    private UserStore store = new UserStore();

    public void addTask(String username, String taskname, String deadline) throws IOException {
        List<User> list = new ArrayList<>(store.getAllUsers());
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getUsername().equals(username)) //if names of user is equals
                list.get(i).addTask(taskname, deadline); //add new task in list
        }
        store.setAllUsers(list);
    }

    public int getTaskSize(int index) throws IOException{
        return store.getAllUsers().get(index).getTasksSize();
    }
}
