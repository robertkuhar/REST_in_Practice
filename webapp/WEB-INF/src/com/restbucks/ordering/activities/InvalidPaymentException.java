package com.restbucks.ordering.activities;

public class InvalidPaymentException extends RuntimeException {
    private static final long serialVersionUID = 2246194025990136268L;

    public InvalidPaymentException() {
    }

    public InvalidPaymentException( String message ) {
        super( message );
    }

    public InvalidPaymentException( Throwable cause ) {
        super( cause );
    }

    public InvalidPaymentException( String message, Throwable cause ) {
        super( message, cause );
    }

}
