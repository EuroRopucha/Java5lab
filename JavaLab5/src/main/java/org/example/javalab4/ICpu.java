package org.example.javalab4;

public interface ICpu {
    void exec(Command command) throws CpuException;

    int getRegister(String name) throws CpuException;
    void setRegister(String name, int value) throws CpuException;
    int[] getMemory();
}
