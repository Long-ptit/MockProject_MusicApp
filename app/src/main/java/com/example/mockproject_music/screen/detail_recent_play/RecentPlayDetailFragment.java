package com.example.mockproject_music.screen.detail_recent_play;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentRecentPlayDetailBinding;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.screen.detail_recent_play.adapter.RecentPlayDetailAdapter;
import com.example.mockproject_music.screen.home.adapter.RecentPlayAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;
import com.example.mockproject_music.screen.main.type.Event;

import java.util.List;

public class RecentPlayDetailFragment extends BaseFragment<MainViewModel, FragmentRecentPlayDetailBinding>
    implements RecentPlayDetailAdapter.CallBackRecentPlayDetail {

    private RecentPlayDetailAdapter mRecentPlayAdapter;
    private MyMediaPlayerController mMediaPlayerController;
    private List<Song> mListCurrentSong;

    @Override
    public void observerLiveData() {
        viewModel.getAllSongRecent().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songDBS) {
                Log.d("ptit", "changed");
                //it will be cause bug,
//                mMediaPlayerController.setListSong(songDBS);
                mListCurrentSong = songDBS;
                mRecentPlayAdapter.setListData(songDBS);
            }
        });
    }

    @Override
    public void initListener() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigateUp();
            }
        });

    }

    @Override
    public void initView() {
        mMediaPlayerController = MyMediaPlayerController.getInstance(getContext());
        setUpRcv();

    }

    private void setUpRcv() {
        mRecentPlayAdapter = new RecentPlayDetailAdapter(getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            getContext(),
            RecyclerView.VERTICAL,
            false
        );
        binding.rcv.setAdapter(mRecentPlayAdapter);
        binding.rcv.setLayoutManager(layoutManager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recent_play_detail;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public void onClickItem(int position) {
        //list when item click, not is realtime
        mMediaPlayerController.openMusicFromPositionWithList(position, mListCurrentSong);
        viewModel.setDataEvent(Event.OPEN_MUSIC);
    }
}