package by.exadel.application.dao;

import by.exadel.application.model.Task;
import by.exadel.application.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TaskDaoHibernate implements IDaoTask {

    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr  = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);

        cr.select(root).where(criteriaBuilder.equal(root.get("user"), userId));
        Query<Task> query = session.createQuery(cr);
        List<Task> tasks = query.setFirstResult(position).setMaxResults(3).getResultList();
        transaction.commit();
        return tasks;
    }

    @Override
    public Task getByUserAndId(Integer userId, String taskName) throws Exception {
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

    @Override
    public Task getById(Integer id) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        Query<Task> query = session.createQuery(cr);
        Task task = query.getSingleResult();

        transaction.commit();
        return task;
    }

    @Override
    public Task add(Task task) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);

        tx1.commit();
        return getByUserAndId(task.getUser().getId(), task.getTaskName());
    }

    @Override
    public Integer delete(Task task) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
        return 1;
    }

    @Override
    public List<Task> getAll(Integer pos) throws Exception {
        return null;
    }

    @Override
    public Integer getSize() throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //Count number of tasks
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Task> root = criteriaQuery.from(Task.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        long count = query.getSingleResult();
        return Math.toIntExact(count); //to Int
    }

    @Override
    public Integer update(Task task) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
        return 1;
    }
}
