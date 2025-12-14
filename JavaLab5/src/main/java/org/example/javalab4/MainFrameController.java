package org.example.javalab4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.*;

import java.io.IOException;

public class MainFrameController implements IObserver {
    Program program = BProgram.build();
    ICpu cpu = BCpu.build();

    @FXML
    GridPane allCommands;
    @FXML
    TextField fCommand;
    @FXML
    GridPane registersGrid;
    @FXML
    GridPane memoryGrid;
    @FXML
    GridPane statsGrid;

    @FXML
    void initialize() {
        program.addListener(this);
        refreshUI();
    }

    @FXML
    void addCommand() {
        try {
            Command c = new Command(fCommand.getText());
            program.add(c);
        } catch (CpuException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void event() {
        allCommands.getChildren().clear();
        int idx = 0;
        int pc = program.getPc();

        for (Command c : program) {
            CommandController cc = new CommandController();
            FXMLLoader loader = new FXMLLoader(
                    MainFrameController.class.getResource("ViewCommand.fxml"));
            loader.setController(cc);
            try {
                Pane pane = loader.load();
                cc.setCommand(c);

                if (idx == pc) {
                    pane.setStyle("-fx-background-color: #ffe5e5;"); // красный фон
                }
                allCommands.addColumn(0, pane);
                idx++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        refreshUI();
    }

    void updateRegisters() {
        registersGrid.getChildren().clear();
        try {
            registersGrid.add(new Label("a = " + cpu.getRegister("a")), 0, 0);
            registersGrid.add(new Label("b = " + cpu.getRegister("b")), 0, 1);
            registersGrid.add(new Label("c = " + cpu.getRegister("c")), 0, 2);
            registersGrid.add(new Label("d = " + cpu.getRegister("d")), 0, 3);
        } catch (CpuException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    void updateMemory() {
        memoryGrid.getChildren().clear();
        int[] mem = cpu.getMemory();
        int cols = 4;
        for (int i = 0; i < 20; i++) {
            int row = i / cols;
            int col = i % cols;
            Label label = new Label("[" + i + "]=" + mem[i]);
            memoryGrid.add(label, col, row);
        }
    }
    void updateStats() {
        statsGrid.getChildren().clear();
        int row = 0;
        for (String s : program.instructionsByFrequency()) {
            statsGrid.add(new Label(s), 0, row++);
        }
    }
    void refreshUI() {
        updateRegisters();
        updateMemory();
        updateStats();
    }
    @FXML
    void nextStep() {
        Command cur = program.current();
        if (cur == null) return;
        try {
            cpu.exec(cur);
            program.setPc(program.getPc() + 1);
            refreshUI();
        } catch (CpuException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    @FXML
    void resetExecution() {
        program.reset();
        Arrays.fill(cpu.getMemory(), 0);
        try {
            cpu.setRegister("a", 0);
            cpu.setRegister("b", 0);
            cpu.setRegister("c", 0);
            cpu.setRegister("d", 0);
        } catch (CpuException e) {
            System.out.println("Ошибка при сбросе регистров: " + e.getMessage());
        }
        refreshUI();
    }
}
