package com.example.foodorderingapp;

import androidx.annotation.NonNull;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class Dish {
    //public properties
    public String name;
    public String description;
    public Integer priceInCents;
    public String imageSource;

    // private properties
    private static Lorem lorem = LoremIspum.getInstance();
    private int maxPriceInCents = 1999;
    private int minPriceInCents = 999;
    //constructor
    public Dish(String imageSource) {
        this.imageSource = imageSource;

        populateProperties();
    }


    //public methods

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceInCents=" + priceInCents +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }
    public Boolean equals(Dish dish) {
        return (name.equals(dish.name) &&
                description.equals(dish.description) &&
                imageSource.equals(dish.imageSource) &&
                priceInCents == dish.priceInCents);
    }

    //private methods
    private void populateProperties() {
        //random self generating other properties...
        String name = lorem.getTitle(1,4);
        String description = lorem.getParagraphs(2,4);
        //normally you wont populate it like this
        //TODO maybe i can make random uber names lol that would be cool
        // using lorem.getName(), country, etc
        this.name = name;
        this.description = description;
        this.priceInCents = (int)(Math.random() * (maxPriceInCents - minPriceInCents + 1) + minPriceInCents);
    }

