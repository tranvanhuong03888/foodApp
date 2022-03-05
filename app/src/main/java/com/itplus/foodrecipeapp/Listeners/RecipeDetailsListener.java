package com.itplus.foodrecipeapp.Listeners;

import com.itplus.foodrecipeapp.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response,String message);
    void didError(String message);
}
