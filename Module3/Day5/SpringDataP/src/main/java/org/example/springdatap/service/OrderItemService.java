package org.example.springdatap.service;

import org.example.springdatap.exception.OrderNotFound;
import org.example.springdatap.model.OrderItem;
import org.example.springdatap.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("OrderItem not found with id: " + id));
    }

    public void deleteOrderItem(Long id) {
        getOrderItemById(id);
        orderItemRepository.deleteById(id);
    }
}