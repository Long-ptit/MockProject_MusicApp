package com.example.mockproject_music.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.DrawerModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<List<DrawerModel>> mListDrawerMutableLiveData = new MutableLiveData<>();

    public LiveData<List<DrawerModel>> getListDrawerMutableLiveData() {
        return mListDrawerMutableLiveData;
    }

    public void addDataDrawer() {
        List<DrawerModel> listDrawer = new ArrayList<>();
        listDrawer.add(new DrawerModel("Themes", R.drawable.ic_drawer_themes));
        listDrawer.add(new DrawerModel("Ringtone Cutter", R.drawable.ic_drawer_ringtone_cutter));
        listDrawer.add(new DrawerModel("Sleep timer", R.drawable.ic_drawer_sleep_timer));
        listDrawer.add(new DrawerModel("Equliser", R.drawable.ic_drawer_equliser));
        listDrawer.add(new DrawerModel("Drive Mode", R.drawable.ic_drawer_drive_mode));
        listDrawer.add(new DrawerModel("Hidden Folders", R.drawable.ic_drawer_hidden_folders));
        listDrawer.add(new DrawerModel("Scan Media", R.drawable.ic_drawer_scan_media));

        mListDrawerMutableLiveData.postValue(listDrawer);
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
