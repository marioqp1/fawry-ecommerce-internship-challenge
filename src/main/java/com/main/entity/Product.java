package com.main.entity;

import java.util.Date;

public class Product {
    protected String name;
    protected float price;
    protected int quantity;
    protected Date expiryDate;

    public Product(String name, float price, int quantity, Date expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean hasEnoughStock(int requiredQuantity) {
        return requiredQuantity <= quantity;
    }

    public boolean checkExpiryDate() {
        //check if the product has expiryDate that is not expired
        return expiryDate == null || !expiryDate.before(new Date());
    }
}
