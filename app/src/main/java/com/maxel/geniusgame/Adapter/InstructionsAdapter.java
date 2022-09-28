package com.maxel.geniusgame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maxel.geniusgame.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.InstructionsHolder> {

    private final List<String> instructions;
    private final LayoutInflater inflater;

    public InstructionsAdapter(List<String> instructions, Context context) {
        this.instructions = instructions;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public InstructionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.activity_instructions, parent, false);
        return new InstructionsAdapter.InstructionsHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsHolder holder, int position) {
        TextView text = holder.itemView.findViewById(R.id.instruction);
        text.setText(instructions.get(position));
    }

    @Override
    public int getItemCount() {
        return instructions.size();
    }

    public static class InstructionsHolder extends RecyclerView.ViewHolder{
        public InstructionsHolder(View view){
            super(view);
        }
    }
}
