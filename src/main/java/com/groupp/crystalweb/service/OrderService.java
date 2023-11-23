package com.groupp.crystalweb.service;
import com.groupp.crystalweb.dto.request.OrderRequest;
import com.groupp.crystalweb.entity.Order;
import com.groupp.crystalweb.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(OrderRequest orderRequest){
        Order newOrder = new Order(
                orderRequest.refId(),
                orderRequest.clientRefId(),
                orderRequest.totalPrice(),
                orderRequest.orderStatus(),
                orderRequest.notes()
        );
        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders() {

        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrder(String refId) {
        Optional<Order> order = orderRepository.findByRefId(refId); //?
        if(order.isPresent()){
            return order.get();
        }
//        TODO: handle response
        return null;
    }

    public Order update(String refId, OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findByRefId(refId);
        if(order.isPresent()){
            Order existingOrder = order.get();
            existingOrder.setRefId(orderRequest.refId());
            existingOrder.setClientRefId(orderRequest.clientRefId());
            existingOrder.setTotalPrice(orderRequest.totalPrice());
            existingOrder.setOrderStatus(orderRequest.orderStatus());
            existingOrder.setNotes(orderRequest.notes());
            return orderRepository.save(existingOrder);
        }
        log.info("Order not found for id: P{}", orderRequest.refId());
        return null;
    }

    public long deleteOrder(String refId) {

        return orderRepository.deleteByRefId(refId);
    }
}

