package com.mahmood_anas.arkanoid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Orientation {
    private SensorManager sensorManager;
    private Sensor orientaion;
    private SensorEventListener sensorEventListener;
    public interface Listener
    {
        void onRotation(float rz);
    }
    private Listener listener;

    public void setListener(Listener l)
    {
        listener = l;
    }

    public Orientation(Context context)
    {
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        orientaion = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (orientaion == null){
            System.out.println("There's no sensor !!!!!");
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] values = sensorEvent.values;
                // getting movement from x,y,z axis
                float x = values[0];
                float y = values[1];
                float z = values[2];

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    public void registerSensor(){
        sensorManager.registerListener(sensorEventListener,orientaion,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregisterSensor(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
