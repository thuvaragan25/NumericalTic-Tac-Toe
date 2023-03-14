package com.thuvaraganp.numericaltic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        Handler handler = new Handler();

//        final Runnable r = new Runnable() {
//            public void run() {
//                handler.postDelayed(this, 1000);
//                Intent i = new Intent(SplashScreen.this, MainMenu.class);
//                startActivity(i);
//            }
//        };
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(SplashScreen.this, MainMenu.class);
                startActivity(intent);
                finish();
            }

        }, 1000);

    }
}