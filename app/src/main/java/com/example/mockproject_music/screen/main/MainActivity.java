package com.example.mockproject_music.screen.main;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.R;
import com.example.mockproject_music.player.MediaPlayerCallback;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.player.type.UpdateType;
import com.example.mockproject_music.screen.main.adapter.DrawerAdapter;
import com.example.mockproject_music.base.BaseActivity;
import com.example.mockproject_music.databinding.ActivityMainBinding;
import com.example.mockproject_music.model.Drawer;
import com.example.mockproject_music.screen.main.type.Event;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.service.MusicService;

import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding>
        implements MediaPlayerCallback {

    private static final String TAG = "MyLog";
    private DrawerAdapter mDrawerAdapter;
    private DrawerLayout mDrawer;
    private MyMediaPlayerController mMediaController;
    private Handler mHandler;
    private HandlerThread mHandlerThread;


    @Override
    public void observerLiveData() {
        viewModel.getListDrawerMutableLiveData().observe(this, new Observer<List<Drawer>>() {
            @Override
            public void onChanged(List<Drawer> drawerModels) {
                mDrawerAdapter.setListData(drawerModels);
            }
        });

        viewModel.getEventMutableLiveData().observe(this, new Observer<Event>() {
            @Override
            public void onChanged(Event event) {
                handleEvent(event);
            }
        });

    }

    private void showData(Song song) {
        Log.d(TAG, "showData: ");
        binding.bottomPlayer.progress.setMax(mMediaController.getDuration());
        binding.bottomPlayer.tvNameSong.setText(song.getName());
        binding.bottomPlayer.tvArtisSong.setText(song.getSinger());
    }

    private void handleEvent(Event event) {
        switch (event) {
            case OPEN_MUSIC: {
                Log.d(TAG, "handleEvent: open");
                updateSeekBar();
                showData(mMediaController.getCurrentSong());
                openBottomPlayer();
                startMyService();
                break;
            }
        }
    }

    private void setPause() {
        Glide
                .with(this)
                .load(R.drawable.ic_btoom_player_pause)
                .into(binding.bottomPlayer.imgPlay);
    }

    private void setPlay() {
        Glide
                .with(this)
                .load(R.drawable.ic_bottom_player_play)
                .into(binding.bottomPlayer.imgPlay);
    }

    private void startMyService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        startService(serviceIntent);
    }

    @Override
    public void initListener() {
        mMediaController = MyMediaPlayerController.getInstance(getApplicationContext());
        mMediaController.setCallBack(this);
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        binding.bottomPlayer.imgPlay.setOnClickListener(v -> {
            playOrPauseSong();
        });

        binding.bottomPlayer.imgPrevious.setOnClickListener(v -> {
            mMediaController.previousSong();
        });

        binding.bottomPlayer.imgNext.setOnClickListener(v -> {
            mMediaController.nextSong();
        });

        binding.bottomPlayer.imgClose.setOnClickListener(v -> {
            Log.d(TAG, "delete: ");
            mMediaController.deleteSong();
        });

        setUpListenerSeekBar();
    }

    private void setUpListenerSeekBar() {
        binding.bottomPlayer.progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaController.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playOrPauseSong() {
        if (mMediaController.isPlaying()) {
            mMediaController.pauseSong();
        } else {
            mMediaController.resumeSong();
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // Permission Granted
//                    try {
//                        getMusicFromApp();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    // Permission Denied
//                    Toast.makeText(MainActivity.this, "Read Denied", Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//            }
//
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

    @Override
    public void initView() {
        mHandlerThread = new HandlerThread("Update Seekbar");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.bottomView, host.getNavController());
        setUpRcv();
        viewModel.addDataDrawer();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setUpRcv() {
        mDrawerAdapter = new DrawerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        );
        binding.rcvDrawer.setLayoutManager(layoutManager);
        binding.rcvDrawer.setAdapter(mDrawerAdapter);
    }

    public void openDrawer() {
        mDrawer = binding.drawerLayout;
        mDrawer.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) { //replace this with actual function which returns if the drawer is open
            mDrawer.close();     // replace this with actual function which closes drawer
        } else {
            super.onBackPressed();
        }
    }

    private void openBottomPlayer() {
        binding.bottomPlayer.bottomPlayer.setVisibility(View.VISIBLE);
    }

    private void hideBottomPlayer() {
        binding.bottomPlayer.bottomPlayer.setVisibility(View.GONE);
    }


    @Override
    public void updateData(UpdateType type) {
       switch (type) {
           case CHANGE_UI: {
               if (mMediaController.isPlaying()) {
                   setPause();
               } else {
                   setPlay();
               }
               break;
           }
           case CHANGE_SONG: {
               Log.d(TAG, "updateData: changesong");
               Song currentSong = mMediaController.getCurrentSong();
               showData(currentSong);
               setPause();
               openBottomPlayer();
               break;
           }

           case DELETE_SONG: {
               mHandler.removeMessages(0);
               hideBottomPlayer();
               break;
           }
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaController.removeCallBack(this);
        mHandler.removeMessages(0);
        mHandlerThread.interrupt();
    }

    private void updateSeekBar() {
        Log.d(TAG, "updateSeekBar: ");
        int currPosition = mMediaController.getCurrentPosition();
        if (binding != null) {
            runOnUiThread(() -> binding.bottomPlayer.progress.setProgress(currPosition));
        }
        mHandler.postDelayed(() -> updateSeekBar(), 1000);

    }
}