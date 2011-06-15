package com.restbucks.ordering.activities;

public class OrderDeletionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderDeletionException() {
    }

    public OrderDeletionException( String message ) {
        super( message );
    }

    public OrderDeletionException( Throwable cause ) {
        super( cause );
    }

    public OrderDeletionException( String message, Throwable cause ) {
        super( message, cause );
    }

}
