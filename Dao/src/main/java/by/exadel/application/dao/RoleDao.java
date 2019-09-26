package by.exadel.application.dao;

import by.exadel.application.model.Role;
import by.exadel.application.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDao implements IDaoRole{

    public Role getOne(Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Role> cr = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = cr.from(Role.class);
        cr.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        Query<Role> query = session.createQuery(cr);
        Role role = query.getSingleResult();
        return role;
    }

}
