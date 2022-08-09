package com.example.mockproject_music.player;

import android.content.Context;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public final class MyMediaPlayerController implements CallBackFinish {


    private static final String TAG = "MyMediaPlayer";

    private static MyMediaPlayerController instance = null;
    private final MyMediaPlayer mMediaPlayer;
    private Context context;
    private List<Song> mListSong = new ArrayList<>();
    private int mIndexSong = 0;


    private MyMediaPlayerController(Context context) {
        this.context = context;
        mMediaPlayer = MyMediaPlayer.getInstance(context, this);
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

    public void openMusicFromPosition(int position) {
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
        nextSong();
    }
}
