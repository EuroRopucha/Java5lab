package org.example.javalab4.entity;

import jakarta.persistence.*;
        import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("orderIndex ASC")
    private List<CommandEntity> commands = new ArrayList<>();

    public ProgramEntity() {}

    public ProgramEntity(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<CommandEntity> getCommands() { return commands; }

    public void addCommand(CommandEntity c) {
        c.setProgram(this);
        c.setOrderIndex(commands.size());
        commands.add(c);
    }

    public void removeCommand(CommandEntity c) {
        commands.remove(c);
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).setOrderIndex(i);
        }
    }
}
