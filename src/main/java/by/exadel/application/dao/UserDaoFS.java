package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Repository
@Profile("FileSystem")
public class UserDaoFS implements IDaoUser {

    @Autowired
    private UserStore store;

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

        boolean isDelete;

        ArrayList<User> users = new ArrayList<>(store.getAll());

        user.setUserName(getByUserID(user.getUserId()).getUserName());

        isDelete = users.remove(user);
        if (isDelete) {
            store.setAll(users);
            return 1;
        } else return 0;

    }

    @Override
    public ArrayList<User> getAll(Integer position) throws IOException {

        ArrayList<User> allUsers = new ArrayList<>(store.getAll());
        ArrayList<User> users = new ArrayList<>();

        for (int i = position; i < position + 3; i++) { //check on out of bound
            if (i >= allUsers.size())
                break;
            else
                users.add(allUsers.get(i));
        }

        return users;
    }

    @Override
    public User getByUserName(String userName) throws IOException {
        ArrayList<User> users = new ArrayList<>(store.getAll());

        for (int i = 0; i < users.size(); i++) {
            if (userName.equals(users.get(i).getUserName()))
                return users.get(i);
        }
        return null;
    }

    @Override
    public User getByUserID(Integer Id) throws IOException {
        ArrayList<User> users = new ArrayList<>(store.getAll());

        for (int i = 0; i < users.size(); i++) {
            if (Id.equals(users.get(i).getUserId()))
                return users.get(i);
        }
        return null;
    }

    @Override
    public Integer getSize() throws Exception {
        return store.getAll().size();
    }
}