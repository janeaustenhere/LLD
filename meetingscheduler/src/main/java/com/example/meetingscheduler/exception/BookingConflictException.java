package com.example.meetingscheduler.exception;

public class BookingConflictException extends RuntimeException{

    public BookingConflictException(String message, Throwable cause){
        super(message, cause);
    }
}
