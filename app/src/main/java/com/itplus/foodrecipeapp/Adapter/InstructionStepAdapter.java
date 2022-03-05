package com.itplus.foodrecipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.foodrecipeapp.Models.Step;
import com.itplus.foodrecipeapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{

    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.txtvInstructions_step_number.setText(String.valueOf(list.get(position).number));
        holder.txtvInstructions_step_title.setText(list.get(position).step);

        holder.rcvInstructions_ingredients.setHasFixedSize(true);
        holder.rcvInstructions_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsIngredientsAdapter instructionsIngredientsAdapter = new InstructionsIngredientsAdapter(context,list.get(position).ingredients);
        holder.rcvInstructions_ingredients.setAdapter(instructionsIngredientsAdapter);

        holder.rcvInstructions_equipments.setHasFixedSize(true);
        holder.rcvInstructions_equipments.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        InstructionsEqipmentsAdapter instructionsEqipmentsAdapter = new InstructionsEqipmentsAdapter(context,list.get(position).equipment);
        holder.rcvInstructions_equipments.setAdapter(instructionsEqipmentsAdapter);

    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
}
class InstructionStepViewHolder extends RecyclerView.ViewHolder{

    TextView txtvInstructions_step_number,txtvInstructions_step_title;
    RecyclerView rcvInstructions_ingredients,rcvInstructions_equipments;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        txtvInstructions_step_number = itemView.findViewById(R.id.txtvInstructions_step_number);
        txtvInstructions_step_title = itemView.findViewById(R.id.txtvInstructions_step_title);
        rcvInstructions_ingredients = itemView.findViewById(R.id.rcvInstructions_ingredients);
        rcvInstructions_equipments = itemView.findViewById(R.id.rcvInstructions_equipments);
    }
}
