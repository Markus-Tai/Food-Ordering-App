package com.example.foodorderingapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    public HashMap<String,ArrayList<Dish>> dishesByCuisine() {
        if (menu==null) {
            populateMenu();
        }
        return menu;
    }

    //"CuisineName" : { Dish }
    private HashMap<String,ArrayList<Dish>>menu=null;
    private void populateMenu() {
        menu = new HashMap<>(); // this is a shorthand, TODO Check for more shorthands, theres probably a list in the documentation or something


        for (Cuisine cuisine : Cuisine.values()) {
            ArrayList<Dish> dishesList = new ArrayList<Dish>();

            for (int i=0;i<6;i++) { // TODO instead of a 6, maybe we can grab another random number for this too..
                Dish dish = new Dish("");
                dishesList.add(dish);
            }
            menu.put(cuisine.toString(),dishesList);
        }
        System.out.println("Menu: "+menu); // LLLLLLL
    }
}
//are there conventions in ordering properties, methods, etc?
