package com.restbucks.ordering.activities;

public class NoSuchOrderException extends RuntimeException {
    private static final long serialVersionUID = -9172507442761193566L;

    public NoSuchOrderException() {
    }

    public NoSuchOrderException( String message ) {
        super( message );
    }

    public NoSuchOrderException( Throwable cause ) {
        super( cause );
    }

    public NoSuchOrderException( String message, Throwable cause ) {
        super( message, cause );
    }

}
