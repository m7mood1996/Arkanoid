package com.mahmood_anas.arkanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.content.Context;
import android.view.WindowManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private GameView gameView;
    private SensorManager sensorManager;
    private Sensor orientaion;
    int z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        z = 0;
        gameView = findViewById(R.id.GameViewLayOut);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientaion = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if ( orientaion == null)
        {
            String sensorErrMsg = "";

            if (orientaion == null)
                sensorErrMsg += "\nOrientaion Sensor NOT exists!";

            sensorErrMsg += "\nThe app will exit!";

            Toast.makeText(this, sensorErrMsg, Toast.LENGTH_LONG).show();
            finish();
        }

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameView.newGame == true);
                while(true) {
                    while (z > 0)
                        gameView.paddleMoveR(z);
                    while ( z<0)
                        gameView.paddleMoveL(z);
                }
            }
        }).start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, orientaion, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType())
        {

            case Sensor.TYPE_ACCELEROMETER:
                // Get Sensor Orientation Angles (in degrees) around 3 axis
                float x = event.values[0];  // Pitch (the angle around the x-axis)
                float y = event.values[1];  // Roll (the angle around the y-axis)
                z = (int)y;  // Azimuth (the angle around the z-axis)
                /*while (z > 0.0f)
                {
                    //while(gameView == null);
                    //while(gameView.paddle == null);
                    //System.out.println("Game is \t" + gameView.paddle.getHeight());
                    // System.out.println("Ana d5alt hon sadeke");
                    gameView.paddle_dx = 10;
                    //System.out.println("K is now :\t" + k);
                }
                while (z < -0.0f)
                {
                    //System.out.println("5araaa !!");
                    //gameView.paddle.setWidth(gameView.paddle.getWidth() - 20);
                    //System.out.println("K will be :\t" + k);
                    gameView.paddle_dx = -10;
                }
                if (z == 0){
                    gameView.paddle_dx = 0;
                }

                 */
                break;
            default:
                // do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
