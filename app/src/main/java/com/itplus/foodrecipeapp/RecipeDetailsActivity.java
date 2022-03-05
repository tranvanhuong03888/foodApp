package com.itplus.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itplus.foodrecipeapp.Adapter.IngredientsAdapter;
import com.itplus.foodrecipeapp.Adapter.InstructionsAdapter;
import com.itplus.foodrecipeapp.Adapter.SimilarRecipeAdapetr;
import com.itplus.foodrecipeapp.Listeners.InstructionsListener;
import com.itplus.foodrecipeapp.Listeners.RecipeClickListener;
import com.itplus.foodrecipeapp.Listeners.RecipeDetailsListener;
import com.itplus.foodrecipeapp.Listeners.SimilarRecipesListener;
import com.itplus.foodrecipeapp.Models.InstructionsResponse;
import com.itplus.foodrecipeapp.Models.RecipeDetailsResponse;
import com.itplus.foodrecipeapp.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    TextView txtvView_meal_name,txtvView_meal_source,txtvView_meal_summary;
    ImageView imgView_meal_image;
    RecyclerView rcvMeal_ingredients,rcvMeal_similar,rcvMeal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter adapter;
    SimilarRecipeAdapetr similarRecipeAdapetr;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        mapping();
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipes(similarRecipesListener,id);
        manager.getInstructions(instructionsListener,id);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            txtvView_meal_name.setText(response.title);
            txtvView_meal_source.setText(response.sourceName);
            txtvView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imgView_meal_image);

            rcvMeal_ingredients.setHasFixedSize(true);
            rcvMeal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL,false));
            adapter = new IngredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            rcvMeal_ingredients.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> responses, String message) {
            rcvMeal_similar.setHasFixedSize(true);
            rcvMeal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapetr = new SimilarRecipeAdapetr(RecipeDetailsActivity.this,responses,recipeClickListener);
            rcvMeal_similar.setAdapter(similarRecipeAdapetr);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this,RecipeDetailsActivity.class)
            .putExtra("id",id));
        }
    };

    private void mapping() {
        txtvView_meal_name = findViewById(R.id.txtvView_meal_name);
        txtvView_meal_source = findViewById(R.id.txtvView_meal_source);
        txtvView_meal_summary = findViewById(R.id.txtvView_meal_summary);
        imgView_meal_image = findViewById(R.id.imgView_meal_image);
        rcvMeal_ingredients = findViewById(R.id.rcvMeal_ingredients);
        rcvMeal_similar = findViewById(R.id.rcvMeal_similar);
        rcvMeal_instructions = findViewById(R.id.rcvMeal_instructions);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loding Details...");
        dialog.show();
    }

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            rcvMeal_instructions.setHasFixedSize(true);
            rcvMeal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this,response);
            rcvMeal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };

}