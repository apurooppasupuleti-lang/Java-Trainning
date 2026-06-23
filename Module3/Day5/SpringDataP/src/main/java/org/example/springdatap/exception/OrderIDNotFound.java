package org.example.springdatap.exception;

public class OrderIDNotFound extends RuntimeException {
    public OrderIDNotFound(String message) {
        super(message);
    }
}
