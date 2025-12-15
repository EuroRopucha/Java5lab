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
        this.arg1 = (arg1 != null) ? arg1 : "";
        this.arg2 = (arg2 != null) ? arg2 : "";
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOpcode() { return opcode; }
    public String getArg1() { return arg1; }
    public String getArg2() { return arg2; }

    @Override
    public String toString() {
        String c = opcode + " ";
        if(arg1 != null) c+=arg1;
        if (arg2 != null) c+=arg2;
        return c;
    }
}


