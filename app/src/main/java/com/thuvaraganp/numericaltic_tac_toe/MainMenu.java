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
        });
        getSupportActionBar().hide();
    }
}