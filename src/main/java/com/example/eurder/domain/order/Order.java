package com.example.eurder.domain.order;

import com.example.eurder.domain.customer.Customer;
import com.example.eurder.repository.UserRepository;
import org.apache.catalina.User;

import java.util.UUID;

public class Order {

    private final String orderId;
    private final ItemGroup[] itemGroups;
    private final String customerId;
    private final int totalPrice;

    public Order(ItemGroup[] itemGroups, String customerId) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroups = itemGroups;
        this.customerId = customerId;
        this.totalPrice = calculateTotalPrice();
    }

    public String getOrderId() {
        return orderId;
    }

    public ItemGroup[] getItemGroups() {
        return itemGroups;
    }

    public String getCustomerId() {
        return customerId;
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (ItemGroup itemGroup : itemGroups) {
            totalPrice += itemGroup.getTotalPrice();
        }
        return totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer(String customerId) {
        UserRepository userRepository = new UserRepository();
        return userRepository.findByid(customerId);
    }
}
