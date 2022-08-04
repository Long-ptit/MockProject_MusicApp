package com.example.mockproject_music.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject_music.R;
import com.example.mockproject_music.adapter.SettingAdapter;
import com.example.mockproject_music.databinding.FragmentSettingBinding;
import com.example.mockproject_music.model.SettingModel;
import com.example.mockproject_music.viewmodel.MainViewModel;
import com.example.mockproject_music.viewmodel.SettingViewModel;

import java.util.List;


public class SettingFragment extends Fragment {

    private FragmentSettingBinding mBinding;
    private SettingViewModel mViewModel;
    private SettingAdapter mSettingAdapter;
    private CallBackListener callBackListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        if (getActivity() instanceof CallBackListener) {
            callBackListener = (CallBackListener) getActivity();
        }

        setUpOnclick();
        setUpObservable();
        setUpRcv();
        mViewModel.getDataSetting();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.imgIconDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackListener.onClickDrawer();
            }
        });
    }

    private void setUpOnclick() {
    }

    private void setUpRcv() {
        mSettingAdapter = new SettingAdapter(requireContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mBinding.rcv.setLayoutManager(layoutManager);
        mBinding.rcv.setAdapter(mSettingAdapter);
    }

    private void setUpObservable() {
        mViewModel.getListMutableData().observe(getViewLifecycleOwner(), new Observer<List<SettingModel>>() {
            @Override
            public void onChanged(List<SettingModel> settingModels) {
                mSettingAdapter.setListData(settingModels);
            }
        });
    }

    public interface CallBackListener {
       void onClickDrawer();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}