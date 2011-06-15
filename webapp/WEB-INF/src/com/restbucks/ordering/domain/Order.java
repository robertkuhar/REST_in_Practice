package com.restbucks.ordering.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class Order {
    
    private final Location location;
    private final List<Item> items;
    @XmlTransient
    private OrderStatus status = OrderStatus.UNPAID;

    public Order(Location location, List<Item> items) {
      this(location, OrderStatus.UNPAID, items);
    }
    

    public Order(Location location, OrderStatus status, List<Item> items) {
        this.location = location;
        this.items = items;
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public double calculateCost() {
        double total = 0.0;
        if (items != null) {
            for (Item item : items) {
                if(item != null && item.getDrink() != null) {
                    total += item.getDrink().getPrice();
                }
            }
        }
        return total;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Location: " + location + "\n");
        sb.append("Status: " + status + "\n");
        for(Item i : items) {
            sb.append("Item: " + i.toString()+ "\n");
        }
        return sb.toString();
    }
}