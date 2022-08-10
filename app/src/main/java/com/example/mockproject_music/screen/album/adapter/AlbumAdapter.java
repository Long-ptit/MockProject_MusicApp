package com.example.mockproject_music.screen.album.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvAlbumBinding;
import com.example.mockproject_music.databinding.ItemRcvAllSongBinding;
import com.example.mockproject_music.databinding.ItemRcvPlaylistTopBinding;
import com.example.mockproject_music.model.Setting;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<Setting> mListData;
    private final Context mContext;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Setting> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public AlbumAdapter(Context context) {
        this.mContext = context;
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
//        SettingModel settingModel = mListData.get(position);
//
//        holder.mBinding.tvTitleSetting.setText(settingModel.getTitle());
//        Glide.with(mContext).load(settingModel.getSrcImage()).into(holder.mBinding.imgIconSetting);

//        if (position == mListData.size() - 1) holder.mBinding.diveder.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvAlbumBinding mBinding;

        public ViewHolder(@NonNull ItemRcvAlbumBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

//    public interface CallBack {
//        void onClickStudent(Student student);
//    }

}