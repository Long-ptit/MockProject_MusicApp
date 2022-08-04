package com.example.mockproject_music.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.model.Playlist;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.view.MainActivity;
import com.example.mockproject_music.R;
import com.example.mockproject_music.adapter.HomeAdapter;
import com.example.mockproject_music.databinding.FragmentHomeBinding;
import com.example.mockproject_music.viewmodel.HomeViewModel;

import java.util.List;


public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding mBinding;
    private HomeViewModel mViewModel;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        setUpOnclick();
        setUpRcv();
        setUpObsevable();
        mViewModel.addFakeData();
        mViewModel.addFakePlayList();
        mViewModel.addFakeRecentPlay();
        return mBinding.getRoot();
    }

    private void setUpObsevable() {
        mViewModel.getListSongMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mAdapter.getHotRecommendAdapter().setListData(songs);
            }
        });

        mViewModel.getListPlayListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                mAdapter.getPlaylistAdapter().setListData(playlists);
            }
        });

        mViewModel.getListSongRecentMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mAdapter.getRecentPlayAdapter().setListData(songs);
            }
        });


    }

    private void setUpRcv() {
        mAdapter = new HomeAdapter(requireContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
        );
        mBinding.rcv.setLayoutManager(linearLayoutManager);
        mBinding.rcv.setAdapter(mAdapter);
    }

    private void setUpOnclick() {
        mBinding.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).openDrawer();
            }
        });
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: HomeFragment");
        super.onResume();
    }
}