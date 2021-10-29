package com.example.eurder.repository;

import com.example.eurder.domain.item.Item;
import com.example.eurder.domain.order.ItemGroup;
import com.example.eurder.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final Map<String, Order> orderDatabase;
    public final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    public OrderRepository() {
        this.orderDatabase= new ConcurrentHashMap<>();
    }

    public boolean existsInDatabase(String uuid) {
        return orderDatabase.containsKey(uuid);
    }

    public Order findById(String orderId){
        var foundOrder = this.orderDatabase.get(orderId);
        if (foundOrder==null){
            logger.warn("this order does not exist!");
            throw new IllegalArgumentException(String.format("Order with id %s does not exist!",orderId));
        }
        return foundOrder;
    }

    public List<Order> findAllOrdersfromCustomer(String customerId){
        return orderDatabase.values().stream()
                .filter(order -> Objects.equals(order.getCustomerId(), customerId))
                .collect(Collectors.toList());
    }

    public void createOrder(Order order,String customerId){
        if (existsInDatabase(order.getOrderId())){
            throw new IllegalArgumentException();
        }
        orderDatabase.put(order.getOrderId(),order);
    }


}
