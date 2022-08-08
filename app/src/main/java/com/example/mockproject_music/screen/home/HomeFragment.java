package com.example.mockproject_music.screen.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.model.Playlist;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.R;
import com.example.mockproject_music.screen.home.adapter.HomeAdapter;
import com.example.mockproject_music.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {


    private static final String TAG = "HomeFragment";
    private HomeAdapter mAdapter;

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
                mAdapter.getRecentPlayAdapter().setListData(songs);
            }
        });
    }

    @Override
    public void initListener() {
//        binding.imgMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callBackListener.onOpenDrawer();
//            }
//        });
    }

    @Override
    public void initView() {
        setUpRcv();
        viewModel.addFakeData();
        viewModel.addFakePlayList();
        viewModel.addFakeRecentPlay();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }


    private void setUpRcv() {
        mAdapter = new HomeAdapter(requireContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
        );
        binding.rcv.setLayoutManager(linearLayoutManager);
        binding.rcv.setAdapter(mAdapter);
    }

}