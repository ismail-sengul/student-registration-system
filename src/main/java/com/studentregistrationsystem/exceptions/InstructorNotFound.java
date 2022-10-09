package com.studentregistrationsystem.exceptions;

public class InstructorNotFound extends Exception{

    public InstructorNotFound(String message){
        super(message);
    }

    public InstructorNotFound(String message,Throwable cause){
        super(message,cause);
    }
}
