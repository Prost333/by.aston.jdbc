package by.aston.jdbc.repository;


import by.aston.jdbc.entity.City;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Repository
public class CityDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addCity(City city) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("some problem");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean updateCity(City city) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("some problem");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean deleteCity(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            City city = session.get(City.class, id);
            if (city != null) {
                session.delete(city);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("some problem");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public City findById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(City.class, id);
        } catch (Exception e) {
            throw new EntityNotFoundException("city not found");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<City> findByAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("from City", City.class).list();
        } catch (Exception e) {
            throw new EntityNotFoundException("city not found");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}