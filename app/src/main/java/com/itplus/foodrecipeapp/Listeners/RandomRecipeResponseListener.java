package com.itplus.foodrecipeapp.Listeners;

import com.itplus.foodrecipeapp.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {

    void didFetch(RandomRecipeApiResponse response,String message);
    void didError(String massage);

}
