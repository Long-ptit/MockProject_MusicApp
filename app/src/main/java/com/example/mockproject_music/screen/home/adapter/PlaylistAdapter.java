package com.example.mockproject_music.screen.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvPlayListBinding;
import com.example.mockproject_music.model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
  //  private List<Student> mListData;
    private final Context mContext;
    private List<Playlist> mListData;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Playlist> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public PlaylistAdapter(Context context) {
        this.mContext = context;
        mListData = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvPlayListBinding itemView =
                ItemRcvPlayListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Playlist playlist = mListData.get(position);

        holder.mBinding.tvName.setText(playlist.getName());
        holder.mBinding.tvAuthor.setText(playlist.getAuthor());

        //Glide.with(mContext).load(song.getPreviewResource()).into(holder.mBinding.imgContent);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvPlayListBinding mBinding;

        public ViewHolder(@NonNull ItemRcvPlayListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }


}