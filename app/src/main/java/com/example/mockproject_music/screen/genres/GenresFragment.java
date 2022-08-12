package com.example.mockproject_music.screen.genres;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.common.SpaceItemDecoration;
import com.example.mockproject_music.databinding.FragmentGenresBinding;
import com.example.mockproject_music.model.Genres;
import com.example.mockproject_music.screen.genres.adaper.GenresAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;

import java.util.List;

public class GenresFragment extends BaseFragment<GenresViewModel, FragmentGenresBinding> {

    private GenresAdapter mGenresAdapter;

    @Override
    public void observerLiveData() {
        viewModel.getListGenresFake().observe(getViewLifecycleOwner(), new Observer<List<Genres>>() {
            @Override
            public void onChanged(List<Genres> genres) {
                mGenresAdapter.setListData(genres);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        initRcv();
    }

    private void initRcv() {
        mGenresAdapter = new GenresAdapter(requireContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        int top = getResources().getDimensionPixelSize(R.dimen.dp12);
        int right = getResources().getDimensionPixelSize(R.dimen.dp12);
        binding.rcv.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 10));
        binding.rcv.setAdapter(mGenresAdapter);
        binding.rcv.setLayoutManager(layoutManager);
        viewModel.getDataFake();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_genres;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(GenresViewModel.class);
    }
}