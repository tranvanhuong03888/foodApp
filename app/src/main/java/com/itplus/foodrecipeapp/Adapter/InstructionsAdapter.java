package com.itplus.foodrecipeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.foodrecipeapp.Models.InstructionsResponse;
import com.itplus.foodrecipeapp.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHodel>{

    Context context;
    List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHodel(LayoutInflater.from(context).inflate(R.layout.list_instructions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHodel holder, int position) {

        holder.txtvInstruction_name.setText(list.get(position).name);
        holder.rcvInstruction_steps.setHasFixedSize(true);
        holder.rcvInstruction_steps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context,list.get(position).steps);
        holder.rcvInstruction_steps.setAdapter(stepAdapter);
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
}
class InstructionsViewHodel extends RecyclerView.ViewHolder{

    TextView txtvInstruction_name;
    RecyclerView rcvInstruction_steps;

    public InstructionsViewHodel(@NonNull View itemView) {
        super(itemView);
        txtvInstruction_name = itemView.findViewById(R.id.txtvInstruction_name);
        rcvInstruction_steps = itemView.findViewById(R.id.rcvInstruction_steps);
    }
}
