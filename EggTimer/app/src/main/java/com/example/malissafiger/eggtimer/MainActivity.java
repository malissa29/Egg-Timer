package com.example.malissafiger.eggtimer;

import android.support.v7.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timertextView;
    Button controllerbutton;
    Boolean counterisactive = false;
    CountDownTimer countDownTimer;

    public void updatetimer (int secondsleft){

        int minutes= (int) secondsleft/60;
        int seconds= secondsleft- minutes * 60;

        String secondstring= Integer.toString(seconds);

        if(seconds <= 9)
        {
            secondstring= "0" + secondstring;
        }

        timertextView.setText(Integer.toString(minutes) +":"+ secondstring);


    }

    public void controltimer(View view){

        if(counterisactive == false)
        {
            counterisactive = true;
            seekBar.setEnabled(false);
            controllerbutton.setText("stop!");


            countDownTimer= new  CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updatetimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    timertextView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
                    mediaPlayer.start();

                }
            }.start();
        }else{
            timertextView.setText("0:30");
            seekBar.setProgress(30);
            countDownTimer.cancel();
            controllerbutton.setText("GO!");
            seekBar.setEnabled(true);
            counterisactive= false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        timertextView= (TextView)findViewById(R.id.timertextView);
        controllerbutton = (Button)findViewById(R.id.controllerbutton);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updatetimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
