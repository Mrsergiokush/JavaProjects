package by.exadel.application.service;

import by.exadel.application.dao.Dao;
import by.exadel.application.model.User;

public class Service {

    private Dao dao = new Dao();

    public void addByUsername(User user){

        if(!compareUser(user))
            System.out.println("This user is already in list");
        else
            dao.addToList(user);
    }

    public boolean printList(){

        if (dao.getSize() == 0) {
            System.out.println("List is empty");
            return false;
        }
        else {

            System.out.printf("%-20s %-20s %-20s %-20s\n\n", "Username", "Taskname", "ID", "Deadline");
            for (int i = 0; i < dao.getSize(); i++)
                System.out.printf("%-20s %-20s %-20s %-20s\n", dao.getUserByIndex(i).getUsername(), dao.getUserByIndex(i).getTask().getTaskName(),
                        dao.getUserByIndex(i).getTask().getTaskId(), dao.getUserByIndex(i).getTask().getDeadline());
        }
        return true;

    }
    public boolean compareUser(User user){    //compare 2 users
        if(dao.getSize() == 0)
            return true;
        else{
            for(int i = 0; i < dao.getSize(); i++){
                if(dao.getUserByIndex(i).getUsername().equals(user.getUsername()))
                    return false;
            }
            return true;
        }
    }

    public void deleteData(int index){
        if(dao.getSize() == 0)
            System.out.println("List is empty");
        else if (index > dao.getSize() || index < 0)
            System.out.println("You enter uncorrect index");
        else {
            dao.deleteByIndex(index);
            System.out.println("Element was succesfuly deleted");
        }
    }

}
