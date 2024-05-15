package by.aston.jdbc.repository;

import by.aston.jdbc.entity.City;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.entity.Rates;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RatesDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addRates(Rates rates) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            if (rates.getCity() != null) {
                City city = session.createQuery("from City where name = :name", City.class)
                        .setParameter("name", rates.getCity().getName())
                        .uniqueResult();
                if (city == null) {
                    city = new City();
                    city.setName(rates.getCity().getName());
                    session.save(city);
                }
                rates.setCity(city);
            }

            if (rates.getName() != null) {
                NameRates nameRates = session.createQuery("from NameRates where name = :name", NameRates.class)
                        .setParameter("name", rates.getName().getName())
                        .uniqueResult();
                if (nameRates == null) {
                    nameRates = new NameRates();
                    nameRates.setName(rates.getName().getName());
                    session.save(nameRates);
                }
                rates.setName(nameRates);
            }

            session.save(rates);

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

    public boolean updateRates(Rates rates) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(rates);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRates(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Rates rates = session.get(Rates.class, id);
            if (rates != null) {
                session.delete(rates);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Rates findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Rates r LEFT JOIN FETCH r.city WHERE r.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return (Rates) query.uniqueResult();
        }
    }

    public List<Rates> findAll() {
        Session session = null;
        List<Rates> drives = null;
        try {
            session = sessionFactory.openSession();
            drives = session.createQuery("from Rates", Rates.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return drives;
    }

}
