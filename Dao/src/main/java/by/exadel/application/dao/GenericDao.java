package by.exadel.application.dao;

public interface GenericDao<T> {
    T add(T type) throws Exception;

    void delete(T type) throws Exception;

    Integer getSize() throws Exception;

    void update(T type) throws Exception;
}
