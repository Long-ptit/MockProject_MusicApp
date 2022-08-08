package com.example.mockproject_music.screen.song;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.screen.main.MainViewModel;
import com.example.mockproject_music.screen.song.adapter.ViewPagerSongAdapter;
import com.example.mockproject_music.databinding.FragmentSongBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class SongFragment extends BaseFragment<MainViewModel, FragmentSongBinding> {

    private ViewPagerSongAdapter mViewPagerAdapter;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        initTabLayout();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_song;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    private void initTabLayout() {
        mViewPagerAdapter = new ViewPagerSongAdapter(requireActivity());
        binding.viewpager.setAdapter(mViewPagerAdapter);
        List<String> listName = new ArrayList<>();
        listName.add(getString(R.string.tab_all_song));
        listName.add(getString(R.string.tab_play_list));
        listName.add(getString(R.string.tab_albums));
        listName.add(getString(R.string.tab_artists));
        listName.add(getString(R.string.tab_genres));

        new TabLayoutMediator(binding.tabLayout, binding.viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(listName.get(position));
            }
        }).attach();
    }

}