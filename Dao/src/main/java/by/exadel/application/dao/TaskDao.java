package by.exadel.application.dao;

import by.exadel.application.model.Task;
import by.exadel.application.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class TaskDao implements IDaoTask {

    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("user"), userId));
        Query<Task> query = session.createQuery(cr);
        List<Task> tasks = query.setFirstResult(position).setMaxResults(3).getResultList();
        session.close();
        return tasks;
    }

    @Override
    public Task getByUserAndId(Integer userId, String taskName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        Predicate[] predicates = new Predicate[2];
        predicates[0] = criteriaBuilder.equal(root.get("user"), userId);
        predicates[1] = criteriaBuilder.equal(root.get("taskName"), taskName);
        cr.select(root).where(predicates);
        Query<Task> query = session.createQuery(cr);
        try {
            Task task = query.getSingleResult();
            transaction.commit();
            return task;
        } catch (NoResultException e) {
            return null;
        }
    }

    ////TODO This method can return null value so I must think about solution of this problem
    @Override
    public Task getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (Task) session.createCriteria(Task.class)
                .add(eq("id", id))
                .uniqueResult();
    }

    @Override
    public Task add(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(task);
            tx1.commit();
            return getByUserAndId(task.getUser().getId(), task.getTaskName());
        } catch (ConstraintViolationException e) {
            return null;
        }
    }

    @Override
    public Integer delete(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
        return 1;
    }

    @Override
    public Integer getSize() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Task> root = criteriaQuery.from(Task.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        Long count = query.getSingleResult();
        return Math.toIntExact(count); //to Int
    }

    @Override
    public Integer update(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
        return 1;
    }
}
