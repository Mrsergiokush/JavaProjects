package by.exadel.application.dao;

import java.util.Collection;

public interface IDao <T>{

    T add(T type) throws Exception;

    Integer delete(T type) throws Exception;

    Collection<T> getAll() throws Exception;
}

