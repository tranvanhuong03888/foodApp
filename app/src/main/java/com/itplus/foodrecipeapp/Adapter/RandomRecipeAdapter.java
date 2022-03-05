package com.itplus.foodrecipeapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.foodrecipeapp.Listeners.RecipeClickListener;
import com.itplus.foodrecipeapp.Models.Recipe;
import com.itplus.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list,RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).
                inflate(R.layout.list_random_recipe,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.txtvView_title.setText(list.get(position).title);
        holder.txtvView_title.setSelected(true);
        holder.txtvView_likes.setText(list.get(position).aggregateLikes + " Likes");
        holder.txtvView_servings.setText(list.get(position).servings + " Servings");
        holder.txtvView_time.setText(list.get(position).readyInMinutes + " Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imgView_food);
        holder.random_list_container.setOnClickListener(view ->
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id)));
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{

    CardView random_list_container;
    TextView txtvView_title,txtvView_servings,txtvView_likes,txtvView_time;
    ImageView imgView_food;

    public RandomRecipeViewHolder(View itemView){
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        txtvView_title = itemView.findViewById(R.id.txtvTitle);
        txtvView_servings = itemView.findViewById(R.id.txtvView_servings);
        txtvView_likes = itemView.findViewById(R.id.txtvView_likes);
        txtvView_time = itemView.findViewById(R.id.txtvView_time);
        imgView_food = itemView.findViewById(R.id.imgView_food);

    }

}
