
package org.example.javalab4;

public class TestMain {
    public static void main(String[] args) {
        ProgramDAO_Hibernate dao = new ProgramDAO_Hibernate();

        // Загружаем или создаём программу
        ProgramEntity entity = dao.loadOrCreateDefault();
        Program program = ProgramAdapter.fromEntity(entity);

        // Добавляем команды в эмулятор
        program.add(new Command(typeCommand.ld, "R1", "100"));
        program.add(new Command(typeCommand.st, "R1", "200"));

        // Сохраняем обратно
        dao.save(ProgramAdapter.toEntity(program));

        // Проверяем загрузку
        ProgramEntity loaded = dao.loadOrCreateDefault();
        Program restored = ProgramAdapter.fromEntity(loaded);

        System.out.println("Команды после загрузки:");
        for (Command c : restored) {
            System.out.println(c);
        }
    }
}


