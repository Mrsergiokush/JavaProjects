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
public class UserDao implements IDaoUser {

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
        User user = query.getSingleResult();
        return user;
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
            User user = query.getSingleResult();
            return user;
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
        List<User> users = query.setFirstResult(from).setMaxResults(3).getResultList();
        return users;
    }

    @Override
    public User add(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(user);
            tx1.commit();
            return getByEmail(user.getEmail());
        } catch (ConstraintViolationException e) {
            return null;
        }
    }

    @Override
    public Integer delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getByUserID(user.getId()));
        tx1.commit();
        session.close();
        return 1;
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

    @Override
    public Integer getSize() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        long count = query.getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public Integer update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
        return 1;
    }
}