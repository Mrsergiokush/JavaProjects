package by.exadel.application.dao;

import by.exadel.application.model.User;
import by.exadel.application.utils.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class UserDao extends GenericDaoImpl<User> implements IDaoUser {

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getByUserName(String userName, Integer from) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<User>) session.createCriteria(User.class)
                .add(eq("username", userName))
                .setMaxResults(3)
                .setFirstResult(from)
                .list();
    }

    @Override
    public User getByUserID(Integer userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = criteriaBuilder.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("id"), userId));
        Query<User> query = session.createQuery(cr);
        return query.getSingleResult();
    }

    @Override
    public User getByEmail(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = criteriaBuilder.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        Query<User> query = session.createQuery(cr);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getByAge(Integer age, Integer from) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = criteriaBuilder.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("age"), age));
        Query<User> query = session.createQuery(cr);
        return query
                .setFirstResult(from)
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public List<User> getAll(Integer pos) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        return query
                .setFirstResult(pos)
                .setMaxResults(3)
                .getResultList();
    }

}