package org.example.javalab4;

public class ProgramAdapter {

    public static ProgramEntity toEntity(Program program) {
        ProgramEntity entity = new ProgramEntity("Default"); // имя программы фиксированное
        int orderIndex = 0;
        for (Command cmd : program) { // Program implements Iterable<Command>
            CommandEntity commandEntity = new CommandEntity(
                    cmd.getCommand(),   // typeCommand
                    cmd.getArg1(),
                    cmd.getArg2(),
                    orderIndex++
            );
            // вместо прямого добавления в список вызываем addCommand
            entity.addCommand(commandEntity);
        }
        return entity;
    }


    public static Program fromEntity(ProgramEntity entity) {
        Program program = new Program(); // у тебя Program без конструктора с именем
        for (CommandEntity cmdEntity : entity.getCommands()) {
            Command cmd = new Command(
                    cmdEntity.getOpcode(), // typeCommand
                    cmdEntity.getArg1(),
                    cmdEntity.getArg2()
            );
            program.add(cmd);
        }
        return program;
    }
}

