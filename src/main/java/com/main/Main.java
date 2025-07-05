package com.main;

import com.main.entity.*;
import com.main.service.CheckoutService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expiryDate = sdf.parse("2025-07-30");

        Product cheese = new ShippableProduct("Cheese", 100, 5, 0.2, expiryDate);
        Product tv = new ShippableProduct("TV", 1000, 1, 8.0, null);

        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();

        cartItems.add(new CartItem(cheese,2));
        cartItems.add(new CartItem(tv, 1));

        cart.setItems(cartItems);

        Customer customer = new Customer("Mario", 999500);

        String result = CheckoutService.checkout(cart, customer);
        System.out.println(result);

    }
}