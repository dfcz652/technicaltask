package com.example.technicaltask.exceptions;

public class Status409UserNotFoundException extends ErrorCodeException{
    public static final int CODE = 409;

    public Status409UserNotFoundException(String id) {
        super(CODE, "User Not Found for id:" + id);
    }

    public Status409UserNotFoundException() {
        super(CODE, "No user found for this range of dates");
    }
}
