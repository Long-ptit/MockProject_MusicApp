package com.example.mockproject_music.screen.home;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.model.Playlist;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.R;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.screen.home.adapter.HomeAdapter;
import com.example.mockproject_music.databinding.FragmentHomeBinding;
import com.example.mockproject_music.screen.home.adapter.RecentPlayAdapter;
import com.example.mockproject_music.screen.main.MainActivity;
import com.example.mockproject_music.screen.main.MainViewModel;
import com.example.mockproject_music.screen.main.type.Event;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<MainViewModel, FragmentHomeBinding>
    implements HomeAdapter.CallBackHome, RecentPlayAdapter.CallBackRecentPlay {


    private static final String TAG = "HomeFragment";
    private HomeAdapter mAdapter;
    private List<Song> mListRecentPlay;
    private MyMediaPlayerController mControllerMusic;

    @Override
    public void observerLiveData() {
        viewModel.getListSongMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mAdapter.getHotRecommendAdapter().setListData(songs);
            }
        });

        viewModel.getListPlayListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                mAdapter.getPlaylistAdapter().setListData(playlists);
            }
        });

        viewModel.getListSongRecentMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                mListRecentPlay = songs;
                mAdapter.getRecentPlayAdapter().setListData(songs);
            }
        });
    }

    @Override
    public void initListener() {
        binding.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).openDrawer();
            }
        });
    }

    @Override
    public void initView() {
        mListRecentPlay = new ArrayList<>();
        mControllerMusic = MyMediaPlayerController.getInstance(getContext());
        setUpRcv();
        viewModel.addFakeData();
        viewModel.addFakePlayList();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }


    private void setUpRcv() {
        mAdapter = new HomeAdapter(requireContext(), this);
        mAdapter.setCallBackRecent(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
        );
        binding.rcv.setLayoutManager(linearLayoutManager);
        binding.rcv.setAdapter(mAdapter);
    }

    @Override
    public void onClickViewAll() {
        ((MainActivity) getActivity()).navigateToDetailScreen();
    }

    @Override
    public void onClickSongRecent(int position) {
        viewModel.setDataEvent(Event.OPEN_MUSIC);
        mControllerMusic.openMusicFromPositionWithList(position, mListRecentPlay);
    }
}