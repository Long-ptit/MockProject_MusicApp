package com.example.mockproject_music.screen.album;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mockproject_music.R;
import com.example.mockproject_music.base.BaseFragment;
import com.example.mockproject_music.common.SpaceItemDecoration;
import com.example.mockproject_music.databinding.FragmentAlbumsBinding;
import com.example.mockproject_music.model.Album;
import com.example.mockproject_music.screen.album.adapter.AlbumAdapter;
import com.example.mockproject_music.screen.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class AlbumsFragment extends BaseFragment<MainViewModel, FragmentAlbumsBinding>
        implements AlbumAdapter.CallBackAlbum {

    private static final String TAG = "MyLog";
    private AlbumAdapter mAlbumAdapter;

    @Override
    public void observerLiveData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        setUpRcv();

    }

    private void setUpRcv() {
        mAlbumAdapter = new AlbumAdapter(getContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcv.setLayoutManager(layoutManager);
        binding.rcv.setAdapter(mAlbumAdapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp10);
        //binding.rcv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        getFakeData();
    }

    private void getFakeData() {
        List<Album> listAlbum = new ArrayList<>();
        listAlbum.add(new Album("History", "Michael Jackson"));
        listAlbum.add(new Album("Thriller", "Michael Jackson"));
        listAlbum.add(new Album("It Won't Be Soon. . ", "Maroon 5"));
        listAlbum.add(new Album("I Am... Yours", "Beyonce"));

        mAlbumAdapter.setListData(listAlbum);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_albums;
    }

    @Override
    public void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public void onClickAlbum(Album album) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        navController.navigate(R.id.action_songFragment_to_detailAlbumFragment);
    }

    @Override
    public void onClickMore(View v) {
        Log.d(TAG, "onClickMore: ");
        PopupMenu pm = new PopupMenu(getContext(), v, Gravity.BOTTOM);
        pm.getMenuInflater().inflate(R.menu.popup_menu, pm.getMenu());
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        pm.show();
    }
}