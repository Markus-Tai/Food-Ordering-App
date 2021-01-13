package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dish dishOne = new Dish("");
<<<<<<< HEAD
        System.out.println("Dish One: "+dishOne);

        Dish dish1 = new Dish();

=======
        System.out.println("Dish One: " + dishOne);
>>>>>>> d7b028970c79b072b3923b97438d5cff57ee1637
    }
}