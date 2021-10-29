package com.example.eurder.domain.order;

import com.example.eurder.domain.customer.Customer;

public class OrderDTO {

    private final String orderId;
    private final ItemGroup [] itemGroups;
    private final Customer customer;
    private int totalPrice;

    public OrderDTO(String orderId, ItemGroup[] itemGroups, Customer customer) {
        this.orderId = orderId;
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice=calculateTotalPrice();
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

    public int getTotalPrice() {
        return totalPrice;
    }

    private int calculateTotalPrice(){
        int totalPrice=0;
        for (ItemGroup itemGroup : itemGroups) {
            totalPrice+=itemGroup.getTotalPrice();
        }
        return totalPrice;
    }
}
