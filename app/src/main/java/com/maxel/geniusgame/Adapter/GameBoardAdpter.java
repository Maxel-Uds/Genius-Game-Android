package com.maxel.geniusgame.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maxel.geniusgame.Logic.Action;
import com.maxel.geniusgame.R;

import java.util.Arrays;
import java.util.List;

public class GameBoardAdpter extends RecyclerView.Adapter<GameBoardAdpter.GameBoardHolder> {

    private final LayoutInflater inflater;
    private final List<Integer> sounds = Arrays.asList(R.raw.beep1, R.raw.beep2, R.raw.beep3, R.raw.beep4);
    private final List<String> colors = Arrays.asList("#FF0000", "#008000", "#0000FF", "#87871A");

    public GameBoardAdpter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GameBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.activity_button, parent, false);
        return new GameBoardHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull GameBoardHolder holder, int position) {
        Button button = holder.itemView.findViewById(R.id.button);
        button.getBackground().setTint(Color.parseColor(colors.get(position)));
        button.setOnClickListener(view -> {
            Action.checkPlay(button);
            Action.playSound(button.getContext(), sounds.get(position));
        });
        Action.getGameButtons().add(button);
        Action.getSoundByButton().put(button, sounds.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class GameBoardHolder extends RecyclerView.ViewHolder{
        public GameBoardHolder(View view){
            super(view);
        }
    }
}
