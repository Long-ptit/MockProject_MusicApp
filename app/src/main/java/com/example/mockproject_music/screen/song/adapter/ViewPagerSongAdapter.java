package com.example.mockproject_music.screen.song.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mockproject_music.screen.album.AlbumsFragment;
import com.example.mockproject_music.screen.allsong.AllSongFragment;
import com.example.mockproject_music.screen.artists.ArtistsFragment;
import com.example.mockproject_music.screen.genres.GenresFragment;
import com.example.mockproject_music.screen.play_list.PlaylistFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerSongAdapter extends FragmentStateAdapter {

    private List<Fragment> mListFragment;
    public ViewPagerSongAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        mListFragment = new ArrayList<>();
        mListFragment.add(new AllSongFragment());
        mListFragment.add(new PlaylistFragment());
        mListFragment.add(new AlbumsFragment());
        mListFragment.add(new ArtistsFragment());
        mListFragment.add(new GenresFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return mListFragment.size();
    }
}
