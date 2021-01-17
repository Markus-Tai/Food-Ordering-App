package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resuming ActivityMain");
        invalidateOptionsMenu();  // Refresh action bar button (cart button)
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate our action bar layout (action_bar_menu.xml)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        // Check to see if there are items in the cart
        Boolean areItemsInCart = Cart.getInstance().numberOfItems() > 0;

        // Set the cart icon to be enabled/disabled depending on whether there are items in the cart
        menu.findItem(R.id.action_cart).setEnabled(areItemsInCart);

        // Return our inflated menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                System.out.println("Selected cart");
                // Navigate to cart screen
                navigateToCart();
                break;
            default:
                break;
        }
        return true;
    }

    // Private methods
    private void navigateToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }





}