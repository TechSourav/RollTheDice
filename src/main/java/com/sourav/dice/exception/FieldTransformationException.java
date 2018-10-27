package com.sourav.dice.exception;

public class FieldTransformationException extends RuntimeException {

    public FieldTransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldTransformationException(Throwable cause) {
        super(cause);
    }

    public FieldTransformationException(String msg) {
        super(msg);
    }
}
