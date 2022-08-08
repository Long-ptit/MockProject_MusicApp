package com.example.mockproject_music.screen.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mockproject_music.databinding.ItemRcvDrawerBinding;
import com.example.mockproject_music.model.Drawer;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private List<Drawer> mListData;
    private final Context mContext;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Drawer> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public DrawerAdapter(Context context) {
        this.mContext = context;

        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvDrawerBinding itemView =
                ItemRcvDrawerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drawer settingModel = mListData.get(position);

        holder.mBinding.tvTitleDrawer.setText(settingModel.getCategory());
        Glide.with(mContext).load(settingModel.getSrcImage()).into(holder.mBinding.imgIconDrawer);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvDrawerBinding mBinding;

        public ViewHolder(@NonNull ItemRcvDrawerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

//    public interface CallBack {
//        void onClickStudent(Student student);
//    }

}