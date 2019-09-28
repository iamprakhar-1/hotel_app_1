package com.example.hotel_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;

public class doorCamera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_camera);

        final VideoView doorVideoView = (VideoView)findViewById(R.id.id_Video1);
        doorVideoView.setVideoPath("https://tutorialehtml.com/assets_tutorials/media/Shaun-the-Sheep-The-Movie-Official-Trailer.mp4");
        doorVideoView.start();

        // Player controls (play,pause,etc)
        MediaController mediaBar1 = new MediaController(this);
        mediaBar1.setAnchorView(doorVideoView);
        doorVideoView.setMediaController(mediaBar1);
    }
}
