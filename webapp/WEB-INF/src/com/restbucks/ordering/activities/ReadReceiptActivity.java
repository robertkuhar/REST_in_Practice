package com.restbucks.ordering.activities;

import com.restbucks.ordering.domain.*;
import com.restbucks.ordering.repositories.*;
import com.restbucks.ordering.representations.*;

public class ReadReceiptActivity {

    public ReceiptRepresentation read(RestbucksUri receiptUri) {
        Identifier identifier = receiptUri.getId();
        if(!orderHasBeenPaid(identifier)) {
            throw new OrderNotPaidException();
        } else if (OrderRepository.current().has(identifier) && OrderRepository.current().get(identifier).getStatus() == OrderStatus.TAKEN) {
            throw new OrderAlreadyCompletedException();
        }
        
        Payment payment = PaymentRepository.current().get(identifier);
        
        return new ReceiptRepresentation(payment, new Link(Representation.RELATIONS_URI + "order", UriExchange.orderForReceipt(receiptUri)));
    }

    private boolean orderHasBeenPaid(Identifier id) {
        return PaymentRepository.current().has(id);
    }

}
