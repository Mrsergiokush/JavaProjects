package by.exadel.application.service;

import java.util.Collection;

public interface ServiceJDBC <T>{

    T add(T type) throws Exception;

    boolean delete(T type) throws Exception;

    Collection<T> getAll() throws Exception;
}
