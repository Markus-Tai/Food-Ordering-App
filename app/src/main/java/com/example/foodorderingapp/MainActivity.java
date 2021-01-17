package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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



        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HashMap<String,ArrayList<Dish>> dishesByCuisine = Menu.getInstance().dishesByCuisine();
        for (Map.Entry<String, ArrayList<Dish>> entry : dishesByCuisine.entrySet()) {
            Cuisine cuisine = Cuisine.valueOf((entry.getKey()));
            Fragment fragment = CuisineListFragment.newInstance(cuisine);
            fragmentTransaction.add(linearLayout.getId(), fragment, null);
        }
        fragmentTransaction.commit();

    }
}