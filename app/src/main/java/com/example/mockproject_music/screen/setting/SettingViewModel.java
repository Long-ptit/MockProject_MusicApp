package com.example.mockproject_music.screen.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingViewModel extends AndroidViewModel {

    private MutableLiveData<List<Setting>> mListMutableData = new MutableLiveData<>();

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Setting>> getListMutableData() {
        return mListMutableData;
    }

    public void getDataSetting() {
        List<Setting> settingModelList = new ArrayList<>();

        settingModelList.add(new Setting("Display", R.drawable.ic_setting_display));
        settingModelList.add(new Setting("Audio", R.drawable.ic_setting_audio));
        settingModelList.add(new Setting("Headset", R.drawable.ic_setting_heaset));
        settingModelList.add(new Setting("Lockscreen", R.drawable.ic_setting_lockscreen));
        settingModelList.add(new Setting("Advanced", R.drawable.ic_setting_advanced));
        settingModelList.add(new Setting("Other", R.drawable.ic_setting_other));

        mListMutableData.postValue(settingModelList);
    }
}
