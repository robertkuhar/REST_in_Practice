package com.restbucks.ordering.activities;

public class UpdateException extends RuntimeException {
    private static final long serialVersionUID = 703397257259555041L;
    
    public UpdateException() {
    }

    public UpdateException( String message ) {
        super( message );
    }

    public UpdateException( Throwable cause ) {
        super( cause );
    }

    public UpdateException( String message, Throwable cause ) {
        super( message, cause );
    }

}
