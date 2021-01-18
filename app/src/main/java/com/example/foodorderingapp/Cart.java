package com.example.foodorderingapp;

import java.util.ArrayList;

public class Cart {
    public static Cart getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Cart();
        }
        return sharedInstance;
    }
    private static Cart sharedInstance = null;
    private Cart() {}
    public Integer numberOfItems() {
        Integer numberOfItems = 0;
        for (CartItem cartItem : cartItems) {
            numberOfItems += cartItem.getQuantity();
        }
        return numberOfItems;
    }
    public Integer totalPriceInCents() {
        Integer totalPriceInCents = 0;
        for (CartItem cartItem : cartItems) {
            totalPriceInCents += cartItem.subtotalPriceInCents();
        }
        return totalPriceInCents;
    }
    public void add(Dish dish, Integer quantity) {
        System.out.println("Adding " + quantity + " items of " + dish.name);
        CartItem foundCartItem = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.dish.equals(dish)) {
                foundCartItem = cartItem;
                break;
            }
        }
        if (foundCartItem != null) {
            foundCartItem.quantity += quantity;
        }
        else {
            CartItem cartItem = new CartItem(dish, quantity);

            getCartItems().add(cartItem);

            cartItems.add(cartItem);

        }
    }
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }
    public void clear() {
        cartItems = new ArrayList<>();
    }
    private ArrayList<CartItem> cartItems = new ArrayList<>();
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

        // Constructor
        public CartItem(Dish dish, Integer quantity) {
            this.dish = dish;
            this.quantity = quantity;
        }

        // Private properties
        private Dish dish;
        private Integer quantity;
    }
}



