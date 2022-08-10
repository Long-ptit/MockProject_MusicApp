package com.example.mockproject_music.screen.detail_album.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvAlbumBinding;
import com.example.mockproject_music.databinding.ItemRcvDetailAlbumBinding;
import com.example.mockproject_music.model.Setting;

import java.util.ArrayList;
import java.util.List;

public class DetailAlbumAdapter extends RecyclerView.Adapter<DetailAlbumAdapter.ViewHolder> {
    private List<Setting> mListData;
    private final Context mContext;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Setting> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public DetailAlbumAdapter(Context context) {
        this.mContext = context;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvDetailAlbumBinding itemView =
                ItemRcvDetailAlbumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvDetailAlbumBinding mBinding;

        public ViewHolder(@NonNull ItemRcvDetailAlbumBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


}