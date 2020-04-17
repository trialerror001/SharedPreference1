package com.example.androidpreferences;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

public class MusicBackground extends Service {
    MediaPlayer mplayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mplayer = MediaPlayer.create(this, R.raw.studymusic);
        mplayer.setLooping(true);
        mplayer.setVolume(50,50);
    }

    public int onStartCommand(Intent i, int flags, int startId){
        mplayer.start();
        return startId;
    }

    @Override
    public void onDestroy() {
        mplayer.stop();
        mplayer.release();
    }
}
