package com.example.foodorderingapp;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class Restaurant {

    // Singleton
    public static Restaurant getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Restaurant();
        }
        return sharedInstance;
    }
    private static Restaurant sharedInstance = null;
    private Restaurant() { setupProperties();}

    //public properties
    public String restaurantName;
    public String shortDesc;
    public String streetAddress;
    public String city;
    public String phoneNumber;
    public int imageResource;

    private Lorem lorem = LoremIpsum.getInstance();
    //private method
    private void setupProperties() {
        restaurantName = lorem.getTitle(1,2);
        shortDesc = lorem.getWords(3, 7);
        streetAddress = randomStreetAddress();
        phoneNumber = lorem.getPhone();
        imageResource = R.drawable.restaurant;
        // TODO add some images maybe
        // imageResource = R.drawable.restaurant;

    }

    private String randomStreetAddress() {
        int min = 100;
        int max = 9999;
        int randomNumber = (int)(Math.random() * (max - min +1) + min);
        String randomStreetAddress = randomNumber + " " + lorem.getCity() + " " + "St.";
        return randomStreetAddress;
    }
}
