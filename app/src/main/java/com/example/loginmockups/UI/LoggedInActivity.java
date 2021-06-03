package com.example.loginmockups.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.loginmockups.Model.MediaPlayerService;
import com.example.loginmockups.R;


public class LoggedInActivity extends AppCompatActivity {
    private boolean isConnect = false;
    private Button mediaPlayButton;
    private MediaPlayerService mediaPlayer;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isConnect = true;
            MediaPlayerService.LocalBinder bind = (MediaPlayerService.LocalBinder) binder;
            mediaPlayer = bind.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        mediaPlayButton = findViewById(R.id.playButton);
        mediaPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayButton.setText("Play");
                }
                else {
                    mediaPlayer.play();
                    mediaPlayButton.setText("Pause");
                    Intent intent = new Intent(LoggedInActivity.this, MediaPlayerService.class);
                    startService(intent);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(LoggedInActivity.this, MediaPlayerService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isConnect) {
            unbindService(serviceConnection);
            isConnect = false;
        }
    }
}