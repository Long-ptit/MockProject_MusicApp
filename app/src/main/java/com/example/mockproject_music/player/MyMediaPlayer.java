package com.example.mockproject_music.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.type.UpdateType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyMediaPlayer {
    private static MyMediaPlayer instance;
    private final MediaPlayer mMediaPlayer;
    private List<MediaPlayerCallback> mListCallBack;
    private Context mContext;

    public MyMediaPlayer(Context context) {
        mListCallBack = new ArrayList<>();
        mMediaPlayer = new MediaPlayer();
        mContext = context;
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //handle auto next, i not do this right now
            }
        });
    }

    public static MyMediaPlayer getInstance(Context context) {
        if (instance == null) {
            synchronized (MyMediaPlayer.class) {
                instance = new MyMediaPlayer(context);

            }
        }
        return instance;
    }

    private void notifyDataUpdate(UpdateType type) {
        for (MediaPlayerCallback callback : mListCallBack) {
            callback.updateData(type);
        }
    }


    public synchronized void playSong(Song song) {
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(song.getDataSource()));
            mMediaPlayer.prepare();
            mMediaPlayer.setVolume(100f, 100f);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.start();
            notifyDataUpdate(UpdateType.CHANGE_SONG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void pauseSong() {
        mMediaPlayer.pause();
        notifyDataUpdate(UpdateType.CHANGE_UI);
    }

    public synchronized void resume() {
        mMediaPlayer.start();
        notifyDataUpdate(UpdateType.CHANGE_UI);
    }

    public void delete() {
        mMediaPlayer.reset();
        notifyDataUpdate(UpdateType.DELETE_SONG);
    }


    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void setCallBack(MediaPlayerCallback callBack) {
        mListCallBack.add(callBack);
    }

    public void removeCallBack(MediaPlayerCallback callback) {
        mListCallBack.remove(callback);
    }
}
