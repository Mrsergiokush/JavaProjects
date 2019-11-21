package by.exadel.application.dao;

import static org.hibernate.criterion.Restrictions.and;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.or;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import by.exadel.application.model.Message;
import by.exadel.application.model.User;
import by.exadel.application.utils.HibernateSessionFactoryUtil;

@Repository
public class MessageDao implements IDaoMessage {

    @Override
    public Message send(Message message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(message);
            tx1.commit();
            return message;
        } catch (ConstraintViolationException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Message> getDialog(User firstUser, User secondUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criterion rest1 = and(eq("sender", firstUser), eq("receiver", secondUser));
        Criterion rest2 = and(eq("sender", secondUser), eq("receiver", firstUser));
        return session.createCriteria(Message.class)
                .add(or(rest1, rest2))
                .list();
    }

}
