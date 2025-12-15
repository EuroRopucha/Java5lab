package org.example.javalab5;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {

    // получить все команды из таблицы
    public List<ProgramEntity> getAllCommands() {
        List<ProgramEntity> result = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            result.addAll(
                    session.createQuery("FROM ProgramEntity", ProgramEntity.class)
                            .getResultList()
            );

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // добавить команду
    public void add(ProgramEntity command) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(command);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // очистить таблицу
    public void clearAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE FROM ProgramEntity").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
