package by.exadel.application.service;

import by.exadel.application.dao.Dao;
import by.exadel.application.model.User;

public class Service {

    private Dao dao = new Dao();

    public boolean addByUsername(User user) {

        if (!compareUser(user))
            return false;
        else
            dao.addToList(user);
        return true;
    }

    public boolean isEmpty() {

        if (dao.getSize() == 0)
            return true;
        else
            return false;
    }

    public boolean compareUser(User user) {    //compare 2 users
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

    public boolean deleteData(int index) {
        if (index > dao.getSize() || index < 0)
            return false;
        else {
            dao.deleteByIndex(index);
            return true;
        }
    }

    public User getUser(int index) {        //return Users for print
        return dao.getUserByIndex(index);
    }

}
