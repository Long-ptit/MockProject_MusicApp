package com.example.mockproject_music.screen.artists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.model.Artists;
import com.example.mockproject_music.repository.ArtistRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArtistViewModel extends AndroidViewModel {

    private MutableLiveData<List<Artists>> mListFakeArtist = new MutableLiveData<>();
    private ArtistRepository mArtistRepository;
    private ExecutorService mExecutorService;

    public LiveData<List<Artists>> getListFake() {
        return mListFakeArtist;
    }

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        mArtistRepository = ArtistRepository.getInstance(application.getApplicationContext());
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public void getDataFake() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mListFakeArtist.postValue(mArtistRepository.getFakeArtist());
            }
        });
    }
}
