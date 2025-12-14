package org.example.javalab4;

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
    void removeCommand(){
        program.remove(c);
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
