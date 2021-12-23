package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
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
//    TextView stepsTextView;
    private long steps;

    @BindView(R.id.walking_animation)
    LottieAnimationView lottieAnimationView;
    //    @BindView(R.id.main_activity_toolbar)
//    Toolbar toolbar;
    MediaPlayer mediaPlayer;

    private PedoSenserManager pedoSenserManager;
    private boolean endTimerActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTheme(R.style.MainActivity);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
        mediaPlayer = MediaPlayer.create(this, R.raw.stay);
//        toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        stepsTextView = findViewById(R.id.steps_textView);
        EventBus.getDefault().register(this);
        pedoSenserManager = new PedoSenserManager(MainActivity.this);
        updateSteps(new UpdateStepChangesInDisplay());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSteps(UpdateStepChangesInDisplay updateStepChangesInDisplay) {
//        stepsTextView.setText("Steps Taken - " + String.valueOf(pedoSenserManager.getSteps()));
        if (pedoSenserManager.getSteps() != 0) {
            Log.i(TAG, String.valueOf(lottieAnimationView.getSpeed()));
            if (!endTimerActive)
                beginAnimationTimer();
            endAnimationTimer();
        }


    }

    private void beginAnimationTimer() {
        new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished > 0) {
                    lottieAnimationView.setSpeed(1.0f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(100f));
                }
                if (millisUntilFinished > 1000) {
                    lottieAnimationView.setSpeed(0.9f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(90f));
                }
                if (millisUntilFinished > 2000) {
                    lottieAnimationView.setSpeed(0.8f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(80f));
                }
                if (millisUntilFinished > 3000) {
                    lottieAnimationView.setSpeed(0.7f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(70f));
                }
                if (millisUntilFinished > 4000) {
                    lottieAnimationView.setSpeed(0.6f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(60f));
                }
                if (millisUntilFinished > 5000) {
                    lottieAnimationView.setSpeed(0.5f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(50f));
                }
                if (millisUntilFinished > 6000) {
                    lottieAnimationView.setSpeed(0.4f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(40f));
                }
                if (millisUntilFinished > 7000) {
                    lottieAnimationView.setSpeed(0.3f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(30f));
                }
                if (millisUntilFinished > 8000) {
                    lottieAnimationView.setSpeed(0.2f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(20f));
                }
                if (millisUntilFinished > 9000) {
                    if (!lottieAnimationView.isAnimating())
                        lottieAnimationView.playAnimation();
                    lottieAnimationView.setSpeed(0.1f);

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            if (!mediaPlayer.isPlaying()) {
//                        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(10f));
                                mediaPlayer.start();
                            }
                        }
                    };
                    thread.start();


                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    private void endAnimationTimer() {
        new CountDownTimer(10000, 100) {
            public void onTick(long millisUntilFinished) {
                endTimerActive = true;
                if (millisUntilFinished < 8000) {
                    lottieAnimationView.setSpeed(0.8f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(80f));
                }
                if (millisUntilFinished < 7000) {
                    lottieAnimationView.setSpeed(0.7f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(70f));
                }
                if (millisUntilFinished < 6000) {
                    lottieAnimationView.setSpeed(0.6f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(60f));
                }
                if (millisUntilFinished < 5000) {
                    lottieAnimationView.setSpeed(0.5f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(50f));
                }
                if (millisUntilFinished < 4000) {
                    lottieAnimationView.setSpeed(0.4f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(40f));
                }
                if (millisUntilFinished < 3000) {
                    lottieAnimationView.setSpeed(0.3f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(30f));
                }
                if (millisUntilFinished < 2000) {
                    lottieAnimationView.setSpeed(0.2f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(20f));
                }
                if (millisUntilFinished < 1000) {
                    lottieAnimationView.setSpeed(0.1f);
//                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(10f));
                }

            }

            public void onFinish() {
                endTimerActive = false;
                lottieAnimationView.setSpeed(0f);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        if (!mediaPlayer.isPlaying()) {
                            if (mediaPlayer.isPlaying())
                                mediaPlayer.pause();
                        }
                    }
                };
                thread.start();

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
