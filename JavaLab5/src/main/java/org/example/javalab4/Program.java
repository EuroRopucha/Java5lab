package org.example.javalab4;

import java.util.*;
import java.util.stream.*;

public class Program implements Iterable<Command>{
    List<Command> commands = new ArrayList<>();
    int pc = 0;

    public int getPc() { return pc; }
    public void setPc(int pc) { this.pc = pc; event(); }

    public boolean hasNext() { return pc >= 0 && pc < commands.size(); }

    public Command current() {
        if(hasNext()) return commands.get(pc);
        return null; }

    public void reset() { pc = 0; event(); }

    public void add(Command c) {
        commands.add(c);
        event();
    }
    public void remove(Command c) {
        commands.remove(c);
        event();
    }
    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

    // самая популярная инструкция
    public typeCommand mostPopularInstruction() {
        return commands.stream()
                .collect(Collectors.groupingBy(Command::getCommand, Collectors.counting())) //создаем map
                .entrySet().stream() //получаем множество пар
                // и превращаем Set<Map.Entry<typeCommand, Long>> в Stream<Map.Entry<typeCommand, Long>>
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    // диапазон адресов памяти
    public int[] memoryRange() {
        List<Integer> addresses = commands.stream()
                .filter(c -> c.getCommand() == typeCommand.init)   // только init
                .map(c -> Integer.parseInt(c.getArg1())) //преобразовали Stream<Command> в Stream<Integer>
                .collect(Collectors.toList());
        if (addresses.isEmpty()) { return new int[]{}; }

        int min = addresses.stream().min(Integer::compareTo).orElse(-1);
        int max = addresses.stream().max(Integer::compareTo).orElse(-1);

        return new int[]{min, max};
    }

    // список инструкций по частоте
    public List<String> instructionsByFrequency() {
        return commands.stream()
                .collect(Collectors.groupingBy(Command::getCommand, Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue())) // по убыванию
                .map(c -> c.getKey() + " = " + c.getValue())
                .collect(Collectors.toList());
    }


    List<IObserver> observers = new ArrayList<>();

    public void addListener(IObserver o) {
        observers.add(o);
        event();
    }
    private void event() {
        observers.forEach(action->action.event());
    }
    public void moveUp(Command c) {
        int index = commands.indexOf(c);
        if (index > 0) {
            Collections.swap(commands, index, index - 1);
            event();
        }
    }
    public void moveDown(Command c) {
        int index = commands.indexOf(c);
        if (index >= 0 && index < commands.size() - 1) {
            Collections.swap(commands, index, index + 1);
            event();
        }
    }
}

