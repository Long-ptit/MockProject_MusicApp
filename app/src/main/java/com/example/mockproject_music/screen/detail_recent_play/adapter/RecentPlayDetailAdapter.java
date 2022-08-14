package com.example.mockproject_music.screen.detail_recent_play.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvAllSongBinding;
import com.example.mockproject_music.databinding.ItemRcvRecentlyPlayedBinding;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class RecentPlayDetailAdapter extends RecyclerView.Adapter<RecentPlayDetailAdapter.ViewHolder> {
    private final Context mContext;
    private List<Song> mListData;
    private CallBackRecentPlayDetail mCallback;



    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Song> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public RecentPlayDetailAdapter(Context context, CallBackRecentPlayDetail callBackRecentPlayDetail) {
        this.mCallback = callBackRecentPlayDetail;
        this.mContext = context;
        mListData = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvAllSongBinding itemView =
                ItemRcvAllSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mListData.get(position);

        holder.mBinding.tvSongName.setText(song.getName());
        holder.mBinding.tvSongAuthor.setText(song.getSinger());
        holder.mBinding.imgPlay.setOnClickListener(v -> {
            mCallback.onClickItem(position);
        });
        Glide
                .with(mContext)
                .load(song.getPreviewResource())
                .into(holder.mBinding.imgThumb);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvAllSongBinding mBinding;

        public ViewHolder(@NonNull ItemRcvAllSongBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBackRecentPlayDetail {
        void onClickItem(int position);
    }


}