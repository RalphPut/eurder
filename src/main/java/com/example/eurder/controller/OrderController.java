package com.example.eurder.controller;

import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.OrderDTO;
import com.example.eurder.service.OrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private  final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrdersFromCustomer(@RequestHeader(value = "customerId", required = false) String customerId){
        return this.orderService.findallOrdersOfCustomer(customerId);
    }

    @GetMapping( path ="/{orderId}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO findById(@PathVariable String orderId){
        return this.orderService.findById(orderId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO orderDTO, @RequestHeader(value = "customerId",required = false)String customerId){
        orderService.addOrder(customerId,orderDTO);
    }
}
