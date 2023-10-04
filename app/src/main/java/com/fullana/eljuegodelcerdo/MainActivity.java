package com.fullana.eljuegodelcerdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String playerTurn;
    private int player1TotalScore;
    private int player2TotalScore;
    private int playerTurnTotal;

    private Button roll;
    private Button hold ,restart;
    private TextView player1Total;
    private TextView player2Total;
    private TextView currentPlayer;
    private TextView turnTotal;
    private ImageView dice2;
    private TextView turnTotalTag;
    private boolean turno1 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializa();
        roll.setOnClickListener((listner) -> {
            hold.setEnabled(true);
            Random rand = new Random();
            int rand2 = rand.nextInt(6) + 1;
            switch (rand2) {
                case 1:
                    dice2.setImageResource(R.drawable.dice_one);
                    Toast.makeText(MainActivity.this,"Has perdido el turno",Toast.LENGTH_SHORT).show();
                    if (turno1)
                        currentPlayer.setText("Jugador 2");
                    else
                        currentPlayer.setText("Jugador 1");
                    playerTurnTotal = 0;
                    rand2 = 0;
                    break;
                case 2:
                    dice2.setImageResource(R.drawable.dice_two);
                    break;
                case 3:
                    dice2.setImageResource(R.drawable.dice_three);
                    break;
                case 4:
                    dice2.setImageResource(R.drawable.dice_four);
                    break;
                case 5:
                    dice2.setImageResource(R.drawable.dice_five);
                    break;
                case 6:
                    dice2.setImageResource(R.drawable.dice_six);
                    break;
            }
            playerTurnTotal += rand2;
            turnTotal.setText(String.valueOf(playerTurnTotal));
        });
            //If one of the dices are 1, then turn ends & turn total is 0

            hold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //If player1 holds, then add their turn total to their total score & move on to next player (also clear turn total variable)
                    if (turno1) {
                        player1TotalScore += playerTurnTotal;
                        player1Total.setText(String.valueOf(player1TotalScore));
                        turno1 = false;
                        currentPlayer.setText(playerTurn);
                        playerTurnTotal = 0;
                    } //if player2 holds, then add their turn total to their total score & move on to next player (also clear turn total variable)
                    else {
                        player2TotalScore += playerTurnTotal;
                        player2Total.setText(String.valueOf(player2TotalScore));
                        turno1 = true;
                        currentPlayer.setText(playerTurn);
                        playerTurnTotal = 0;
                    }
                    turnTotal.setText(String.valueOf(playerTurnTotal));
                    //Check if any player won!
                    if (player1TotalScore >= 50) {
                        someoneWin("Jugador 1");
                    } else if (player2TotalScore >= 50) {
                        someoneWin("Jugador 2");
                    }

                }
            });
            restart.setOnClickListener((r) -> {
                new Thread (() ->{
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }).start();
            });
        }

    private void someoneWin(String s) {
        dice2.setVisibility(View.GONE);
        turnTotal.setVisibility(View.GONE);
        turnTotalTag.setVisibility(View.GONE);
        roll.setVisibility(View.GONE);
        hold.setVisibility((View.GONE));
        restart.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this,"Felicitaciones "+s+", Ganaste!",Toast.LENGTH_SHORT).show();
    }

    public void inicializa() {
        roll =  findViewById(R.id.rolldice2);
        hold =  findViewById(R.id.Guardar);
        player1Total = findViewById(R.id.player1Total);
        player2Total = findViewById(R.id.player2Total);
        currentPlayer = findViewById(R.id.currentPlayer);
        turnTotal = findViewById(R.id.turnTotal);
        dice2 = findViewById(R.id.dice2);
        restart = findViewById(R.id.restart);
        turnTotalTag = findViewById(R.id.textView11);

        playerTurn = "Jugador 1";
        player1TotalScore = 0;
        player2TotalScore = 0;
        playerTurnTotal = 0;

        currentPlayer.setText(playerTurn);
        player1Total.setText(String.valueOf(player1TotalScore));
        player2Total.setText(String.valueOf(player2TotalScore));
        turnTotal.setText(String.valueOf(playerTurnTotal));

    }
}