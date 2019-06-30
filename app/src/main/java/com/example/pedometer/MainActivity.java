package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedometer.EventPojo.UpdateStepChangesInDisplay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.steps_textView)
    TextView stepsTextView;
    private long steps;

    private PedoSenserManager pedoSenserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        stepsTextView = findViewById(R.id.steps_textView);
        EventBus.getDefault().register(this);
        pedoSenserManager = new PedoSenserManager(MainActivity.this);
        updateSteps(new UpdateStepChangesInDisplay());


//        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Sensor sSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
//        if (sSensor != null) {
//            sensorManager.registerListener(this, sSensor, SensorManager.SENSOR_DELAY_UI);
//        } else {
//            Toast.makeText(this, "Hardware unavailabele", Toast.LENGTH_SHORT).show();
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSteps(UpdateStepChangesInDisplay updateStepChangesInDisplay) {
        stepsTextView.setText("Steps Taken - "+String.valueOf(pedoSenserManager.getSteps()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        Sensor sensor = event.sensor;
//        float[] values = event.values;
//        int value = -1;
//
//        if (values.length > 0) {
//            value = (int) values[0];
//        }
//        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
//            steps++;
//            stepsTextView.setText(String.valueOf(steps));
//        }
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }
}
