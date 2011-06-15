package com.restbucks.ordering.activities;

public class OrderAlreadyCompletedException extends RuntimeException {
    private static final long serialVersionUID = 1191233614476903166L;

    public OrderAlreadyCompletedException() {
    }

    public OrderAlreadyCompletedException( String message ) {
        super( message );
    }

    public OrderAlreadyCompletedException( Throwable cause ) {
        super( cause );
    }

    public OrderAlreadyCompletedException( String message, Throwable cause ) {
        super( message, cause );
    }

}
