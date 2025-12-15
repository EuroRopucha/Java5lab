package org.example.javalab5;

public class BCpu {
    static Cpu cpu;

    public static ICpu build() {
        if (cpu == null) {
            cpu = new Cpu();
            cpu.getHandler()
                    .add(new MemoryHandler())
                    .add(new ArithmeticHandler())
                    .add(new UtilityHandler());
        }
        return cpu;
    }
}
