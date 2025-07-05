package com.main.service;

import com.main.entity.ShippableInterface;

import java.util.List;

public class ShippingService {
    public static void shippingItems(List<ShippableInterface> items){
        System.out.println("Shipping Service received items:  ");
        for(ShippableInterface item : items){
            System.out.print(item.getName()+ "     " +item.getWeight()+"kg\n");
        }
    }
}
