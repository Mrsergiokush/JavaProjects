package by.exadel.application.dao;

import java.util.Collection;

public interface DaoJDBC <T>{

    T add(T type) throws Exception;

    int delete(T type) throws Exception;

    Collection<T> getAll() throws Exception;
}