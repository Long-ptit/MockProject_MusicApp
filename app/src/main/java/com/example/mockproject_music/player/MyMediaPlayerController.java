package com.example.mockproject_music.player;

import android.content.Context;
import com.example.mockproject_music.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class MyMediaPlayerController {


    private static final String TAG = "MyMediaPlayer";

    private static MyMediaPlayerController instance = null;
    private final MyMediaPlayer mMediaPlayer;
    private Context context;
    private List<Song> mListSong = new ArrayList<>();
    private int mIndexSong = 0;
    private int mPreSong = -1;


    private MyMediaPlayerController(Context context) {
        this.context = context;
        mMediaPlayer = MyMediaPlayer.getInstance(context);
    }

    public static MyMediaPlayerController getInstance(Context context) {
        if (instance == null) {
            synchronized (MyMediaPlayerController.class) {
                if (instance == null) {
                    instance = new MyMediaPlayerController(context);
                }
            }
        }
        return instance;
    }

    public void setCallBack(MediaPlayerCallback callBack) {
        mMediaPlayer.setCallBack(callBack);
    }

    public void removeCallBack(MediaPlayerCallback callBack) {
        mMediaPlayer.removeCallBack(callBack);
    }


    public Song getCurrentSong() {
        return mListSong.get(mIndexSong);
    }


    public void setListSong(List<Song> mListSong) {
        this.mListSong.clear();
        this.mListSong = mListSong;
    }

    public void setPosition(int position) {
        if (mIndexSong != position) {
            mIndexSong = position;
            mMediaPlayer.playSong(mListSong.get(mIndexSong));
        }
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void pauseSong() {
        mMediaPlayer.pauseSong();
    }

    public void resumeSong() {
        mMediaPlayer.resume();
    }

    public void nextSong() {
        if (mIndexSong == mListSong.size() - 1) {
            mIndexSong = 0;
        } else {
            mIndexSong++;
        }
        mMediaPlayer.playSong(mListSong.get(mIndexSong));
    }

    public void previousSong() {
        if (mIndexSong == 0) {
            mIndexSong = mListSong.size() - 1;
        } else {
            mIndexSong--;
        }
        mMediaPlayer.playSong(mListSong.get(mIndexSong));
    }

    public void deleteSong() {
        mMediaPlayer.delete();
    }


}
