package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.OrderRequest;
import com.groupp.crystalweb.entity.Order;
import com.groupp.crystalweb.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class OrderController {



    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    //    save user
    @PostMapping("/order")
    public Order saveOrder(@RequestBody OrderRequest orderRequest){
        return orderService.saveOrder(orderRequest);
    }

    //    get all users
    @GetMapping("/order")
    public List<Order> getOrder(){
        return orderService.getAllOrders();

    }

    //    get by id
    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable String refId){
        return orderService.getOrder(refId);
    }

    //    update by id
    @PutMapping("order/{refId}")
    public Order updateOrder(@PathVariable String refId, @RequestBody OrderRequest orderRequest){
        return orderService.update(refId, orderRequest); //?
    }

    //    delete by id
    @DeleteMapping("user/delete/{refId}")
    public String deleteOrder(@PathVariable String refId){
        long deleted = orderService.deleteOrder(refId);
        if(deleted != 0){
            return ("Order deleted successfully");
        }
        return "Order not found!";
    }

}

