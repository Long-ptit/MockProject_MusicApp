package com.example.mockproject_music.screen.album;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.model.Album;
import com.example.mockproject_music.repository.AlbumRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlbumViewModel extends AndroidViewModel {
    private AlbumRepository mAlbumRepository;
    private ExecutorService mExecutorService;
    private MutableLiveData<List<Album>> mMutableListFakeAlbum = new MutableLiveData<>();

    public AlbumViewModel(@NonNull Application application) {
        super(application);
        mAlbumRepository = AlbumRepository.getInstance(application.getApplicationContext());
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Album>> getMutableListFakeAlbum() {
        return mMutableListFakeAlbum;
    }

    public void getDataFakeAlbum() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mMutableListFakeAlbum.postValue(mAlbumRepository.getFakeAlbum());
            }
        });
    }
}
