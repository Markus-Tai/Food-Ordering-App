package com.example.foodorderingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        System.out.println("Cuisine: " + Menu.getInstance().selectedDishCuisine + ", pos: " + Menu.getInstance().selectedDishPosition);
        final HashMap<String, ArrayList<Dish>> dishesByCuisine = Menu.getInstance().dishesByCuisine();
        final ArrayList<Dish> dishesForCuisine = dishesByCuisine.get(Menu.getInstance().selectedDishCuisine.name());
        selectedDish = dishesForCuisine.get(Menu.getInstance().selectedDishPosition);

        getSupportActionBar().setTitle(selectedDish.name);

        final ImageView dishImageView = findViewById(R.id.dishImageView);
        final int imageId = getResources().getIdentifier(selectedDish.imageSource, "drawable", getPackageName());
        dishImageView.setImageResource(imageId);

        final TextView dishNameTextView = findViewById(R.id.dishNameTextView);
        dishNameTextView.setText(selectedDish.name);

        final TextView itemPriceTextView = findViewById(R.id.itemPriceTextView);
        itemPriceTextView.setText(String.format("$%.2f", selectedDish.priceInCents / 100.0));

        final PickerNumberStepper numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedQuantity = numberPicker.currentValue;
                updateSubtotal();
            }
        });

        final ImageButton cartButton = findViewById(R.id.addToCartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
        final TextView dishDescription = findViewById(R.id.dishDescriptionTextView);
        dishDescription.setText(selectedDish.description);
        updateSubtotal();
    }

    private Dish selectedDish;
    private Integer selectedQuantity = 0;

    // Private methods
    private void updateSubtotal() {
        final TextView subtotalTextView = findViewById(R.id.subtotalTextView);
        subtotalTextView.setText(String.format("$%.2f", selectedQuantity * selectedDish.priceInCents / 100.0));
    }

    private void addToCart() {
        if (selectedQuantity == 0) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.cannot_add_to_cart)
                    .setMessage(R.string.choose_one_or_more_items)
                    .setPositiveButton(R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            Cart.getInstance().add(selectedDish, selectedQuantity);
            finish();
        }
    }
}
