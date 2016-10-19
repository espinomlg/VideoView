package com.example.espino.videoview;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoView_Activity extends AppCompatActivity {

    private VideoView mvv;
    private MediaController mmc;

    //MediaPlayer en vez de mediacontroller para controlar cnd se pulsa el botón de pausa.pag 10 apuntes: no
    //garantiza el orden de ejecucion de onsaved.. onpause.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_);

        mvv = (VideoView) findViewById(R.id.videoView);

        mvv.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
       // mvv.setVideoPath(Environment.getExternalStorageDirectory().getAbsolutePath()+"mp4.mp4");
        mmc = new MediaController(this);
        mvv.setMediaController(mmc);
        mvv.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvv.resume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mvv.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mvv.seekTo(savedInstanceState.getInt("position"));
        if(!savedInstanceState.getBoolean("playing"))
            mvv.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int position = mvv.getCurrentPosition();
        outState.putInt("position", position);
        outState.putBoolean("playing", mvv.isPlaying());
    }
}
