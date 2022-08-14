package com.example.mockproject_music.database.room;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.mockproject_music.model.Song;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SongDBRepository {
    private SongDAO mSongDAO;
    private ExecutorService mExecutorService;
    private static SongDBRepository instance;

    private SongDBRepository(Application application) {
        initData(application);
    }

    public static SongDBRepository getInstance(Application application) {
        if (instance == null) {
            synchronized (SongDBRepository.class) {
                instance = new SongDBRepository(application);
            }
        }
        return instance;
    }

    private void initData(Application application) {
        MyDataBase myDataBase = MyDataBase.getInstance(application.getApplicationContext());
        mSongDAO = myDataBase.songDAO();

        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Song>> getAllSongInDB() {
        return mSongDAO.getAllSongDB();
    }

    public void insertSong(Song songDB) {
        mExecutorService.execute(() -> mSongDAO.insertSong(songDB));
    }

    public LiveData<List<Song>> getSongRecentPlay(int quantity) {
        return mSongDAO.getSongRecentPlayWithQuantity(quantity);
    }
}
