package com.main.entity;

import java.util.Date;

public class ShippableProduct extends Product implements ShippableInterface {
    private final double weight;

    public ShippableProduct(String name, float price, int quantity,
                            double weight, Date expiryDate) {
        super(name,price,quantity,expiryDate);
        this.weight = weight;
    }


    @Override
    public double getWeight() {
        return this.weight;
    }
}

