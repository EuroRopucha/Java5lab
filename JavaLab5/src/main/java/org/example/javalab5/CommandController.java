package org.example.javalab5;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CommandController {
    Program program = BProgram.build();
    Command c;
    @FXML
    Label lCommand;

    public void setCommand(Command c)
    {
        this.c = c;
        lCommand.setText(c.getName());
    }
    @FXML
    void removeCommand() {
        program.remove(c);

        // после удаления очистить таблицу и пересохранить все команды
        ProgramDAO dao = new ProgramDAO();
        dao.clearAll();
        for (Command cmd : program) {
            dao.add(new ProgramEntity(cmd.getCommand().toString(), cmd.getArg1(), cmd.getArg2()));
        }
    }
    @FXML
    void moveUp() {
        program.moveUp(c);
    }

    @FXML
    void moveDown() {
        program.moveDown(c);
    }

}
