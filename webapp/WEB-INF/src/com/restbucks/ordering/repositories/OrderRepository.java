package com.restbucks.ordering.repositories;

import java.util.HashMap;
import java.util.Map.Entry;

import com.restbucks.ordering.domain.Identifier;
import com.restbucks.ordering.domain.Order;

public class OrderRepository {

    private static final OrderRepository theRepository = new OrderRepository();
    private HashMap<String, Order> backingStore = new HashMap<String, Order>(); // Default implementation, not suitable for production!

    public static OrderRepository current() {
        return theRepository;
    }
    
    private OrderRepository(){}
    
    public Order get(Identifier identifier) {
        return backingStore.get(identifier.toString());
     }
    
    public Order take(Identifier identifier) {
        Order order = backingStore.get(identifier.toString());
        remove(identifier);
        return order;
    }

    public Identifier store(Order order) {
        Identifier id = new Identifier();
        backingStore.put(id.toString(), order);
        return id;
    }
    
    public void store(Identifier orderIdentifier, Order order) {
        backingStore.put(orderIdentifier.toString(), order);
    }

    public boolean has(Identifier identifier) {
        boolean result =  backingStore.containsKey(identifier.toString());
        return result;
    }

    public void remove(Identifier identifier) {
        backingStore.remove(identifier.toString());
    }
    
    public boolean orderPlaced(Identifier identifier) {
        return OrderRepository.current().has(identifier);
    }
    
    public boolean orderNotPlaced(Identifier identifier) {
        return !orderPlaced(identifier);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, Order> entry : backingStore.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\t:\t");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    public synchronized void clear() {
        backingStore = new HashMap<String, Order>();
    }

    public int size() {
        return backingStore.size();
    }
}
