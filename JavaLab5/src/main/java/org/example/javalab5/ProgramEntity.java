package org.example.javalab5;

import jakarta.persistence.*;

@Entity
@Table(name = "commands")
public class ProgramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // тип команды (opcode)
    @Column(name = "opcode", nullable = false)
    private String opcode;

    @Column(name = "arg1")
    private String arg1;

    @Column(name = "arg2")
    private String arg2;

    // конструкторы
    public ProgramEntity() {}

    public ProgramEntity(String opcode, String arg1, String arg2) {
        this.opcode = opcode;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOpcode() { return opcode; }
    public void setOpcode(String opcode) { this.opcode = opcode; }

    public String getArg1() { return arg1; }
    public void setArg1(String arg1) { this.arg1 = arg1; }

    public String getArg2() { return arg2; }
    public void setArg2(String arg2) { this.arg2 = arg2; }

    @Override
    public String toString() {
        return opcode + " " +
                (arg1 != null ? arg1 : "") +
                (arg2 != null ? " " + arg2 : "");
    }
}


