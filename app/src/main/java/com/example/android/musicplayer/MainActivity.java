package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.play_button);
        Button pauseButton = (Button) findViewById(R.id.pause_button);
        Button stopButton = (Button) findViewById(R.id.stop_button);


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
}
