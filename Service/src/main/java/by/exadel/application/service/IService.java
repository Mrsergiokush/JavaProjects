package by.exadel.application.service;

public interface IService<T> {

    T add(T type) throws Exception;

    boolean delete(T type) throws Exception;

    Integer getSize() throws Exception;

}
