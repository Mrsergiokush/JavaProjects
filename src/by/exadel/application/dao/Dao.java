package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.store.Store;

public class Dao {

    private Store store = new Store();

    public void addToList(User user) {
        store.list.add(user);
    }


    public User getUserByIndex(int i) {
        return store.list.get(i);
    }

    public int getSize() {
        return store.list.size();
    }

    public void deleteByIndex(int i) {
        store.list.remove(i);
    }

}
