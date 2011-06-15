package com.restbucks.ordering.activities;

public class InvalidOrderException extends RuntimeException {
    private static final long serialVersionUID = -9172507442761193566L;

    public InvalidOrderException() {
    }

    public InvalidOrderException( String message ) {
        super( message );
    }

    public InvalidOrderException( Throwable cause ) {
        super( cause );
    }

    public InvalidOrderException( String message, Throwable cause ) {
        super( message, cause );
    }

}
