package com.example.eurder.domain.order;

import com.example.eurder.domain.item.Item;

import java.time.LocalDate;

public class ItemGroup {

    private String itemId;
    private final int amount;
    private final LocalDate shippingDate;
    private final int totalPrice;


    public ItemGroup(Item item, int amount) {
        this.itemId = item.getId();
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item);
        this.totalPrice=item.getPrice()*amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private LocalDate calculateShippingDate(Item item) {
        if (item.getAmount()>0){
            return LocalDate.now().plusDays(1);
        }
        else{
            return LocalDate.now().plusDays(7);
        }
    }
}
