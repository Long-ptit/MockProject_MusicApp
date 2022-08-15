package com.example.mockproject_music.screen.allsong.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.R;
import com.example.mockproject_music.databinding.ItemRcvAllSongBinding;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class AllSongAdapter extends RecyclerView.Adapter<AllSongAdapter.ViewHolder> {
    private List<Song> mListData;
    private final Context mContext;
    private CallBack mCallBack;

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Song> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public AllSongAdapter(Context context, CallBack callBack) {
        this.mContext = context;
        this.mCallBack = callBack;
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
        holder.mBinding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onClickMusic(song, position);
            }
        });
        holder.mBinding.tvSongName.setText(song.getName());
        holder.mBinding.tvSongAuthor.setText(song.getSinger());

        Glide
                .with(mContext)
                .load(song.getPreviewResource())
                .placeholder(R.drawable.img_preview_song_home)g
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

    public interface CallBack {
        void onClickMusic(Song song, int position);
    }

}