package com.example.mockproject_music.player;

import android.content.Context;
import android.util.Log;

import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.util.Util;

import java.util.ArrayList;
import java.util.List;

public final class MyMediaPlayerController implements CallBackFinish {


    private static final String TAG = "MyMediaPlayer";

    private static MyMediaPlayerController instance = null;
    private final MyMediaPlayer mMediaPlayer;
    private Context mContext;
    private List<Song> mListSong;
    private int mIndexSong = 0;


    private MyMediaPlayerController(Context mContext) {
        this.mContext = mContext;
        mMediaPlayer = MyMediaPlayer.getInstance(mContext, this);
        mListSong = new ArrayList<>();
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

    //using this function when only one list in screen
    public void openMusicFromPositionWithList(int position, List<Song> mListSong) {
        this.mListSong.clear();
        this.mListSong = mListSong;
        mIndexSong = position;
        mMediaPlayer.playSong(mListSong.get(position));
    }

    //using this function when have many list in screen
    public void openMusicFromPosition(int position) {
        mIndexSong = position;
        mMediaPlayer.playSong(mListSong.get(position));
    }

    public void setPosition(int mIndexSong) {
        this.mIndexSong = mIndexSong;
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

    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public void seekTo(int duration) {
        mMediaPlayer.seekTo(duration);
    }


    @Override
    public void onCompleteSong() {
        Log.d("ptit", "onCompleteSong: ");
        nextSong();
    }
}
