package by.exadel.application.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import by.exadel.application.utils.HibernateSessionFactoryUtil;

/**
 * @param <T>
 * @author Sergej Kushner
 */

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @SuppressWarnings("unchecked")
    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @Override
    public T add(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(entity);
            tx1.commit();
            return entity;
        } catch (ConstraintViolationException e) {
            return null;
        }
    }

    @Override
    public void delete(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public Integer getSize() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        long count = query.getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public void update(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }
}
