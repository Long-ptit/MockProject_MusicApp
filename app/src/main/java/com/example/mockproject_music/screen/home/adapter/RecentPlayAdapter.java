package com.example.mockproject_music.screen.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvRecentlyPlayedBinding;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class RecentPlayAdapter extends RecyclerView.Adapter<RecentPlayAdapter.ViewHolder> {
    //  private List<Student> mListData;
    private final Context mContext;
    private List<Song> mListData;
    private CallBackRecentPlay mCallBack;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Song> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public RecentPlayAdapter(Context context) {
        this.mContext = context;
        mListData = new ArrayList<>();
    }

    public void setListener( CallBackRecentPlay callBackRecentPlay) {
        this.mCallBack = callBackRecentPlay;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvRecentlyPlayedBinding itemView =
                ItemRcvRecentlyPlayedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mListData.get(position);

        holder.mBinding.tvName.setText(song.getName());
        holder.mBinding.tvAuthor.setText(song.getSinger());
        holder.mBinding.imgPlay.setOnClickListener(v -> {
            mCallBack.onClickSongRecent(position);
        });
        //Glide.with(mContext).load(song.getPreviewResource()).into(holder.mBinding.imgContent);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvRecentlyPlayedBinding mBinding;

        public ViewHolder(@NonNull ItemRcvRecentlyPlayedBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBackRecentPlay {
        void onClickSongRecent(int position);
    }


}