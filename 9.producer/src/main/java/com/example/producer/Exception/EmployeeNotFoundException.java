package com.example.producer.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    private static final long SerialVersionUID = 1L;

    public EmployeeNotFoundException(){
        super();
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
