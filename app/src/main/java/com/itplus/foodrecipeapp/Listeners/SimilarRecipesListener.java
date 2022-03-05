package com.itplus.foodrecipeapp.Listeners;

import com.itplus.foodrecipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {

    void didFetch(List<SimilarRecipeResponse> responses,String message);

    void didError(String message);

}
