package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.store.UserStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private UserStore store = new UserStore();

    public void addToList(User user) throws IOException{
            List<User> list = new ArrayList<>(store.getAllUsers()); //Think about better decision!
            list.add(user);
            store.setAllUsers(list);
    }


    public User getUserByIndex(int i) throws IOException {
        return store.getAllUsers().get(i);
    }

    public int getSize() throws IOException {
        return store.getAllUsers().size();
    }

    public void deleteByIndex(int index) throws IOException {
        List<User> list = new ArrayList<>(store.getAllUsers());
        list.remove(index);
        store.setAllUsers(list);
    }

}