package com.example.eurder.domain.order;

import com.example.eurder.domain.customer.Customer;
import com.example.eurder.repository.UserRepository;

public class OrderDTO {

    private final String orderId;
    private final ItemGroup [] itemGroups;
    private final String customerId;
    private final int totalPrice;


    public OrderDTO(String orderId, ItemGroup[] itemGroups, String customerId){
        this.orderId = orderId;
        this.itemGroups = itemGroups;
        this.customerId = customerId;
        this.totalPrice=calculateTotalPrice();
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
