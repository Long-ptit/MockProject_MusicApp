package com.example.mockproject_music.base;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public abstract class BaseActivity<VM extends ViewModel,BINDING extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    protected BINDING binding;
    protected VM viewModel;


    public abstract void observerLiveData();

    public abstract void initListener();

    public abstract void initView();

    public abstract int getLayoutId();

    public abstract void initViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        setContentView(binding.getRoot());
        initViewModel();
        initView();
        observerLiveData();
        initListener();
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected boolean hasPermission(String permission) {
        Log.d(TAG, "hasPermission: ");
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "request P: ");
            requestPermissions(permissions, requestCode);
        }
    }
}
