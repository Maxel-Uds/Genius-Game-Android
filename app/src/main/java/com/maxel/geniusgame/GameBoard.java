package com.maxel.geniusgame;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maxel.geniusgame.Adapter.GameBoardAdpter;
import com.maxel.geniusgame.Logic.Action;

import java.util.ArrayList;

public class GameBoard extends AppCompatActivity {

    private RecyclerView gameBoardRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameBoardRecyclerView = findViewById(R.id.gameBoardRecyclerView);

        gameBoardRecyclerView.setLayoutManager(new GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false));

        GameBoardAdpter adpter = new GameBoardAdpter(this);
        gameBoardRecyclerView.setAdapter(adpter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                Action.setContext(this);
                Action.createSequence();
            } catch (InterruptedException ignored) {}
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Action.resetAction();
    }

    @Override
    public void onBackPressed() {}
}