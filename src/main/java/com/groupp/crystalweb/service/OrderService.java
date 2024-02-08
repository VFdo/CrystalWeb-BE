package com.groupp.crystalweb.service;
import com.groupp.crystalweb.dto.request.OrderRequest;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.entity.Order;
import com.groupp.crystalweb.repository.ClientRepository;
import com.groupp.crystalweb.repository.InventoryRepository;
import com.groupp.crystalweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final InventoryRepository inventoryRepository;

    public Order saveOrder(OrderRequest orderRequest){
        Optional<Client> client = clientRepository.findByRefId(orderRequest.clientRefId());
        float total = 0;
        List<Inventory> itemList = new ArrayList<>();
        for(String i : orderRequest.items()){
            Optional<Inventory> item = inventoryRepository.findByRefId(i);
            if(item.isPresent()){
                Inventory invItem = item.get();
                itemList.add(invItem);
//                TODO: add quantity to bill
                total += invItem.getUnitPrice();
            }
        }
        Order newOrder = new Order();
        if(client.isPresent()){
            newOrder.setClientRefId(client.get());
        }
        newOrder.setItems(itemList);
        newOrder.setTotalPrice(total);
        newOrder.setOrderStatus(orderRequest.orderStatus());
        newOrder.setNotes(orderRequest.notes());
        log.info(newOrder.getRefId());
        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrder(String refId) {
        Optional<Order> order = orderRepository.findByRefId(refId);
        if(order.isPresent()){
            return order.get();
        }
//        TODO: handle response
        return null;
    }

    public Order update(String refId, OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findByRefId(refId);
        if(order.isPresent()){
            Optional<Client> client = clientRepository.findByRefId(orderRequest.clientRefId());
            float total = 0;
            List<Inventory> itemList = new ArrayList<>();
            for(String i : orderRequest.items()){
                Optional<Inventory> item = inventoryRepository.findByRefId(i);
                if(item.isPresent()){
                    Inventory invItem = item.get();
                    itemList.add(invItem);
//                TODO: add quantity to bill
                    total += invItem.getUnitPrice();
                }
            }
            Order existingOrder = order.get();
            if(client.isPresent()){
                existingOrder.setClientRefId(client.get());
            }
            existingOrder.setItems(itemList);
            existingOrder.setTotalPrice(total);
            existingOrder.setOrderStatus(orderRequest.orderStatus());
            existingOrder.setNotes(orderRequest.notes());
            return orderRepository.save(existingOrder);
        }
        log.info("Order not found for id: {})", refId);
        return null;
    }

//    public long deleteOrder(String refId) {
//        return orderRepository.deleteByRefId(refId);
//    }
}

