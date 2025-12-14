package org.example.javalab4;

import jakarta.persistence.*;

@Entity
@Table(name = "commands")
public class CommandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProgramEntity program;

    @Enumerated(EnumType.STRING)
    private typeCommand opcode;

    private String arg1;
    private String arg2;
    private Integer orderIndex;

    public CommandEntity() {}

    public CommandEntity(typeCommand opcode, String arg1, String arg2, int orderIndex) {
        this.opcode = opcode;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.orderIndex = orderIndex;
    }


    public Long getId() { return id; }
    public ProgramEntity getProgram() { return program; }
    public void setProgram(ProgramEntity program) { this.program = program; }

    public typeCommand getOpcode() { return opcode; }
    public void setOpcode(typeCommand opcode) { this.opcode = opcode; }

    public String getArg1() { return arg1; }
    public void setArg1(String arg1) { this.arg1 = arg1; }

    public String getArg2() { return arg2; }
    public void setArg2(String arg2) { this.arg2 = arg2; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
