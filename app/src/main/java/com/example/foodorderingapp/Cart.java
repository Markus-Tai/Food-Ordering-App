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
    public Integer numberOfTimes() {
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
        //Check is dish already in our cart?
        CartItem existingCartItem = null;
        for (CartItem cartItem : cartItem) {
            if (cartItem.dish.equals(dish)) {
                existingCartItem=cartItem;
                break;
            }
        }

        // Did we find a matching cart item?
        if (existingCartItem != null) {
            // We found a matching dish in our cart
            existingCartItem.quantity += quantity;
        } else {
            // We don't have this dish in our cart yet
            CartItem newCartItem = new CartItem(dish, quantity);
            cartItem.add(newCartItem);
        }

    }
    //Private properties
    private ArrayList<CartItem> cartItem = new ArrayList<>();

    //nested class
    public class CartItem {
        public CartItem(Dish dish, Integer quantity) {
            this.dish = dish;
            this.quantity = quantity;
        }

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

       // Private properties
        private Dish dish;
        private Integer quantity;

    }

}
