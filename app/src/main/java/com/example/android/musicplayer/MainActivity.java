package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.concurrent.TimeUnit;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer audioPlayer;

    private Handler myHandler = new Handler();
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.play_button);
        Button pauseButton = (Button) findViewById(R.id.pause_button);
        Button stopButton = (Button) findViewById(R.id.stop_button);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setClickable(false);

        // create listeners for buttons
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // create audioPlayer object if there's none
                if(audioPlayer == null){
                    audioPlayer = MediaPlayer.create(MainActivity.this, R.raw.song);
                }
                // play the audio file
                audioPlayer.start();

                // get current position and duration
                seekBar.setProgress(audioPlayer.getCurrentPosition());
                myHandler.postDelayed(UpdateSongTime,100);

            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                audioPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopPlayback();
            }
        });
    }

    private void stopPlayback(){
        if (audioPlayer != null){
            audioPlayer.stop();
            audioPlayer.release();
            audioPlayer = null;
        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            seekBar.setProgress(audioPlayer.getCurrentPosition());
            myHandler.postDelayed(this, 100);
        }
    };
}
