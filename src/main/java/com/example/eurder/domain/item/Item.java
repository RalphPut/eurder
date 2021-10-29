package com.example.eurder.domain.item;

import java.util.UUID;

public class Item implements Comparable<Item> {

    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final int amount;
    private final StockLevel stockLevel;


    public Item(String name, String description, int price, int amount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.stockLevel = updateStockLevel();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public StockLevel getStockLevel() {
        return stockLevel;
    }

    private StockLevel updateStockLevel() {
        if (this.amount < 5) {
            return StockLevel.STOCK_LOW;
        }
        if (this.amount < 10) {
            return StockLevel.STOCK_MEDIUM;
        } else {
            return StockLevel.STOCK_HIGH;
        }
    }

    @Override
    public int compareTo(Item o) {
        if (this.stockLevel == StockLevel.STOCK_LOW) {
            return -2;
        }
        if (this.stockLevel == StockLevel.STOCK_MEDIUM) {
            return -1;
        } else
            return 1;
    }

}

