package org.example.javalab4;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProgramDAO_Hibernate {

    public ProgramEntity loadOrCreateDefault() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            ProgramEntity program = session.createQuery(
                    "FROM ProgramEntity", ProgramEntity.class
            ).setMaxResults(1).uniqueResult();

            if (program == null) {
                program = new ProgramEntity("Default");
                session.persist(program);
            }

            tx.commit();
            return program;
        }
    }

    public void save(ProgramEntity program) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(program); // сохраняет и обновляет каскадно
            tx.commit();
        }
    }
}
