package by.exadel.application.dao;

public interface GenericDao<ENTITY> {
    ENTITY add(ENTITY type) throws Exception;

    void delete(ENTITY type) throws Exception;

    Integer getSize() throws Exception;

    void update(ENTITY type) throws Exception;
}
