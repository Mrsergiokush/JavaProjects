package by.exadel.application.dao;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import by.exadel.application.model.Task;
import by.exadel.application.utils.HibernateSessionFactoryUtil;

@Repository
public class TaskDao extends GenericDaoImpl<Task> implements IDaoTask {

    @Override
    public List<Task> getTaskByUserId(Integer userId, Integer position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("user"), userId));
        Query<Task> query = session.createQuery(cr);
        List<Task> tasks = query.setFirstResult(position).setMaxResults(5).getResultList();
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
}
