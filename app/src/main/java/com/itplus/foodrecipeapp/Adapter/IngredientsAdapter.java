package com.itplus.foodrecipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.foodrecipeapp.Models.ExtendedIngredient;
import com.itplus.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHodelr>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHodelr(LayoutInflater.from(context).
                inflate(R.layout.list_meal_ingredients,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHodelr holder, int position) {
        holder.txtvIngredients_name.setText(list.get(position).name);
        holder.txtvIngredients_name.setSelected(true);
        holder.txtvIngredients_quantity.setText(list.get(position).original);
        holder.txtvIngredients_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+
                list.get(position).image).into(holder.imgIngredients_name);
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
}
class IngredientsViewHodelr extends RecyclerView.ViewHolder{
    TextView txtvIngredients_quantity,txtvIngredients_name;
    ImageView imgIngredients_name;
    public IngredientsViewHodelr(@NonNull View itemView) {
        super(itemView);
        txtvIngredients_quantity = itemView.findViewById(R.id.txtvIngredients_quantity);
        txtvIngredients_name = itemView.findViewById(R.id.txtvIngredients_name);
        imgIngredients_name = itemView.findViewById(R.id.imgIngredients_name);
    }
}
