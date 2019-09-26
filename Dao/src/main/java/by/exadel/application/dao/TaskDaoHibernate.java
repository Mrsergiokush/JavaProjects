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

@Repository
public class TaskDaoHibernate implements IDaoTask {

//    public static final Logger logger = Logger.getLogger(TaskDaoHibernate.class);

    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);

        cr.select(root).where(criteriaBuilder.equal(root.get("user"), userId));
        Query<Task> query = session.createQuery(cr);
        List<Task> tasks = query.setFirstResult(position).setMaxResults(3).getResultList();

//        logger.info("Get tasks by User Id");

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
//            logger.info("Get task by Name and Id");
            return task;
        } catch (NoResultException e) {
//            logger.info("Get task by Name and Id was FAILED");
            return null;
        }
    }

    @Override
    public Task getById(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        Query<Task> query = session.createQuery(cr);

        try {
            Task task = query.getSingleResult();
//            logger.info("Get task By Id");
            return task;
        } catch (NoResultException e) {
//            logger.info("Get task by ID was FAILED");
            return null;
        }
    }

    @Override
    public Task add(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        try {
            session.save(task);
            tx1.commit();
//            logger.info("Task was added successfully");
            return getByUserAndId(task.getUser().getId(), task.getTaskName());
        } catch (ConstraintViolationException e) { // two the same tasks
//            logger.info("Task wasn't added (UNIQUE CONSTRAINT)");
            return null;
        }

    }

    @Override
    public Integer delete(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
//        logger.info("Task was successfully deleted");
        session.close();
        return 1;
    }

    @Override
    public List<Task> getAll(Integer pos) {
        return null;
    }

    @Override
    public Integer getSize() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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
//        logger.info("Task was successfully updated");
        tx1.commit();
        session.close();
        return 1;
    }
}
