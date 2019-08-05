package by.exadel.application.service;

import java.util.List;

public interface IService<T> {

    T add(T type) throws Exception;

    boolean delete(T type) throws Exception;

    List<T> getAll(Integer pos) throws Exception;

    Integer getSize() throws Exception;

}
