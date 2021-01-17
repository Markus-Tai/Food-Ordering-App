
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
    //Singleton
    public static Menu getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Menu();
        }
        return sharedInstance;
    }
    private static Menu sharedInstance = null;
    private Menu() {}

    public Cuisine selectedDishCuisine;
    public Integer selectedDishPosition;

    //"CuisineName" : { Dish }
    private HashMap<String,ArrayList<Dish>>menu=null;
    private void populateMenu() {
        menu = new HashMap<>(); // this is a shorthand, TODO Check for more shorthands, theres probably a list in the documentation or something

        // Iterate through every possible Cuisine type
        for (Cuisine cuisine : Cuisine.values()) {
            ArrayList<Dish> dishesList = new ArrayList<Dish>();

            // Insert however many dishes for each cuisine
            for (int i=0;i<6;i++) {
                String imgSourceName = cuisine.name().toLowerCase()+ "0" + (i+1);
                Dish dish = new Dish(imgSourceName);
                dishesList.add(dish);
            }

            //Here, dishesList will have however many dishes added to it
            // Sp add it to the menu dictionary.
            menu.put(cuisine.name(),dishesList);
        }
        System.out.println("Menu: "+menu); // LLLLLLL
    }
}
//are there conventions in ordering properties, methods, etc?
