package com.example.mockproject_music.screen.genres.adaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvAlbumBinding;
import com.example.mockproject_music.databinding.ItemRcvGenresBinding;
import com.example.mockproject_music.model.Album;
import com.example.mockproject_music.model.Genres;

import java.util.ArrayList;
import java.util.List;


public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<Genres> mListData;
    private final Context mContext;

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Genres> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public GenresAdapter(Context context) {
        this.mContext = context;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvGenresBinding itemView =
                ItemRcvGenresBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Genres genres = mListData.get(position);

        holder.mBinding.tvCategory.setText(genres.getCategory());
        holder.mBinding.tvNumSong.setText(genres.getNumSong() + " songs");
        Glide
                .with(mContext)
                .load(genres.getImgResourceThumb())
                .into(holder.mBinding.imgBg);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvGenresBinding mBinding;

        public ViewHolder(@NonNull ItemRcvGenresBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


}