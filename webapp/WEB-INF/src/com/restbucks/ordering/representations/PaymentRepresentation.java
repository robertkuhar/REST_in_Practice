package com.restbucks.ordering.representations;

import javax.xml.bind.annotation.*;

import com.restbucks.ordering.domain.*;

@XmlRootElement(name = "payment", namespace = Representation.RESTBUCKS_NAMESPACE)
public class PaymentRepresentation extends Representation {

    @XmlElement(namespace = PaymentRepresentation.RESTBUCKS_NAMESPACE)
    private double amount;
    @XmlElement(namespace = PaymentRepresentation.RESTBUCKS_NAMESPACE)
    private String cardholderName;
    @XmlElement(namespace = PaymentRepresentation.RESTBUCKS_NAMESPACE)
    private String cardNumber;
    @XmlElement(namespace = PaymentRepresentation.RESTBUCKS_NAMESPACE)
    private int expiryMonth;
    @XmlElement(namespace = PaymentRepresentation.RESTBUCKS_NAMESPACE)
    private int expiryYear;

    /**
     * For JAXB :-(
     */
    PaymentRepresentation() {
    }

    public PaymentRepresentation( Payment payment, Link... links ) {
        amount = payment.getAmount();
        cardholderName = payment.getCardholderName();
        cardNumber = payment.getCardNumber();
        expiryMonth = payment.getExpiryMonth();
        expiryYear = payment.getExpiryYear();
        this.links = java.util.Arrays.asList( links );
    }

    public Payment getPayment() {
        return new Payment( amount, cardholderName, cardNumber, expiryMonth, expiryYear );
    }

    public Link getReceiptLink() {
        return getLinkByName( Representation.RELATIONS_URI + "receipt" );
    }

    public Link getOrderLink() {
        return getLinkByName( Representation.RELATIONS_URI + "order" );
    }
}
