package com.main.service;

import com.main.entity.*;

import java.util.ArrayList;
import java.util.List;

import static com.main.service.ShippingService.shippingItems;

public class CheckoutService {

    public static String checkout(Cart cart, Customer customer) {
        //check products stock & expiry date if the product has
        if (cart == null || cart.getItems().isEmpty()) {
            return "Cart is empty";
        }
        boolean needShipping = false;

        float shippingFees = 0;
        float subTotal = 0;
        //calculating subTotal + checking items expire date if it has and the stock
        for (CartItem item : cart.getItems()) {
            if (!item.getProduct().hasEnoughStock(item.getQuantity())) {
                return "No enough stock for: " + item.getProduct().getName();
            }
            if (!item.getProduct().checkExpiryDate()) {
                return "product is expired: " + item.getProduct().getName();
            }
            subTotal += item.getProduct().getPrice() * item.getQuantity();
            if (item.getProduct() instanceof ShippableInterface) {
                needShipping = true;
            }
        }
        if (needShipping) {
            shippingFees = 30;
        }

        //check customer balance
        float total = subTotal + shippingFees;
        if (total > customer.getBalance()) {
            return "Customer balance exceeded";
        }
        customer.setBalance(customer.getBalance() - total);

        return checkoutMessage(cart.getItems(), subTotal, shippingFees, total);
    }

    private static String checkoutMessage(List<CartItem> cartItemList, float subTotal, float fees, float total) {

        StringBuilder shipmentNotice = new StringBuilder("** Shipment notice **\n");
        StringBuilder checkoutReceipt = new StringBuilder("** Checkout receipt **\n");

        double weightTotal = 0;
        boolean shippedItem = false;
        //list of the items need to be shipped.
        List<ShippableInterface> shippableProducts = new ArrayList<>();

        for (CartItem item : cartItemList) {
            Product product = item.getProduct();

            if (product instanceof ShippableInterface shippable) {
                shippedItem = true;
                weightTotal += shippable.getWeight() * item.getQuantity();
                double totalItemWeight = shippable.getWeight() * item.getQuantity();
                shipmentNotice.append(item.getQuantity()).append("X ").append(shippable.getName()).append("       ").append(totalItemWeight).append("kg\n");

                // adding to the list to send to the shipping service
                shippableProducts.add(shippable);
            }

            float totalItemPrice = product.getPrice() * item.getQuantity();
            checkoutReceipt.append(item.getQuantity()).append("X ").append(product.getName()).append("       ").append(totalItemPrice).append("\n");
        }
        if (!shippableProducts.isEmpty()) {
            shippingItems(shippableProducts);
        }
        shipmentNotice.append("Total package weight: ").append(weightTotal).append("kg\n");
        checkoutReceipt.append("\n----------------------\n");
        checkoutReceipt.append("Subtotal:       ").append(subTotal).append("\n");
        if (fees > 0) {
            checkoutReceipt.append("Shipping:       ").append(fees).append("\n");
        }
        checkoutReceipt.append("Amount:       ").append(total).append("\n");

        if (shippedItem) {
            return (shipmentNotice.append(checkoutReceipt)).toString();
        }
        return checkoutReceipt.toString();
    }
}
