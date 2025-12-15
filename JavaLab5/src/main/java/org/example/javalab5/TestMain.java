package org.example.javalab5;


import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        ProgramDAO dao = new ProgramDAO();

        // очистим таблицу перед тестом
        dao.clearAll();

        // добавим несколько команд
        dao.add(new ProgramEntity("init", "100", null));
        dao.add(new ProgramEntity("ld", "R1", "100"));
        dao.add(new ProgramEntity("st", "R1", "200"));

        // получим все команды из базы
        List<ProgramEntity> commands = dao.getAllCommands();

        System.out.println("Команды в базе:");
        for (ProgramEntity cmd : commands) {
            System.out.println(cmd);
        }

        // закрываем Hibernate
        HibernateUtil.shutdown();
    }
}
