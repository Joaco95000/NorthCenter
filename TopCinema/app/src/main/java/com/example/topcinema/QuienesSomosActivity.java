package com.example.topcinema;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class QuienesSomosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quienes_somos);
        try{
            VideoView videoView = findViewById(R.id.video_view);
            String videoPath ="android.resource://"+getPackageName()+"/"+R.raw.video;
            Uri uri = Uri.parse(videoPath);
            videoView.setVideoURI(uri);

            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
        }
        catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}