package by.aston.jdbc.repository;


import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NameRatesDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addNameRates(NameRates nameRates) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(nameRates);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean updateNameRates(NameRates nameRates) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(nameRates);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public boolean deleteNameRates(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            NameRates nameRates = session.get(NameRates.class, id);
            if (nameRates != null) {
                session.delete(nameRates);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public NameRates findById(Long id) {
        Session session = null;
        NameRates nameRates = null;
        try {
            session = sessionFactory.openSession();
            nameRates = session.get(NameRates.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return nameRates;
    }

    public List<NameRates> findAll() {
        Session session = null;
        List<NameRates> nameRates = null;
        try {
            session = sessionFactory.openSession();
            nameRates = session.createQuery("from NameRates", NameRates.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return nameRates;
    }
}