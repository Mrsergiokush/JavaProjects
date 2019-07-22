package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.store.UserStore;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class UserDaoFS implements IDao<User> {

    private UserStore store = new UserStore();

    @Override
    public User add(User user) throws IOException {

        ArrayList<User> list = new ArrayList<>(store.getAll());

        user.setUserId(store.scanID());

        list.add(user);

        store.setAll(list);

        return user;
    }

    @Override
    public Integer delete(User user) throws IOException {

        ArrayList<User> users = new ArrayList<>(store.getAll());
        users.remove(user);

        store.setAll(users);

        return 1;
    }

    @Override
    public ArrayList<User> getAll() throws IOException {
        return store.getAll();
    }

    @Override
    public User get(User user) throws IOException {
        ArrayList<User> users = new ArrayList<>(store.getAll());

        for (int i = 0; i < users.size(); i++) {
            if (user.getUserName().equals(users.get(i).getUserName()))
                return user;
        }

        return null;
    }
}