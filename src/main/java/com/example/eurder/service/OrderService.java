package com.example.eurder.service;

import com.example.eurder.domain.customer.UserRole;
import com.example.eurder.domain.order.OrderDTO;
import com.example.eurder.mapper.OrderMapper;
import com.example.eurder.repository.OrderRepository;
import com.example.eurder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public OrderDTO findById(String orderId) {
        try {
            return orderMapper.toDto(orderRepository.findById(orderId));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<OrderDTO> findallOrdersOfCustomer(String customerId) {
        return orderMapper.toDto(orderRepository.findAllOrdersfromCustomer(customerId));
    }

    public void addOrder(String customerId, OrderDTO orderDTO) {
        try {
            if (userIsCustomer(customerId)) {
                orderRepository.createOrder(orderMapper.toOrder(orderDTO), customerId);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item was not added!");
        }
    }

    public boolean userIsCustomer(String userId) {

        try {
            return userRepository.findByid(userId).getUserRole() == UserRole.CUSTOMER;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
