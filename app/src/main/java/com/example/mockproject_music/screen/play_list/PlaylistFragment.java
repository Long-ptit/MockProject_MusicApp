package com.example.mockproject_music.screen.play_list;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mockproject_music.R;
import com.example.mockproject_music.screen.play_list.adapter.PlaylistCategoryAdapter;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentPlaylistBinding;
import com.example.mockproject_music.screen.main.MainViewModel;


public class PlaylistFragment extends BaseFragment<MainViewModel, FragmentPlaylistBinding> {

    private PlaylistCategoryAdapter mCategoryAdapter;

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

        mCategoryAdapter = new PlaylistCategoryAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(
                getContext(),
                2
        );
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mCategoryAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_playlist;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    //inflater.inflate(R.layout.fragment_playlist
}