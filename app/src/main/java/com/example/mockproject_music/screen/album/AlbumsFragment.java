package com.example.mockproject_music.screen.album;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentAlbumsBinding;
import com.example.mockproject_music.screen.album.adapter.AlbumAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;


public class AlbumsFragment extends BaseFragment<MainViewModel, FragmentAlbumsBinding> {

    private AlbumAdapter mAlbumAdapter;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        setUpRcv();

    }

    private void setUpRcv() {
        mAlbumAdapter = new AlbumAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mAlbumAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp10);
        binding.rcv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_albums;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }
}