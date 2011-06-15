package com.restbucks.ordering.activities;

import com.restbucks.ordering.representations.*;

public class UriExchange {

    public static RestbucksUri paymentForOrder(RestbucksUri orderUri) {
        checkForValidOrderUri(orderUri);
        return new RestbucksUri(orderUri.getBaseUri() + "/payment/" + orderUri.getId().toString());
    }
    
    public static RestbucksUri orderForPayment(RestbucksUri paymentUri) {
        checkForValidPaymentUri(paymentUri);
        return new RestbucksUri(paymentUri.getBaseUri() + "/order/" + paymentUri.getId().toString());
    }

    public static RestbucksUri receiptForPayment(RestbucksUri paymentUri) {
        checkForValidPaymentUri(paymentUri);
        return new RestbucksUri(paymentUri.getBaseUri() + "/receipt/" + paymentUri.getId().toString());
    }
    
    public static RestbucksUri orderForReceipt(RestbucksUri receiptUri) {
        checkForValidReceiptUri(receiptUri);
        return new RestbucksUri(receiptUri.getBaseUri() + "/order/" + receiptUri.getId().toString());
    }

    private static void checkForValidOrderUri(RestbucksUri orderUri) {
        if(!orderUri.toString().contains("/order/")) {
            throw new RuntimeException("Invalid Order URI");
        }
    }
    
    private static void checkForValidPaymentUri(RestbucksUri payment) {
        if(!payment.toString().contains("/payment/")) {
            throw new RuntimeException("Invalid Payment URI");
        }
    }
    
    private static void checkForValidReceiptUri(RestbucksUri receipt) {
        if(!receipt.toString().contains("/receipt/")) {
            throw new RuntimeException("Invalid Receipt URI");
        }
    }
}
