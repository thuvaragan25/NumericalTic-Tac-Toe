package com.thuvaraganp.numericaltic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Button back = (Button) findViewById(R.id.button12);

        back.setOnClickListener(v -> {
            this.finish();
        });
        getSupportActionBar().hide();
    }
}