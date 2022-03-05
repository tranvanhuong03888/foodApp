package com.itplus.foodrecipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.foodrecipeapp.Models.Equipment;
import com.itplus.foodrecipeapp.Models.Ingredient;
import com.itplus.foodrecipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsEqipmentsAdapter extends RecyclerView.Adapter<InstructionEquipmentdViewHolder>{

    Context context;
    List<Equipment> list;

    public InstructionsEqipmentsAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentdViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentdViewHolder holder, int position) {

        holder.txtvInstructions_step_item.setText(list.get(position).name);
        holder.txtvInstructions_step_item.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" +
                list.get(position).image).into(holder.imgInstructions_step_item);
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
}

class InstructionEquipmentdViewHolder extends RecyclerView.ViewHolder{

    ImageView imgInstructions_step_item;
    TextView txtvInstructions_step_item;

    public InstructionEquipmentdViewHolder(@NonNull View itemView) {
        super(itemView);
        imgInstructions_step_item = itemView.findViewById(R.id.imgInstructions_step_item);
        txtvInstructions_step_item = itemView.findViewById(R.id.txtvInstructions_step_item);
    }
}
