package by.aston.jdbc.repository;


import by.aston.jdbc.dto.DriveResp;
import by.aston.jdbc.entity.Drive;
import by.aston.jdbc.entity.User;
import by.aston.jdbc.exeption.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DriveDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void addDrive(Drive drive) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            if (drive.getUser() != null) {
                User user = session.createQuery("from User where id = :id", User.class)
                        .setParameter("id", drive.getUser().getId())
                        .uniqueResult();
                if (user == null) {
                    user = new User();
                    user.setId(drive.getUser().getId());
                    session.save(user);
                }
                drive.setUser(user);
            }

            session.save(drive);

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

    public DriveResp findById(Long id) {
        Session session = null;
        DriveResp drive = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("select d.km, d.time, d.surge, d.city, d.rate, d.paidTime, " +
                    "d.doorToDoor, d.paidSubmission, d.dopSum, u.name " +
                    "from Drive d " +
                    "join d.user u " +
                    "where d.id = :id");
            query.setParameter("id", id);
            List<Object[]> result = query.list();
            for (Object[] row : result) {
                drive = new DriveResp((BigDecimal)row[0], (BigDecimal)row[1], (BigDecimal)row[2], (String)row[3],
                        (String)row[4], (BigDecimal)row[5], (Integer)row[6], (BigDecimal)row[7],
                        (BigDecimal)row[8], (String)row[9]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return drive;
    }

    public List<Drive> findAll() {
        Session session = null;
        List<Drive> drives = null;
        try {
            session = sessionFactory.openSession();
            drives = session.createQuery("from Drive", Drive.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return drives;
    }

    public void updateDrive(Drive drive) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(drive);
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

    public void deleteDrive(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Drive drive = session.get(Drive.class, id);
            if (drive != null) {
                session.delete(drive);
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
    }

    public List<DriveResp> findAllToResponse() {
        List<DriveResp> drives = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("select d.km, d.time, d.surge, d.city, d.rate, d.paidTime, " +
                    "d.doorToDoor, d.paidSubmission, d.dopSum, u.name " +
                    "from Drive d " +
                    "join d.user u");
            List<Object[]> result = query.list();
            for (Object[] row : result) {
                drives.add(new DriveResp((BigDecimal)row[0], (BigDecimal)row[1], (BigDecimal)row[2], (String)row[3],
                        (String)row[4], (BigDecimal)row[5], (Integer)row[6], (BigDecimal)row[7],
                        (BigDecimal)row[8], (String)row[9]));
            }
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
