package com.example.mockproject_music.screen.play_song;

import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.customview.CirSeekBar;
import com.example.mockproject_music.databinding.FragmentPlaySongBinding;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MediaPlayerCallback;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.player.type.UpdateType;
import com.example.mockproject_music.screen.main.MainViewModel;
import com.example.mockproject_music.util.Util;


public class PlaySongFragment extends BaseFragment<MainViewModel,FragmentPlaySongBinding>
    implements MediaPlayerCallback, CirSeekBar.CallBackProgress {

    private MyMediaPlayerController mMediaController;
    private boolean mIsKillService;
    private static final String TAG = "MyLog";
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Handler mMainHandler;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {
        mMediaController.setCallBack(this);
        binding.circleSeekbar.setCallBack(this);

        binding.imgOption.setOnClickListener(v -> {
           openMenu(v);
        });

        binding.imgBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.imgPlayOrPause.setOnClickListener(v -> {
            playOrPauseSong();
        });

        binding.imgNext.setOnClickListener(v -> {
            mMediaController.nextSong();
        });

        binding.imgPrevious.setOnClickListener(v -> {
            mMediaController.previousSong();
        });
    }

    private void openMenu(View v) {
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
    }


    @Override
    public void initView() {
        mIsKillService = false;
        viewModel.closePlayer();
        mMediaController = MyMediaPlayerController.getInstance(requireContext());
        mHandlerThread = new HandlerThread("Circle");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
        mMainHandler = new Handler(Looper.getMainLooper());
        handleData();
        //show current state data first time open player
        showDataSong(mMediaController.getCurrentSong());
        getPlayOrPause();
    }

    private void handleData() {
        int position = mMediaController.getCurrentPosition();
        int duration = mMediaController.getDuration();
        float progress =  (position /(float) duration * 360);
        Log.d(TAG, "handleData: " + progress);
        if (binding != null && mMainHandler != null) {
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    binding.tvCurrentTime.setText(Util.convertMilToMinutes(position));
                    binding.circleSeekbar.setData(progress);
                }
            });

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handleData();
                }
            },1000);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_song;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public void updateData(UpdateType type) {
        switch (type) {
            case CHANGE_UI: {
                getPlayOrPause();
                break;
            }
            case CHANGE_SONG: {
                Song currentSong = mMediaController.getCurrentSong();
                showDataSong(currentSong);
                setPause();
                break;
            }

            case DELETE_SONG: {
                //unregister listener
                mIsKillService = true;
                getActivity().onBackPressed();
                break;
            }
        }
    }

    private void getPlayOrPause() {
        if (mMediaController.isPlaying()) {
            setPause();
        } else {
            setPlay();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        mMediaController.removeCallBack(this);
        if (!mIsKillService) {
            viewModel.openPlayer();
        }
        mHandler.removeMessages(0);
        mMainHandler = null;
        mHandlerThread.interrupt();

    }

    private void setPause() {
        Glide
                .with(requireContext())
                .load(R.drawable.ic_btoom_player_pause)
                .into(binding.imgPlayOrPause);
    }

    private void setPlay() {
        Glide
                .with(requireContext())
                .load(R.drawable.ic_bottom_player_play)
                .into(binding.imgPlayOrPause);
    }

    @SuppressLint("SetTextI18n")
    private void showDataSong(Song song) {
        binding.tvNameSong.setText(song.getName());
        binding.tvArtist.setText(song.getSinger());
        binding.tvDuration.setText(" | " + Util.convertMilToMinutes(song.getDuration()));
        binding.tvAlbum.setText(" Album - " + song.getAlbumName());
    }

    public void playOrPauseSong() {
        if (mMediaController.isPlaying()) {
            mMediaController.pauseSong();
        } else {
            mMediaController.resumeSong();
        }
    }

    @Override
    public void onSeek(float progress) {
        int position = (int)(progress/360* mMediaController.getDuration());
        mMediaController.seekTo(position);
    }
}