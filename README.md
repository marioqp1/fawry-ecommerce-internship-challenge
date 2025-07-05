# Fawry Internship E-Commerce System

This is a Java console project made for the Fawry Full Stack Internship Challenge.

## Project Description

This system allows customers to:

- Add products to a shopping cart
- Checkout and pay for the products

The system supports:

- Products with name, price, and quantity
- Some products can expire (like Cheese)
- Some products need shipping (like TV) and have weight
- Products that do not expire or need shipping (like mobile cards)

## Features

- Customers can add items to the cart with a quantity that is not more than the stock
- At checkout:
  - It checks if products are expired or out of stock
  - It checks if the customer has enough balance
  - It calculates subtotal and shipping fee
  - It shows a receipt with all prices
  - If shipping is needed, it shows items to be shipped and their weight
  - It also sends the shipping items to the ShippingService

## Example Output
Shipping Service received items:  
Cheese     0.2kg
TV     8.0kg
** Shipment notice **
2X Cheese       0.4kg
1X TV       8.0kg
Total package weight: 8.4kg
** Checkout receipt **
2X Cheese       200.0
1X TV       1000.0

----------------------
Subtotal:       1200.0
Shipping:       30.0
Amount:       1230.0

## Folder Structure

- `entity/` has the classes like Product, Cart, Customer
- `service/` has the logic for checkout and shipping
- `Main.java` is used for testing the program

## How to Run

1. Clone the project
2. Open it in your IDE
3. Run the `Main` class

## Notes

- Shipping fee is fixed (30)
- Products are sent to the shipping service only if they are shippable
- Shipping service uses an interface with getName() and getWeight()

## Author

Made by Mario Sherif for the Fawry Internship Challenge
