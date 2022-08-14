package com.example.mockproject_music.screen.allsong;

import android.Manifest;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentAllSongBinding;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.screen.allsong.adapter.AllSongAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;
import com.example.mockproject_music.screen.main.type.Event;

import java.util.List;

public class AllSongFragment extends BaseFragment<MainViewModel, FragmentAllSongBinding>
        implements AllSongAdapter.CallBack {

    private AllSongAdapter mAllSongAdapter;
    private MyMediaPlayerController mMediaPlayer;

    @Override
    public void observerLiveData() {
        viewModel.getListAllSong().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                Log.d("ptit", "onChanged alllist: ");
                mMediaPlayer.setListSong(songs);
                mAllSongAdapter.setListData(songs);
            }
        });
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initView() {
        setUpRcv();
        mMediaPlayer = MyMediaPlayerController.getInstance(getContext());

        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "You should open permission", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.loadSong();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_song;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    private void setUpRcv() {
        mAllSongAdapter = new AllSongAdapter(getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL,
                false
        );
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mAllSongAdapter);
    }

    @Override
    public void onClickMusic(Song song, int position) {
        viewModel.setDataEvent(Event.OPEN_MUSIC);
        mMediaPlayer.openMusicFromPosition(position);
    }

}