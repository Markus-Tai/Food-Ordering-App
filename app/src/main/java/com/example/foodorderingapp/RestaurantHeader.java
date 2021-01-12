package com.example.foodorderingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RestaurantHeader extends Fragment {
    public RestaurantHeader() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RestaurantHeader newInstance() {
        RestaurantHeader fragment = new RestaurantHeader();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupXML();
        return inflater.inflate(R.layout.fragment_restaurant_header, container, false);
    }
    private void setupXML() {

    }
}