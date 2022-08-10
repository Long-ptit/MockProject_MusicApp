package com.example.mockproject_music.screen.detail_album;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentDetailAlbumBinding;
import com.example.mockproject_music.screen.detail_album.adapter.DetailAlbumAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;


public class DetailAlbumFragment extends BaseFragment<MainViewModel, FragmentDetailAlbumBinding> {

    private DetailAlbumAdapter mDetailAlbumAdapter;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {
        binding.imgBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(binding.getRoot());
            navController.navigateUp();
        });
    }

    @Override
    public void initView() {
        setUpRcv();
    }

    private void setUpRcv() {
        mDetailAlbumAdapter = new DetailAlbumAdapter(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mDetailAlbumAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_album;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }
}