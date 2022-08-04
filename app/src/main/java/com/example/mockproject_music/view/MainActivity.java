package com.example.mockproject_music.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.mockproject_music.R;
import com.example.mockproject_music.adapter.DrawerAdapter;
import com.example.mockproject_music.adapter.ScreenSlideAdapter;
import com.example.mockproject_music.databinding.ActivityMainBinding;
import com.example.mockproject_music.model.DrawerModel;
import com.example.mockproject_music.util.Util;
import com.example.mockproject_music.view.fragment.HomeFragment;
import com.example.mockproject_music.view.fragment.SettingFragment;
import com.example.mockproject_music.view.fragment.SongFragment;
import com.example.mockproject_music.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScreenSlideAdapter mPagerAdapter;
    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;
    private DrawerAdapter mDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //Util.transparentStatusbar(this, getWindow());
        initNavigation();
        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(mBinding.bottomView, host.getNavController());
        setUpRcv();
        setUpObservable();

        mViewModel.addDataDrawer();
    }

    private void setUpRcv() {
        mDrawerAdapter = new DrawerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
        );
        mBinding.rcvDrawer.setLayoutManager(layoutManager);
        mBinding.rcvDrawer.setAdapter(mDrawerAdapter);
    }

    private void setUpObservable() {
        mViewModel.getListDrawerMutableLiveData().observe(this, new Observer<List<DrawerModel>>() {
            @Override
            public void onChanged(List<DrawerModel> drawerModels) {
                mDrawerAdapter.setListData(drawerModels);
            }
        });
    }

    private void initNavigation() {

    }

    public void openDrawer() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.openDrawer(GravityCompat.START);
    }

//    private void initFragment() {
//        mPagerAdapter = new ScreenSlideAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        mPagerAdapter.addFragment(new HomeFragment());
//        mPagerAdapter.addFragment(new SongFragment());
//        mPagerAdapter.addFragment(new SettingFragment());
//        mBinding.viewPager.setAdapter(mPagerAdapter);
//    }
}