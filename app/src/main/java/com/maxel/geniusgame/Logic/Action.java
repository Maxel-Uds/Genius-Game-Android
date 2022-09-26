package com.maxel.geniusgame.Logic;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.maxel.geniusgame.GameBoard;
import com.maxel.geniusgame.R;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Action {

    private static Context context;
    private static final List<Button> gameButtons = new ArrayList<>();
    private static final List<Button>  sequenceButtons = new ArrayList<>();
    private static final Map<Button, Integer> soundByButton = new HashMap<>();
    private static Integer sequenceSize = 1;
    private static Integer points = 0;
    private static Integer plays = 0;

    public static void checkPlay(Button button) {
        if(!sequenceButtons.get(plays).equals(button)) {
            endGame();
        }
        else if((sequenceButtons.size() - 1) == plays) {
            points += 5;
            plays = 0;
            sequenceSize++;
            sequenceButtons.clear();
            nextRound();
            createSequence();
        }
        else {
            plays++;
        }
    }

    public static void createSequence() {
        for(int i = 0; i < sequenceSize; i++) {
            sequenceButtons.add(gameButtons.get(getRandomNumber()));
        }

        turnOnSequence();
    }

    public static void playSound(Context context, Integer sound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, sound);
        mediaPlayer.start();
    }

    private static void turnOnSequence() {
        enableButtons(false);

        new Thread(() -> {
            sequenceButtons.forEach(button -> {
                try {
                    Thread.sleep(600);
                    turnOnButton(button);
                    Thread.sleep(600);
                } catch (InterruptedException ignored) {}
            });

            enableButtons(true);
        }).start();
    }

    private static void turnOnButton(Button button) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(700);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);

        button.startAnimation(animation);
        playSound(button.getContext(), soundByButton.get(button));

        new Thread(() -> {
            try {
                Thread.sleep(700);
                button.clearAnimation();
            } catch (Exception ignored){}
        }).start();
    }

    private static void enableButtons(Boolean bol) {
        gameButtons.forEach(button -> button.setClickable(bol));
    }

    private static void nextRound() {
        Snackbar.make(((GameBoard) context).findViewById(R.id.gameBoardRecyclerView), MessageFormat.format("Rodada {0} concluída!", sequenceSize - 1), 650).show();
    }

    private static void endGame() {
        playSound(context, R.raw.lose);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Fim de Jogo");
        builder.setMessage("O jogo acabou, sua pontuação é: " + points + "\nRodadas jogadas: " + sequenceSize + "\nDeseja jogar novamente?");

        builder.setPositiveButton("Jogar de Novo", (dialogInterface, i) -> {
            points = 0;
            plays = 0;
            sequenceSize = 1;
            sequenceButtons.clear();
            createSequence();
        });


        builder.setNegativeButton("Sair do Jogo", (dialogInterface, i) -> {
            GameBoard board = (GameBoard) context;
            board.finish();
        });


        AlertDialog alerta = builder.create();
        alerta.show();
    }

    private static Integer getRandomNumber() {
        Random random = new Random();
        return random.nextInt(4);
    }

    public static void resetAction() {
        soundByButton.clear();
        gameButtons.clear();
        sequenceButtons.clear();
        sequenceSize = 1;
        points = 0;
        plays = 0;
    }

    public static List<Button> getGameButtons() {
        return gameButtons;
    }

    public static Map<Button, Integer> getSoundByButton() {
        return soundByButton;
    }

    public static void setContext(Context con) {
        context = con;
    }
}
