package com.example.foodorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CuisineListFragment extends Fragment {

    public CuisineListFragment() {
        // Required empty public constructor
    }

    /**
     * @param cuisine The cuisine for this cuisine list fragment.
     * @return A new instance of fragment CuisineListFragment.
     */
    public static CuisineListFragment newInstance(Cuisine cuisine) {
        CuisineListFragment fragment = new CuisineListFragment();
        Bundle args = new Bundle();
        args.putString(CUISINE, cuisine.name());  // Pass name of cuisine (a String) as parameter into fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String cuisineName = getArguments().getString(CUISINE);
            Cuisine cuisine = Cuisine.valueOf(cuisineName);
            ArrayList<Dish> dishes = Menu.getInstance().dishesByCuisine().get(cuisineName);
            this.cuisine = cuisine;
            this.dishes = dishes;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cuisine_list, container, false);
        setupCuisineName();
        setupRecyclerView();

        return rootView;
    }

    private static final String CUISINE = "cuisine";
    private Cuisine cuisine;
    private ArrayList<Dish> dishes;
    private View rootView;
    private RecyclerView recyclerView;

    private void setupCuisineName() {
        if (cuisine != null) {
            TextView cuisineNameTextView = rootView.findViewById(R.id.text_cuisine_name);
            cuisineNameTextView.setText(cuisine.toString());
        }
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        DishAdapter dishAdapter = new DishAdapter(getContext(), dishes);
        recyclerView.setAdapter(dishAdapter);
    }

    private void userTappedOnDishAtPosition(int position) {
        Menu.getInstance().selectedDishCuisine = cuisine;
        Menu.getInstance().selectedDishPosition = position;

        Dish dish = Menu.getInstance().dishesByCuisine().get(cuisine.name()).get(position);
        System.out.println("User tapped on dish " + dish.name);

        Intent intent = new Intent(getContext(), DishActivity.class);
        getActivity().startActivity(intent);
    }

    private class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

        public DishAdapter(@NonNull Context context, @NonNull ArrayList<Dish> dishes) {
            this.context = context;
            this.dishes = dishes;
        }

        @NonNull
        @Override
        public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.item_dish, parent, false);
            DishViewHolder viewHolder = new DishViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DishViewHolder holder, final int position) {
            Dish dish = dishes.get(position);
            holder.nameTextView.setText(dish.name);
            Context context = getContext();
            int id = context.getResources().getIdentifier(dish.imageSource, "drawable", context.getPackageName());
            holder.imageView.setImageResource(id);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //userTappedOnDishAtPosition(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dishes.size();
        }

        private final Context context;
        private final ArrayList<Dish> dishes;

        private class DishViewHolder extends RecyclerView.ViewHolder {

            public View itemView;
            public ImageView imageView;
            public TextView nameTextView;

            public DishViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                imageView = itemView.findViewById(R.id.dish_image);
                nameTextView = itemView.findViewById(R.id.text_cuisine_name);
            }
            }
        }
    }
