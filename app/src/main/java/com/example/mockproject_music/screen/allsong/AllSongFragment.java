package com.example.mockproject_music.screen.allsong;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;

import com.example.mockproject_music.R;
import com.example.mockproject_music.screen.allsong.adapter.AllSongAdapter;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentAllSongBinding;
import com.example.mockproject_music.screen.main.type.Event;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.screen.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllSongFragment extends BaseFragment<MainViewModel, FragmentAllSongBinding>
        implements AllSongAdapter.CallBack {

    private AllSongAdapter mAllSongAdapter;
    private MyMediaPlayerController mMediaPlayer;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ContentResolver contentResolver = requireActivity().getContentResolver();
                Uri songUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    songUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
                } else {
                    songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                Cursor songCursor = contentResolver.query(songUri, null, null, null, null);
                List<Song> mList = new ArrayList<>();

                if (songCursor != null && songCursor.moveToFirst()) {
                    int titleIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int artistIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int albumIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                    int dataIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                    while (songCursor.moveToNext()) {
                        String title = songCursor.getString(titleIndex);
                        String artist = songCursor.getString(artistIndex);
                        String strUri = songCursor.getString(dataIndex);
                        String album = songCursor.getString(albumIndex);
                        mList.add(new Song(R.drawable.img_preview_song_home, title, artist, strUri));
                    }
                }
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAllSongAdapter.setListData(mList);
                    }
                });


                mMediaPlayer = MyMediaPlayerController.getInstance(requireContext());
                mMediaPlayer.setListSong(mList);

            }
        }
        );
        thread.start();
    }

    @Override
    public void initView() {
        setUpRcv();
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