package com.example.mockproject_music.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvHomeBinding;
import com.example.mockproject_music.databinding.ItemRcvSettingBinding;
import com.example.mockproject_music.model.SettingModel;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {
    private List<SettingModel> mListData;
    private final Context mContext;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<SettingModel> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public SettingAdapter(Context context) {
        this.mContext = context;

        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvSettingBinding itemView =
                ItemRcvSettingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingModel settingModel = mListData.get(position);

        holder.mBinding.tvTitleSetting.setText(settingModel.getTitle());
        Glide.with(mContext).load(settingModel.getSrcImage()).into(holder.mBinding.imgIconSetting);

        if (position == mListData.size() - 1) holder.mBinding.diveder.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvSettingBinding mBinding;

        public ViewHolder(@NonNull ItemRcvSettingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

//    public interface CallBack {
//        void onClickStudent(Student student);
//    }

}