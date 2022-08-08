package com.example.mockproject_music.screen.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.WindowManager;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseActivity;
import com.example.mockproject_music.databinding.ActivityMainBinding;
import com.example.mockproject_music.databinding.ActivitySplashBinding;
import com.example.mockproject_music.screen.main.MainActivity;

public class SplashActivity extends BaseActivity<SplashViewModel, ActivitySplashBinding> {

    private static final String TAG = "MyLog";
    private static int SPLASH_TIME_OUT = 3000;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {
        handleDelay();
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void handleDelay() {
        mHandlerThread = new HandlerThread("Delay");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

//    private void transparentStatusbar2() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.bg_app));
//        }
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
//    }

    @Override
    protected void onDestroy() {
        mHandler = null;
        mHandlerThread.interrupt();
        super.onDestroy();
    }


}