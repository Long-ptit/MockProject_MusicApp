package com.example.mockproject_music.screen.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject_music.databinding.ItemRcvHostRecommendBinding;
import com.example.mockproject_music.model.Song;

import java.util.ArrayList;
import java.util.List;

public class HotRecommendAdapter extends RecyclerView.Adapter<HotRecommendAdapter.ViewHolder> {
  //  private List<Student> mListData;
    private final Context mContext;
    private List<Song> mListData;


    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<Song> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public HotRecommendAdapter(Context context) {
        this.mContext = context;
        mListData = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRcvHostRecommendBinding itemView =
                ItemRcvHostRecommendBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mListData.get(position);

        holder.mBinding.tvName.setText(song.getName());
        holder.mBinding.tvAuthor.setText(song.getSinger());

        //Glide.with(mContext).load(song.getPreviewResource()).into(holder.mBinding.imgContent);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRcvHostRecommendBinding mBinding;

        public ViewHolder(@NonNull ItemRcvHostRecommendBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

//    public interface CallBack {
//        void onClickStudent(Student student);
//    }

}