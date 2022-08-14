package com.example.mockproject_music.screen.artists;

import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentArtistsBinding;
import com.example.mockproject_music.model.Artists;
import com.example.mockproject_music.screen.artists.adapter.ArtistAdapter;

import java.util.List;


public class ArtistsFragment extends BaseFragment<ArtistViewModel, FragmentArtistsBinding>
    implements ArtistAdapter.CallBackArtis {

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
        mArtistAdapter = new ArtistAdapter(getContext(), this);
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


    @Override
    public void onClickMore(int position, Artists artists, View v) {
        PopupMenu pm = new PopupMenu(getContext(), v, Gravity.BOTTOM);
        pm.getMenuInflater().inflate(R.menu.popup_menu, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        pm.show();
    }
}