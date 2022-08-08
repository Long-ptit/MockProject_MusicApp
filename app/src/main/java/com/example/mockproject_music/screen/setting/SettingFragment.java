package com.example.mockproject_music.screen.setting;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.databinding.FragmentSettingBinding;
import com.example.mockproject_music.model.Setting;
import com.example.mockproject_music.screen.main.MainActivity;
import com.example.mockproject_music.screen.setting.adapter.SettingAdapter;

import java.util.List;


public class SettingFragment extends BaseFragment<SettingViewModel, FragmentSettingBinding> {

    private SettingAdapter mSettingAdapter;

    @Override
    public void observerLiveData() {
        viewModel.getListMutableData().observe(getViewLifecycleOwner(), new Observer<List<Setting>>() {
            @Override
            public void onChanged(List<Setting> settingModels) {
                mSettingAdapter.setListData(settingModels);
            }
        });
    }

    @Override
    public void initListener() {
        binding.imgIconDrawer.setOnClickListener(v -> {
            // temporary
            ((MainActivity) getActivity()).openDrawer();
        });
    }

    @Override
    public void initView() {
        setUpRcv();
        viewModel.getDataSetting();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
    }


    private void setUpRcv() {
        mSettingAdapter = new SettingAdapter(requireContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mSettingAdapter);
    }

}