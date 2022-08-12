package com.example.mockproject_music.screen.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Drawer;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.repository.SongRepository;
import com.example.mockproject_music.screen.main.type.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel  {
    private static final String TAG = "MyLog";
    private MutableLiveData<List<Drawer>> mListDrawerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Event> mEventMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mShowPlayer = new MutableLiveData<>(false);
    private MutableLiveData<List<Song>> mListAllSong = new MutableLiveData<>();
    private ExecutorService mExecutorService;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mExecutorService = Executors.newSingleThreadExecutor();
    }


    public LiveData<Event> getEventMutableLiveData() {
        return mEventMutableLiveData;
    }

    public LiveData<List<Song>> getListAllSong() {
        return mListAllSong;
    }

    public void loadSong() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Song> listAllSong = SongRepository.getInstance(getApplication()).getAllSongFromDevice();
                mListAllSong.postValue(listAllSong);
                MyMediaPlayerController.
                        getInstance(getApplication().getApplicationContext())
                        .setListSong(listAllSong);
            }
        });
    }

    public void setDataEvent(Event event) {
        mEventMutableLiveData.setValue(event);
    }

    public void openPlayer() {
        mShowPlayer.postValue(true);
    }

    public void closePlayer() {
        mShowPlayer.postValue(false);
    }

    public LiveData<Boolean> getShowPlayer() {
        return mShowPlayer;
    }

    public LiveData<List<Drawer>> getListDrawerMutableLiveData() {
        return mListDrawerMutableLiveData;
    }

    public void addDataDrawer() {
        List<Drawer> listDrawer = new ArrayList<>();
        listDrawer.add(new Drawer("Themes", R.drawable.ic_drawer_themes));
        listDrawer.add(new Drawer("Ringtone Cutter", R.drawable.ic_drawer_ringtone_cutter));
        listDrawer.add(new Drawer("Sleep timer", R.drawable.ic_drawer_sleep_timer));
        listDrawer.add(new Drawer("Equliser", R.drawable.ic_drawer_equliser));
        listDrawer.add(new Drawer("Drive Mode", R.drawable.ic_drawer_drive_mode));
        listDrawer.add(new Drawer("Hidden Folders", R.drawable.ic_drawer_hidden_folders));
        listDrawer.add(new Drawer("Scan Media", R.drawable.ic_drawer_scan_media));

        mListDrawerMutableLiveData.postValue(listDrawer);
    }

}
