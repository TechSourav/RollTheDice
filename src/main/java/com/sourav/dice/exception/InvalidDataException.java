package com.sourav.dice.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String msg) {
        super(msg);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(Throwable cause, String msg) {
        super(msg, cause);
    }
}
