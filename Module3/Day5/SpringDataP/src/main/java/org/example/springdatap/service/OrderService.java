package org.example.springdatap.service;

import org.example.springdatap.exception.OrderNotFound;
import org.example.springdatap.model.Order;
import org.example.springdatap.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {

        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("Order not found with id: " + id));
    }

    public Order updateOrder(Long id, Order order) {
        Order existing = getOrderById(id);
        existing.setCustomerName(order.getCustomerName());
        existing.setCustomerEmail(order.getCustomerEmail());
        existing.setStatus(order.getStatus());
        return orderRepository.save(existing);
    }

    public void deleteOrder(Long id) {
        getOrderById(id);
        orderRepository.deleteById(id);
    }
}