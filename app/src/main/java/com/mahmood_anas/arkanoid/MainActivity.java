package com.mahmood_anas.arkanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
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
    Thread threads[];
    int z;
    boolean t;
    boolean firstStart;
    MediaPlayer mediaPlayer;

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onpause" );
        sensorManager.unregisterListener(this);
        for (int i =0;i<6 ; i++){
            threads[i].interrupt();
            System.out.println("thread i" + threads[i].isAlive());
        }

    }


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
        mediaPlayer = MediaPlayer.create(this,R.raw.hd_s);
        threads = new Thread[6];
        t = false;
        firstStart = true;

        if ( orientaion == null)
        {
            String sensorErrMsg = "";

            if (orientaion == null)
                sensorErrMsg += "\nOrientaion Sensor NOT exists!";

            sensorErrMsg += "\nThe app will exit!";

            Toast.makeText(this, sensorErrMsg, Toast.LENGTH_LONG).show();
            finish();
        }




    }



    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onpause1onresume");
        for (int i =0;i<6 ; i++){
            if (threads[i] != null)
            System.out.println("thread i" + threads[i].isAlive());
        }
        sensorManager.registerListener(this, orientaion, SensorManager.SENSOR_DELAY_NORMAL);
        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[0] =Thread.currentThread();
                while(gameView.ball == null);
                while(true) {
                    gameView.wallTouch();
                    if (Thread.currentThread().isInterrupted())
                        break;
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[1] =Thread.currentThread();
                while(gameView.newGame == true);
                while(true) {
                    gameView.bricksTouchA();
                    playM();
                    if(Thread.currentThread().isInterrupted())
                        break;
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[2] =Thread.currentThread();
                while(gameView.newGame == true);
                while(true) {
                    gameView.bricksTouchB();
                    playM();
                    if(Thread.currentThread().isInterrupted())
                        break;
                }
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[3] =Thread.currentThread();
                while(gameView.newGame == true);
                while(true) {
                    gameView.bricksTouchC();
                    playM();
                    if(Thread.currentThread().isInterrupted())
                        break;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[4] =Thread.currentThread();
                while(gameView.newGame == true);
                while(true) {
                    gameView.bricksTouchD();
                    playM();
                    if(Thread.currentThread().isInterrupted())
                        break;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threads[5] =Thread.currentThread();

                while(gameView.newGame == true);
                while(true) {
                    while (z > 0)
                        gameView.paddleMoveR(z);
                    while ( z<0)
                        gameView.paddleMoveL(z);
                    if(Thread.currentThread().isInterrupted())
                        break;
                }
            }
        }).start();
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


    public synchronized void playM(){
        if(gameView.soundon) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.start();


                    gameView.soundon=false;
                }
            });
        }
    }
}
