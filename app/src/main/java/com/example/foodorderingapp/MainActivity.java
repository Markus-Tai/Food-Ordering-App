package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dish dishOne = new Dish("");

        System.out.println("Dish One: "+dishOne);

        Dish dishTwo = new Dish("");
        System.out.println("Dish Two: " + dishTwo);

        Dish dishThree = dishOne;
        System.out.println("Dish Three: " + dishThree);

        System.out.println("dishOne == dishTwo" + dishOne.equals(dishTwo));
        System.out.println("dishOne == dishThree" + dishOne.equals(dishThree));
        System.out.println("dishTwo == dishThree" + dishOne.equals(dishThree));


        Menu menu = new Menu();
        HashMap<String, ArrayList<Dish>> dishesByCuisine= menu.dishesByCuisine();
    }
}