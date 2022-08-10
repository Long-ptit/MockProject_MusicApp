package com.example.mockproject_music.screen.album.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvAlbumBinding;
import com.example.mockproject_music.databinding.ItemRcvAllSongBinding;
import com.example.mockproject_music.databinding.ItemRcvPlaylistTopBinding;
import com.example.mockproject_music.model.Album;
import com.example.mockproject_music.model.Setting;

import java.util.ArrayList;
import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<Album> mListData;
    private final Context mContext;
    private CallBackAlbum mCallBack;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Album> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public AlbumAdapter(Context context, CallBackAlbum callBack) {
        this.mContext = context;
        mCallBack = callBack;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvAlbumBinding itemView =
                ItemRcvAlbumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = mListData.get(position);
        holder.mBinding.tvName.setText(album.getName());
        holder.mBinding.tvArtist.setText(album.getArtist());
        holder.itemView.setOnClickListener(v -> {
            mCallBack.onClickAlbum(album);
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvAlbumBinding mBinding;

        public ViewHolder(@NonNull ItemRcvAlbumBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBackAlbum {
        void onClickAlbum(Album album);
    }

}