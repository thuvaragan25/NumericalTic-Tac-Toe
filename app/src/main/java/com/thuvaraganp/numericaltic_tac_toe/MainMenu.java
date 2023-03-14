package com.thuvaraganp.numericaltic_tac_toe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button playGame, instructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playGame = (Button) findViewById(R.id.button);
        playGame.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        });

        instructions = (Button) findViewById(R.id.button10);
        instructions.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Instructions.class);
            startActivity(intent);
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Instructions");
//            builder.setMessage("Help Needed! Dr. Octopus needs your help. The city is in danger and he needs help getting all the ingredients to create this remedy. \n\nPlay his challenge: \n\t1.Player 1: Has all the chemicals assigned with odd numbers \n\t2.Player 2: Has all the chemicals assigned with even numbers \n\t3.In this challenge, it is very similar to Tic-Tac-Toe, but you must make sure all the chemicals diagonal, vertical or horizontal from each other add to 15. \n\t4.The first player that does this wins and will be rewarded by Dr. Octopus.\n\nGood Luck!");
//            builder.create().show();
        });
        getSupportActionBar().hide();
    }
}