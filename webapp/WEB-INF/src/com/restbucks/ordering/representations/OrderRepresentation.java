package com.restbucks.ordering.representations;

import java.io.*;
import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

import com.restbucks.ordering.activities.*;
import com.restbucks.ordering.domain.*;

@XmlRootElement(name = "order", namespace = Representation.RESTBUCKS_NAMESPACE)
public class OrderRepresentation extends Representation {

    @XmlElement(name = "item", namespace = Representation.RESTBUCKS_NAMESPACE)
    private List< Item> items;
    @XmlElement(name = "location", namespace = Representation.RESTBUCKS_NAMESPACE)
    private Location location;
    @XmlElement(name = "cost", namespace = Representation.RESTBUCKS_NAMESPACE)
    private double cost;
    @XmlElement(name = "status", namespace = Representation.RESTBUCKS_NAMESPACE)
    private OrderStatus status;

    /**
     * For JAXB :-(
     */
    OrderRepresentation() {
    }

    public static OrderRepresentation fromXmlString( String xmlRepresentation ) {
        try {
            JAXBContext context = JAXBContext.newInstance( OrderRepresentation.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (OrderRepresentation) unmarshaller.unmarshal( new ByteArrayInputStream( xmlRepresentation
                    .getBytes() ) );
        } catch ( Exception e ) {
            throw new InvalidOrderException( e );
        }
    }

    public static OrderRepresentation createResponseOrderRepresentation( Order order, RestbucksUri orderUri ) {
        RestbucksUri paymentUri = new RestbucksUri( orderUri.getBaseUri() + "/payment/"
                + orderUri.getId().toString() );
        if ( order.getStatus() == OrderStatus.UNPAID ) {
            return new OrderRepresentation( order, new Link( RELATIONS_URI + "cancel", orderUri ), new Link(
                    RELATIONS_URI + "payment", paymentUri ), new Link( RELATIONS_URI + "update", orderUri ),
                    new Link( Representation.SELF_REL_VALUE, orderUri ) );
        } else if ( order.getStatus() == OrderStatus.PREPARING ) {
            return new OrderRepresentation( order, new Link( Representation.SELF_REL_VALUE, orderUri ) );
        } else if ( order.getStatus() == OrderStatus.READY ) {
            return new OrderRepresentation( order, new Link( Representation.RELATIONS_URI + "reciept",
                    UriExchange.receiptForPayment( paymentUri ) ) );
        } else if ( order.getStatus() == OrderStatus.TAKEN ) {
            return new OrderRepresentation( order );
        } else {
            throw new RuntimeException( "Unknown Order Status" );
        }
    }

    public OrderRepresentation( Order order, Link... links ) {
        try {
            this.location = order.getLocation();
            this.items = order.getItems();
            this.cost = order.calculateCost();
            this.status = order.getStatus();
            this.links = java.util.Arrays.asList( links );
        } catch ( Exception ex ) {
            throw new InvalidOrderException( ex );
        }
    }

    public String toString() {
        try {
            JAXBContext context = JAXBContext.newInstance( OrderRepresentation.class );
            Marshaller marshaller = context.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal( this, stringWriter );

            return stringWriter.toString();
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    public Order getOrder() {
        if ( location == null || items == null ) {
            throw new InvalidOrderException();
        }
        for ( Item i : items ) {
            if ( i == null ) {
                throw new InvalidOrderException();
            }
        }

        return new Order( location, status, items );
    }

    public Link getCancelLink() {
        return getLinkByName( RELATIONS_URI + "cancel" );
    }

    public Link getPaymentLink() {
        return getLinkByName( RELATIONS_URI + "payment" );
    }

    public Link getUpdateLink() {
        return getLinkByName( RELATIONS_URI + "update" );
    }

    public Link getSelfLink() {
        return getLinkByName( "self" );
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getCost() {
        return cost;
    }
}
