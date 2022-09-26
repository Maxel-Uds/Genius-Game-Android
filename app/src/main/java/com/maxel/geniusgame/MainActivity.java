package com.maxel.geniusgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button initGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGame = findViewById(R.id.initGame);

        initGame.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GameBoard.class);
            startActivity(intent);
        });
    }
}