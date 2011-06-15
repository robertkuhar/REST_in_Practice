package com.restbucks.ordering.activities;

public class OrderNotPaidException extends RuntimeException {
    private static final long serialVersionUID = -5111801508782989850L;

    public OrderNotPaidException() {
    }

    public OrderNotPaidException( String message ) {
        super( message );
    }

    public OrderNotPaidException( Throwable cause ) {
        super( cause );
    }

    public OrderNotPaidException( String message, Throwable cause ) {
        super( message, cause );
    }

}
