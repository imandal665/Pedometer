package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.pedometer.EventPojo.UpdateStepChangesInDisplay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    //    @BindView(R.id.steps_textView)
    TextView stepsTextView;
    private long steps;

    @BindView(R.id.walking_animation)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.main_activity_toolbar)
    Toolbar toolbar;

    private PedoSenserManager pedoSenserManager;
    private boolean endTimerActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTheme(R.style.MainActivity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
        stepsTextView = findViewById(R.id.steps_textView);
        EventBus.getDefault().register(this);
        pedoSenserManager = new PedoSenserManager(MainActivity.this);
        updateSteps(new UpdateStepChangesInDisplay());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSteps(UpdateStepChangesInDisplay updateStepChangesInDisplay) {
        stepsTextView.setText("Steps Taken - " + String.valueOf(pedoSenserManager.getSteps()));
        if (pedoSenserManager.getSteps() != 0) {
            Log.i(TAG, String.valueOf(lottieAnimationView.getSpeed()));
            if (!endTimerActive)
                beginAnimationTimer();
            endAnimationTimer();
        }

    }

    private void beginAnimationTimer() {
        new CountDownTimer(10000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished > 0) {

                    lottieAnimationView.setSpeed(1.0f);
                }
                if (millisUntilFinished > 1000) {
                    lottieAnimationView.setSpeed(0.9f);
                }
                if (millisUntilFinished > 2000) {
                    lottieAnimationView.setSpeed(0.8f);
                }
                if (millisUntilFinished > 3000) {
                    lottieAnimationView.setSpeed(0.7f);
                }
                if (millisUntilFinished > 4000) {
                    lottieAnimationView.setSpeed(0.6f);
                }
                if (millisUntilFinished > 5000) {
                    lottieAnimationView.setSpeed(0.5f);
                }
                if (millisUntilFinished > 6000) {
                    lottieAnimationView.setSpeed(0.4f);
                }
                if (millisUntilFinished > 7000) {
                    lottieAnimationView.setSpeed(0.3f);
                }
                if (millisUntilFinished > 8000) {
                    lottieAnimationView.setSpeed(0.2f);
                }
                if (millisUntilFinished > 9000) {
                    if (!lottieAnimationView.isAnimating())
                        lottieAnimationView.playAnimation();
                    lottieAnimationView.setSpeed(0.1f);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    private void endAnimationTimer() {
        new CountDownTimer(35000, 1000) {
            public void onTick(long millisUntilFinished) {
                endTimerActive = true;
                if (millisUntilFinished < 8000) {
                    lottieAnimationView.setSpeed(0.8f);
                }
                if (millisUntilFinished < 7000) {
                    lottieAnimationView.setSpeed(0.7f);
                }
                if (millisUntilFinished < 6000) {
                    lottieAnimationView.setSpeed(0.6f);
                }
                if (millisUntilFinished < 5000) {
                    lottieAnimationView.setSpeed(0.5f);
                }
                if (millisUntilFinished < 4000) {
                    lottieAnimationView.setSpeed(0.4f);
                }
                if (millisUntilFinished < 3000) {
                    lottieAnimationView.setSpeed(0.3f);
                }
                if (millisUntilFinished < 2000) {
                    lottieAnimationView.setSpeed(0.2f);
                }
                if (millisUntilFinished < 1000) {
                    lottieAnimationView.setSpeed(0.1f);
                }

            }

            public void onFinish() {
                endTimerActive = false;
                lottieAnimationView.setSpeed(0f);

            }
        }.start();
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
