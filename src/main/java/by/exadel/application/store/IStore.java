package by.exadel.application.store;

import java.io.IOException;
import java.util.ArrayList;

public interface IStore <T>{

    void setAll(ArrayList<T> type) throws IOException;

    ArrayList<T> getAll() throws IOException;

}
