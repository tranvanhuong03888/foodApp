package com.itplus.foodrecipeapp.Adapter;

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
import com.itplus.foodrecipeapp.Models.SimilarRecipeResponse;
import com.itplus.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapetr extends RecyclerView.Adapter<SimilarRecipeViewHolder>{

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapetr(Context context, List<SimilarRecipeResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.txtvSimilar_title.setText(list.get(position).title);
        holder.txtvSimilar_title.setSelected(true);
        holder.txtvSimilar_serving.setText(list.get(position).servings + " Persions");
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).id + "-556x370." + list.get(position).imageType)
                .into(holder.imgSimilar);
        holder.similar_recipe_holder.setOnClickListener(view ->
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
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{

    TextView txtvSimilar_title,txtvSimilar_serving;
    ImageView imgSimilar;
    CardView similar_recipe_holder;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        txtvSimilar_title = itemView.findViewById(R.id.txtvSimilar_title);
        txtvSimilar_serving = itemView.findViewById(R.id.txtvSimilar_serving);
        imgSimilar = itemView.findViewById(R.id.imgSimilar);
        similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
    }
}
