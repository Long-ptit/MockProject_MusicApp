package com.example.mockproject_music.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

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
    private CallBackFinish mCallBack;

    public MyMediaPlayer(Context context, CallBackFinish callBackSuccess) {
        mListCallBack = new ArrayList<>();
        mMediaPlayer = new MediaPlayer();
        mContext = context;
        this.mCallBack = callBackSuccess;
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mCallBack.onCompleteSong();
                //handle auto next, i don't do this right now
            }
        });
    }

    public static MyMediaPlayer getInstance(Context context, CallBackFinish callBackSuccess) {
        if (instance == null) {
            synchronized (MyMediaPlayer.class) {
                instance = new MyMediaPlayer(context, callBackSuccess);
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

    public synchronized void delete() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        notifyDataUpdate(UpdateType.DELETE_SONG);
    }

    public int getCurrentPosition() {
       return mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public synchronized void seekTo(int position) {
        mMediaPlayer.seekTo(position);
    }

    public void setCallBack(MediaPlayerCallback callBack) {
        mListCallBack.add(callBack);
    }

    public void removeCallBack(MediaPlayerCallback callback) {
        mListCallBack.remove(callback);
    }
}
