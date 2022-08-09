package com.example.mockproject_music.screen.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Drawer;
import com.example.mockproject_music.model.Event;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MediaPlayerCallback;
import com.example.mockproject_music.player.MyMediaPlayerController;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel  {
    private static final String TAG = "MyLog";
    private MutableLiveData<List<Drawer>> mListDrawerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Event> mEventMutableLiveData = new MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Event> getEventMutableLiveData() {
        return mEventMutableLiveData;
    }

    public void setDataEvent(Event event) {
        mEventMutableLiveData.setValue(event);
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
