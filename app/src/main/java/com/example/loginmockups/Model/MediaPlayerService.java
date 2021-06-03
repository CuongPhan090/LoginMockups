package com.example.loginmockups.Model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.loginmockups.R;



public class MediaPlayerService extends Service {
    IBinder binder = new LocalBinder();
    MediaPlayer mediaPlayer;
    private static String TAG = MediaPlayerService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        mediaPlayer = MediaPlayer.create(MediaPlayerService.this, R.raw.railbreak);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
            }
        });
        return Service.START_REDELIVER_INTENT;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binder;
    }

    public class LocalBinder extends Binder {
        public MediaPlayerService getService() {
            return MediaPlayerService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // release the song resource
        Log.d(TAG, "onDestroy");
        mediaPlayer.release();
        super.onDestroy();
    }

    // Client method
    public void play() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }
}
