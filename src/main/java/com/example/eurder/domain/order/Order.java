package com.example.eurder.domain.order;

import com.example.eurder.domain.customer.Customer;

import java.util.UUID;

public class Order {

    private final String orderId;
    private final ItemGroup[] itemGroups;
    private final Customer customer;
    private final int totalPrice;

    public Order(ItemGroup[] itemGroups, Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice = calculateTotalPrice();
    }

    public String getOrderId() {
        return orderId;
    }

    public ItemGroup[] getItemGroups() {
        return itemGroups;
    }

    public Customer getCustomer() {
        return customer;
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (ItemGroup itemGroup : itemGroups) {
            totalPrice += itemGroup.getTotalPrice();
        }
        return totalPrice;
    }


}
