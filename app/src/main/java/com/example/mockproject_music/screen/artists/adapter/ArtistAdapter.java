package com.example.mockproject_music.screen.artists.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvAllSongBinding;
import com.example.mockproject_music.databinding.ItemRcvArtistsBinding;
import com.example.mockproject_music.model.Artists;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private List<Artists> mListData;
    private final Context mContext;
    private CallBackArtis mCallBack;
    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Artists> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public ArtistAdapter(Context context, CallBackArtis callBackArtis) {
        this.mContext = context;
        this.mCallBack = callBackArtis;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvArtistsBinding itemView =
                ItemRcvArtistsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artists artists = mListData.get(position);
        holder.mBinding.tvNameAlbum.setText(artists.getName());
        holder.mBinding.tvTotalSong.setText(artists.getTotalSong() + " Songs");
        holder.mBinding.tvTotalAlbum.setText(artists.getTotalAlbum() + " Albums");
        holder.mBinding.imgMore.setOnClickListener(v -> {
            mCallBack.onClickMore(position, artists, v);
        });
        Glide
                .with(mContext)
                .load(artists.getImgThumb())
                .into(holder.mBinding.imgThumb);

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvArtistsBinding mBinding;

        public ViewHolder(@NonNull ItemRcvArtistsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBackArtis {
        void onClickMore(int position, Artists artists, View v);
    }

}