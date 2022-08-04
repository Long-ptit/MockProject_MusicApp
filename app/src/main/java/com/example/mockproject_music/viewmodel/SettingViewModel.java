package com.example.mockproject_music.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.SettingModel;

import java.util.ArrayList;
import java.util.List;

public class SettingViewModel extends AndroidViewModel {

    private MutableLiveData<List<SettingModel>> mListMutableData = new MutableLiveData<>();

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<SettingModel>> getListMutableData() {
        return mListMutableData;
    }

    public void getDataSetting() {
        List<SettingModel> settingModelList = new ArrayList<>();

        settingModelList.add(new SettingModel("Display", R.drawable.ic_setting_display));
        settingModelList.add(new SettingModel("Audio", R.drawable.ic_setting_audio));
        settingModelList.add(new SettingModel("Headset", R.drawable.ic_setting_heaset));
        settingModelList.add(new SettingModel("Lockscreen", R.drawable.ic_setting_lockscreen));
        settingModelList.add(new SettingModel("Advanced", R.drawable.ic_setting_advanced));
        settingModelList.add(new SettingModel("Other", R.drawable.ic_setting_other));

        mListMutableData.postValue(settingModelList);
    }
}
