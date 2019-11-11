package by.exadel.application.dao;

public interface IDao <T>{

    T add(T type) throws Exception;

    Integer delete(T type) throws Exception;

    Integer getSize() throws Exception;

    Integer update(T type) throws Exception;

}

