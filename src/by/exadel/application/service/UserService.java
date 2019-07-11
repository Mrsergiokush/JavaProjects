package by.exadel.application.service;

import by.exadel.application.dao.UserDao;
import by.exadel.application.model.User;

import java.io.IOException;

public class UserService {

    private UserDao dao = new UserDao();

    public boolean addByUsername(User user) throws IOException {
        if (!compareUser(user))
            return false;
        else
            dao.addToList(user);
        return true;
    }

    public boolean isEmpty() throws IOException{
        if (dao.getSize() == 0)
            return true;
        else
            return false;
    }

    public boolean compareUser(User user) throws IOException{    //compare 2 users
        if (dao.getSize() == 0)
            return true;
        else {
            for (int i = 0; i < dao.getSize(); i++) {
                if (dao.getUserByIndex(i).getUsername().equals(user.getUsername()))
                    return false;
            }
            return true;
        }
    }

    public boolean deleteData(int index) throws IOException{
        if (index > dao.getSize() || index < 0)
            return false;
        else {
            dao.deleteByIndex(index);
            return true;
        }
    }

    public User getUserByIndex(int index) throws IOException{        //return Users for print
        return dao.getUserByIndex(index);
    }

    public int getQuantity() throws IOException{
        return dao.getSize();
    }

    public boolean thereIsUser(String username) throws IOException{
        for(int i = 0; i < dao.getSize(); i++){
            if(dao.getUserByIndex(i).getUsername().equals(username))
                return true;
        }
        return false;
    }


}