package com.studentregistrationsystem.exceptions;

public class StudentNotFound extends Exception{

    public StudentNotFound(String message){
        super(message);
    }

    public StudentNotFound(String message, Throwable cause){
        super(message,cause);
    }
}
