package com.Car_Rental_Spring.repository.hibernate;

import com.Car_Rental_Spring.domain.hibernate.TestUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateUserDao")
public class HibernateUserDao implements HibernateUser {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<TestUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from TestUser tu", TestUser.class).getResultList();
        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        System.out.println(entityManager.toString());
//        return entityManager.createQuery("select tu from TestUser tu", TestUser.class).getResultList();
    }

    @Override
    public TestUser findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public TestUser save(TestUser entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newUserID = (Long) session.save(entity);
            transaction.commit();
            return session.find(TestUser.class, newUserID);
        }
    }

    @Override
    public TestUser update(TestUser entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(TestUser.class, entity.getUserId());
        }
    }
}
