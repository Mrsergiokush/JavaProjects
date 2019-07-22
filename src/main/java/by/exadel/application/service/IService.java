package by.exadel.application.service;

import java.util.Collection;

public interface IService<T>{

    T add(T type) throws Exception;

    boolean delete(T type) throws Exception;

    Collection<T> getAll() throws Exception;

    Integer getId(T type) throws Exception;
}
