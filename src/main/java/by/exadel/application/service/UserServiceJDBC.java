package by.exadel.application.service;

import by.exadel.application.dao.UserDaoJDBC;
import by.exadel.application.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceJDBC {

    private UserDaoJDBC userDaoJDBC = new UserDaoJDBC();

    public int add(User user) throws Exception{

        if (compareUsers(user))
            return 0;
        else
            return userDaoJDBC.add(user);
    }

    public int delete(User user) throws Exception{

        if(userDaoJDBC.delete(user) == 1)
            return 1;
        else return 0;
    }

    public List<User> getAll() throws Exception{

        return userDaoJDBC.getAll();
    }

    public Integer getId(String userName) throws Exception{

        if(compareUsers(new User(userName)))
            return userDaoJDBC.getUserByName(userName).getUserId();
        else return -1;
    }

    public boolean compareUsers(User user) throws Exception{ //compare users

        List <User> allUsers = new ArrayList<>(userDaoJDBC.getAll());

        for(int i = 0; i < allUsers.size(); i++) {
            if (user.getUserName().equals(allUsers.get(i).getUserName()))
                return true;
        }
        return false;
    }
}
