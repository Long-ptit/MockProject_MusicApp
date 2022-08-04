package com.example.mockproject_music.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final Context mContext;
    private HotRecommendAdapter mHotRecommendAdapter;
    private PlaylistAdapter mPlaylistAdapter;
    public HotRecommendAdapter getHotRecommendAdapter() {
        return mHotRecommendAdapter;
    }

    public PlaylistAdapter getPlaylistAdapter() {
        return mPlaylistAdapter;
    }

    public HomeAdapter(Context context) {
        this.mContext = context;
     //   mListData = new ArrayList<>();
        mHotRecommendAdapter = new HotRecommendAdapter(mContext);
        mPlaylistAdapter = new PlaylistAdapter(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvHomeBinding itemView =
                ItemRcvHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0: {
                holder.mBinding.tvTitle.setText("Hot recommend");
                holder.mBinding.rcv.setLayoutManager(new LinearLayoutManager(
                        mContext,
                        RecyclerView.HORIZONTAL,
                        false
                ));
                holder.mBinding.rcv.setAdapter(mHotRecommendAdapter);
                break;
            }
            case 1: {
                holder.mBinding.tvTitle.setText("Playlist");
                holder.mBinding.rcv.setLayoutManager(new LinearLayoutManager(
                        mContext,
                        RecyclerView.HORIZONTAL,
                        false
                ));
                holder.mBinding.rcv.setAdapter(mPlaylistAdapter);
                break;
            }
            case 2: {
                holder.mBinding.tvTitle.setText("Recently Played");
                break;
            }

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvHomeBinding mBinding;

        public ViewHolder(@NonNull ItemRcvHomeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

//    public interface CallBack {
//        void onClickStudent(Student student);
//    }

}