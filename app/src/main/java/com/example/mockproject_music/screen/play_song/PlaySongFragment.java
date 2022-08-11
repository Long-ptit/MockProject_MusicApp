package com.example.mockproject_music.screen.play_song;

import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentPlaySongBinding;
import com.example.mockproject_music.screen.main.MainViewModel;


public class PlaySongFragment extends BaseFragment<MainViewModel,FragmentPlaySongBinding> {


    private static final String TAG = "MyLog";

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {
        binding.imgOption.setOnClickListener(v -> {
            PopupMenu pm = new PopupMenu(getContext(), v, Gravity.BOTTOM);
            pm.getMenuInflater().inflate(R.menu.popup_menu_play_song, pm.getMenu());
            pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            pm.show();
        });

        binding.imgBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.closePlayer();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        viewModel.openPlayer();
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onpause: ");
        super.onPause();
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_song;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }
}