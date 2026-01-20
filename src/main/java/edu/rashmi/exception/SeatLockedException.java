package edu.rashmi.exception;

public class SeatLockedException extends RuntimeException{
    public SeatLockedException(String message){
        super(message);
    }
}
