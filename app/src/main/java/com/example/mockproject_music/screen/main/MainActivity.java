package com.example.mockproject_music.screen.main;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.R;
import com.example.mockproject_music.broadcast.MusicBrocast;
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
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding>
        implements MediaPlayerCallback {

    private static final String TAG = "MyLog";
    private static final int REQUEST_CODE_PM_READ_EXTERNAL = 1;
    private DrawerAdapter mDrawerAdapter;
    private MyMediaPlayerController mMediaController;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int idLastestScreen;
    private MusicBrocast mMusicBroadcast;


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

        viewModel.getShowPlayer().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.bottomPlayer.bottomPlayer.setVisibility(View.VISIBLE);
                } else {
                    binding.bottomPlayer.bottomPlayer.setVisibility(View.GONE);
                }
            }
        });

    }

    private void showData(Song song) {
        Log.d(TAG, "showData: ");
        binding.bottomPlayer.progress.setMax(mMediaController.getDuration());
        binding.bottomPlayer.tvNameSong.setText(song.getName());
        binding.bottomPlayer.tvArtisSong.setText(song.getSinger());
        Glide
                .with(getApplicationContext())
                .load(song.getPreviewResource())
                .into(binding.bottomPlayer.imgPreview);
    }

    private void handleEvent(Event event) {
        switch (event) {
            case OPEN_MUSIC: {
                if (!MusicService.serviceRunning) {
                    startMyService();
                    updateSeekBar();
                }
                navigateToPlayingSong();
                viewModel.closePlayer();
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
        startService(serviceIntent);
    }

    @Override
    public void initListener() {

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

        binding.bottomPlayer.bottomPlayer.setOnClickListener(v -> {
            Log.d(TAG, "layout: ");
            viewModel.closePlayer();
            navigateToPlayingSong();
        });

        setUpListenerSeekBar();
    }

    private void initMediaPlayer() {
        mMediaController = MyMediaPlayerController.getInstance(getApplicationContext());
        mMediaController.setCallBack(this);
    }

    private void initHandler() {
        mHandlerThread = new HandlerThread("Update Seekbar");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    private void registerBroadcast() {
        mMusicBroadcast = new MusicBrocast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(mMusicBroadcast, intentFilter);
    }

    private void navigateToPlayingSong() {
        idLastestScreen = binding.bottomView.getSelectedItemId();
        binding.bottomView.setSelectedItemId(R.id.playSongFragment);
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

    @Override
    public void initView() {
        setUpNavigation();
        setUpRcv();
        initMediaPlayer();
        registerBroadcast();
        initHandler();
        showPlayerWhenOpenApp();
        viewModel.addDataDrawer();
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PM_READ_EXTERNAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PM_READ_EXTERNAL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showPlayerWhenOpenApp() {
        if (MusicService.serviceRunning) {
            updateSeekBar();
            showData(mMediaController.getCurrentSong());
            viewModel.openPlayer();
        }
    }

    private void setUpNavigation() {
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.bottomView, host.getNavController());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
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
        binding.drawerLayout.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) { //replace this with actual function which returns if the drawer is open
            binding.drawerLayout.close();     // replace this with actual function which closes drawer
        } else if (binding.bottomView.getSelectedItemId() == R.id.playSongFragment) {
            binding.bottomView.setSelectedItemId(idLastestScreen);
            Log.d(TAG, "onBackPressed kk: ");
        } else {
            super.onBackPressed();
        }
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
                Log.d("ptit", "updateData: changesong");
                Song currentSong = mMediaController.getCurrentSong();
                showData(currentSong);
                setPause();
                viewModel.addSongToRoom(mMediaController.getCurrentSong());
                break;
            }

            case DELETE_SONG: {
                mHandler.removeMessages(0);
                binding.bottomPlayer.bottomPlayer.setVisibility(View.GONE);
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
        unregisterReceiver(mMusicBroadcast);
    }

    private void updateSeekBar() {
        if (binding != null) {
            runOnUiThread(() -> binding.bottomPlayer.progress.setProgress(mMediaController.getCurrentPosition()));
        }
        mHandler.postDelayed(() -> updateSeekBar(), 1000);
    }

    public void navigateToDetailScreen() {
        idLastestScreen = binding.bottomView.getSelectedItemId();
        binding.bottomView.setSelectedItemId(R.id.recentPlayDetailFragment);
    }

}