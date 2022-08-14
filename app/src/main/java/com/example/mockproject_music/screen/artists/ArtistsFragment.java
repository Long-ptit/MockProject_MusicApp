package com.example.mockproject_music.screen.artists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentArtistsBinding;
import com.example.mockproject_music.model.Artists;
import com.example.mockproject_music.screen.artists.adapter.ArtistAdapter;

import java.util.List;


public class ArtistsFragment extends BaseFragment<ArtistViewModel, FragmentArtistsBinding> {

    private ArtistAdapter mArtistAdapter;

    @Override
    public void observerLiveData() {
        viewModel.getListFake().observe(getViewLifecycleOwner(), new Observer<List<Artists>>() {
            @Override
            public void onChanged(List<Artists> artists) {
                mArtistAdapter.setListData(artists);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        setUpRcv();
        viewModel.getDataFake();
    }

    private void setUpRcv() {
        mArtistAdapter = new ArtistAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL,
                false
        );
        binding.rcv.setAdapter(mArtistAdapter);
        binding.rcv.setLayoutManager(layoutManager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_artists;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(ArtistViewModel.class);
    }
}