package com.example.eurder.mapper;

import com.example.eurder.domain.order.Order;
import com.example.eurder.domain.order.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDto(Order order){
        return new OrderDTO(order.getOrderId(), order.getItemGroups(), order.getCustomerId());
    }

    public List<OrderDTO> toDto(List<Order> orderList){
        return orderList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Order toOrder(OrderDTO orderDTO){
        return new Order(orderDTO.getItemGroups(), orderDTO.getCustomerId());
    }
}
