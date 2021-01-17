package com.example.foodorderingapp;

import java.util.ArrayList;

public class Cart {
    //singleton
    public static Cart getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Cart();
        }
        return sharedInstance;
    }
    private static Cart sharedInstance = null;
    private Cart() {}


    //Public properties
    public Integer numberOfItems() {
        Integer numberOfTimes = 0;
        for (CartItem cartItem : cartItem) {
            numberOfTimes += cartItem.getQuantity();
        }
        return numberOfTimes;
    }

    public Integer totalPriceInCents() {
        Integer totalPrice = 0;
        for (CartItem cartItem : cartItem) {
            totalPrice += cartItem.subtotalPriceInCents();
        }
        return totalPrice;
    }

    public void add(Dish dish, Integer quantity) {
        System.out.println("Adding " + quantity + " items of " + dish.name);

        // Check if dish is already in cart
        CartItem foundCartItem = null;
        for (CartItem cartItem : cartItem) {
            if (cartItem.dish.equals(dish)) {
                foundCartItem = cartItem;
                break;
            }
        }

        if (foundCartItem != null) {
            // Found matching dish
            foundCartItem.quantity += quantity;
        }
        else {
            // No matching dish found
            CartItem cartItem = new CartItem(dish, quantity);
            cartItem.add(cartItem);
        }
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItem;
    }

    public void clear() {
        cartItem = new ArrayList<>();
    }
    //Private properties
    private ArrayList<CartItem> cartItem = new ArrayList<>();

    //nested class
    public class CartItem {

        // Public methods
        public Integer getQuantity() {
            return quantity;
        }

        public Integer itemPriceInCents() {
            return dish == null ? 0 : dish.priceInCents;
        }

        public String dishName() {
            return dish == null ? "" : dish.name;
        }

        public Integer subtotalPriceInCents() {
            return quantity * itemPriceInCents();
        }


        public CartItem(Dish dish, Integer quantity) {
            this.dish = dish;
            this.quantity = quantity;
        }

        // Private properties
        private Dish dish;
        private Integer quantity;

    }

}
