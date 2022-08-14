package com.example.mockproject_music.database.room;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SongDBRepository {
    private SongDAO mSongDAO;
    private LiveData<List<SongDB>> listLiveData;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public SongDBRepository(Application application) {
        MyDataBase myDataBase = MyDataBase.getInstance(application);
        mSongDAO = myDataBase.songDAO();

        mHandlerThread = new HandlerThread("IO");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    public LiveData<List<SongDB>> getAllSongInDB() {
        return mSongDAO.getAllSongDB();
    }

    public void insertSong(SongDB songDB) {
        mHandler.post(() -> mSongDAO.insertSong(songDB));
    }
}
