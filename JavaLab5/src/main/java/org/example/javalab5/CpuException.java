package org.example.javalab5;

public class CpuException extends Exception {
    public CpuException(){}
    public CpuException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "CpuException: " + super.getMessage();
    }
}
