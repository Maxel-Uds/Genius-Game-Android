package com.maxel.geniusgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maxel.geniusgame.Adapter.InstructionsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionsBoard extends AppCompatActivity {

    private final List<String> instructions = new ArrayList<>();
    private RecyclerView instructionsBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_board);

        instructions.addAll(Arrays.asList(
                "1 - Bem vindo ao Genius!",
                "2 - Uma sequência de cores e sons irá piscar",
                "3 - Após a sequência terminar você deve repetir a sequência exata que foi mostrada",
                "4 - Se você repetir a sequência corretamente irá para a próxima fase",
                "5 - Se você errar o jogo acaba!",
                "6 - A cada fase concluída com sucesso você ganha 5 pontos",
                "7 - A cada fase que passa a sequência aumenta em 1 de tamanho, ficando mais e mais difícil"
        ));

        instructionsBoard = findViewById(R.id.instructions_recycler);

        instructionsBoard.setLayoutManager(new LinearLayoutManager(this));

        InstructionsAdapter adpter = new InstructionsAdapter(instructions, this);
        instructionsBoard.setAdapter(adpter);
    }
}