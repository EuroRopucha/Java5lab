package org.example.javalab5;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {
    private Session session = null;
    private Transaction transaction = null;

    // получить все команды из таблицы
    public List<ProgramEntity> getAllCommands() {
        List<ProgramEntity> result = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            List<ProgramEntity> entities = session.createQuery("FROM ProgramEntity", ProgramEntity.class)
                    .getResultList();
            result.addAll(entities);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }

    // добавить команду
    public void add(ProgramEntity command) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.persist(command);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // удалить все команды
    public void clearAll() {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM ProgramEntity").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // удалить конкретную команду
    public void remove(ProgramEntity command) {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            session.remove(command);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

