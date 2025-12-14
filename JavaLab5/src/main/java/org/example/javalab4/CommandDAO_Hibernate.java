package org.example.javalab4;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CommandDAO_Hibernate {

    public void save(CommandEntity command) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(command);
            tx.commit();
        }
    }

    public List<CommandEntity> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM CommandEntity", CommandEntity.class).list();
        }
    }
}
