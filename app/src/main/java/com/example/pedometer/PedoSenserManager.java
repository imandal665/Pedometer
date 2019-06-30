package com.example.pedometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import com.example.pedometer.EventPojo.UpdateStepChangesInDisplay;

import org.greenrobot.eventbus.EventBus;

public class PedoSenserManager implements SensorEventListener {

    private Context context;
    private long steps = 0;
    private SensorManager sensorManager;
    private Sensor sSensor;


    public PedoSenserManager(Context context) {
        this.context = context;

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (sSensor != null) {

            Activity activity = (Activity) context;
            sensorManager.registerListener(this, sSensor, SensorManager.SENSOR_DELAY_FASTEST);
//            Toast.makeText(context, "Hardware Issue", Toast.LENGTH_SHORT).show();
            Log.i("Pedo", "No Hardware");
        } else {
            Toast.makeText(context, "Hardware Issue", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }
        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps++;
            EventBus.getDefault().post(new UpdateStepChangesInDisplay());
        }
    }

    public long getSteps() {
        return steps;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
