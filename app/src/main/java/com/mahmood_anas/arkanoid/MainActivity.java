package com.mahmood_anas.arkanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        gameView = findViewById(R.id.GameViewLayOut);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.ball == null);
                while(true)
                    gameView.wallTouch();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.newGame == true);
                while(true)
                    gameView.bricksTouchA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.newGame == true);
                while(true)
                    gameView.bricksTouchB();
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.newGame == true);
                while(true)
                    gameView.bricksTouchC();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.newGame == true);
                while(true)
                    gameView.bricksTouchD();
            }
        }).start();

    }
}
