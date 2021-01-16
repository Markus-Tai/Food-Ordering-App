package com.example.foodorderingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PickerNumberStepper extends LinearLayout implements View.OnClickListener {
    Integer minimumValue = 0;
    Integer maximumValue = 10;
    Integer currentValue = 0;
    public PickerNumberStepper(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.picker_number_stepper, this, true);
        numberEditText = findViewById(R.id.numberEditText);
        ImageButton subtractButton = findViewById(R.id.subtractImageButton);
        subtractButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue - 1 >= minimumValue) {currentValue--; }
                refreshCurrentValue();
                performClick();
            }
        });

        ImageButton addButton = findViewById(R.id.addImageButton);
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentValue + 1 <= maximumValue) { currentValue++; }
                refreshCurrentValue();
                performClick();
            }
        });

        refreshCurrentValue();
    }
    @Override
    public void onClick(View v) {}
    private EditText numberEditText;
    private void refreshCurrentValue() {
        numberEditText.setText(currentValue.toString());
    }
}
