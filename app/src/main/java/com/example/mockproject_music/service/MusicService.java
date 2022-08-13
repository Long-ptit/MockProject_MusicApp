package com.example.mockproject_music.service;

import static com.example.mockproject_music.app.MyApplication.NOTIFICATION_CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mockproject_music.R;
import com.example.mockproject_music.broadcast.MusicBrocast;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MediaPlayerCallback;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.player.type.UpdateType;

public class MusicService extends Service implements MediaPlayerCallback {
    public static final String ACTION_NAME = "ACTION_NAME";
    public static final int ID_NOTIFICATION = 1;
    public static final int ACTION_PLAY = 1;
    public static final int ACTION_PAUSE = 2;
    public static final int ACTION_CLEAR = 3;
    public static final int ACTION_NEXT = 4;
    public static final int ACTION_PREVIOUS = 5;

    private MyMediaPlayerController mMediaPlayerController;
    private Song mCurrentSong;
    private MusicBrocast mMusicBroadcast;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayerController = MyMediaPlayerController.getInstance(getApplicationContext());
        mMediaPlayerController.setCallBack(this);
        mCurrentSong = mMediaPlayerController.getCurrentSong();
        mMusicBroadcast = new MusicBrocast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(mMusicBroadcast, intentFilter);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int action = intent.getIntExtra(ACTION_NAME, -1);
        handleAction(action);
        createNotification();
        return START_NOT_STICKY;
    }

    private void createNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification_music);
        remoteViews.setTextViewText(R.id.tv_title, mCurrentSong.getName());
        remoteViews.setTextViewText(R.id.tv_Singer, mCurrentSong.getSinger());
        //remoteViews.setInt(R.id.layout, "setBackgroundResource", mCurrentSong.getPreviewResource());
//
        if (mMediaPlayerController.isPlaying()) {
            remoteViews.setImageViewResource(R.id.play_pause_button, R.drawable.ic_btoom_player_pause);
            remoteViews.setOnClickPendingIntent(R.id.play_pause_button, getPendingIntent(ACTION_PAUSE));
        } else {
            remoteViews.setImageViewResource(R.id.play_pause_button, R.drawable.ic_play);
            remoteViews.setOnClickPendingIntent(R.id.play_pause_button, getPendingIntent(ACTION_PLAY));
        }
        remoteViews.setOnClickPendingIntent(R.id.exit_button, getPendingIntent(ACTION_CLEAR));
        remoteViews.setOnClickPendingIntent(R.id.previous_button, getPendingIntent(ACTION_PREVIOUS));
        remoteViews.setOnClickPendingIntent(R.id.next_button, getPendingIntent(ACTION_NEXT));

        Notification notification = new NotificationCompat.Builder(getApplication(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .build();
        startForeground(ID_NOTIFICATION, notification);
    }

    private PendingIntent getPendingIntent(int action) {
        Intent intent = new Intent(getApplicationContext(), MusicService.class);
        intent.putExtra(ACTION_NAME, action);
        return PendingIntent.getService(getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayerController.removeCallBack(this);
        unregisterReceiver(mMusicBroadcast);
    }


    private void handleAction(int action) {
        Log.d("MyLog", "updateData service: " + action);
        switch (action) {
            case ACTION_PLAY:
                mMediaPlayerController.resumeSong();
                break;
            case ACTION_PAUSE:
                mMediaPlayerController.pauseSong();
                break;
            case ACTION_CLEAR:
                mMediaPlayerController.deleteSong();
                break;
            case ACTION_NEXT:
                mMediaPlayerController.nextSong();
                break;
            case ACTION_PREVIOUS:
                mMediaPlayerController.previousSong();
                break;
            default:
                break;
        }

    }

    @Override
    public void updateData(UpdateType type) {
        switch (type) {
            case CHANGE_UI: {
                createNotification();
                break;
            }
            case CHANGE_SONG: {
                mCurrentSong = mMediaPlayerController.getCurrentSong();
                createNotification();
                break;
            }
            case DELETE_SONG: {
                stopSelf();
                break;
            }

        }
    }
}
