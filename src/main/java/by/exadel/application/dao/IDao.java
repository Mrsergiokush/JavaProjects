package by.exadel.application.dao;

import java.util.List;

public interface IDao <T>{

    T add(T type) throws Exception;

    Integer delete(T type) throws Exception;

    List<T> getAll() throws Exception;

    T get(T type) throws Exception;
}

