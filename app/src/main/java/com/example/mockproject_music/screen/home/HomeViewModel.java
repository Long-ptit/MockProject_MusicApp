package com.example.mockproject_music.screen.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Playlist;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<List<Song>> mListSongMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Playlist>> mListPlayListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Song>> mListSongRecentMutableLiveData = new MutableLiveData<>();

    public LiveData<List<Song>> getListSongRecentMutableLiveData() {
        return mListSongRecentMutableLiveData;
    }

    public LiveData<List<Song>> getListSongMutableLiveData() {
        return mListSongMutableLiveData;
    }

    public LiveData<List<Playlist>> getListPlayListMutableLiveData() {
        return mListPlayListMutableLiveData;
    }

    public void addFakeRecentPlay() {
        List<Song> listSong = new ArrayList<>();
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));


        mListSongRecentMutableLiveData.postValue(listSong);
    }

    public void addFakePlayList() {
        List<Playlist> listPlayList = new ArrayList<>();
        listPlayList.add(new Playlist("Classic Playlist", R.drawable.img_preview_song_home,"Piano Guys"));
        listPlayList.add(new Playlist("Classic Playlist", R.drawable.img_preview_song_home,"Piano Guys"));
        listPlayList.add(new Playlist("Classic Playlist", R.drawable.img_preview_song_home,"Piano Guys"));
        listPlayList.add(new Playlist("Classic Playlist", R.drawable.img_preview_song_home,"Piano Guys"));
        listPlayList.add(new Playlist("Classic Playlist", R.drawable.img_preview_song_home,"Piano Guys"));

        mListPlayListMutableLiveData.postValue(listPlayList);
    }

    public void addFakeData() {
        List<Song> listSong = new ArrayList<>();
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));
        listSong.add(new Song(R.drawable.img_preview_song_home, "Sound of Sky", "Dilon Bruce", "1"));



        mListSongMutableLiveData.postValue(listSong);
    }

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
}
