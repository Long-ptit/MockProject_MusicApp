package com.example.mockproject_music.screen.genres;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Genres;

import java.util.ArrayList;
import java.util.List;

public class GenresViewModel extends ViewModel {
    private MutableLiveData<List<Genres>> mListGenresFake = new MutableLiveData<>();

    public LiveData<List<Genres>> getListGenresFake() {
        return mListGenresFake;
    }

    public void getDataFake() {
        List<Genres> listGenrest = new ArrayList<>();
        listGenrest.add(new Genres("Pop", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Classical", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Hip-hop", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Rock", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Soul and R&B", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Instrumental", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Jazz", 56, R.drawable.img_preview_song_home));
        listGenrest.add(new Genres("Country Music", 56, R.drawable.img_preview_song_home));

        mListGenresFake.postValue(listGenrest);
    }

}
