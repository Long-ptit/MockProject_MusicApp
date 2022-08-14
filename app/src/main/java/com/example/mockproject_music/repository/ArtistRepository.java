package com.example.mockproject_music.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Artists;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private Context mContext;
    private static ArtistRepository instance;

    public static ArtistRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (ArtistRepository.class) {
                instance = new ArtistRepository(context);
            }
        }
        return instance;
    }

    public ArtistRepository(Context context) {
        this.mContext = context;

    }


    public List<Artists> getFakeArtist() {
        List<Artists> listArtist = new ArrayList<>();
        listArtist.add(new Artists("Beyonce", 4, 38, R.drawable.img_preview_song_home));
        listArtist.add(new Artists("Bebe Rexha", 2, 17, R.drawable.img_preview_song_home));
        listArtist.add(new Artists("Maroon 5", 4, 56, R.drawable.img_preview_song_home));
        listArtist.add(new Artists("Michael Jackson", 10, 112, R.drawable.img_preview_song_home));
        listArtist.add(new Artists("Queens", 3, 32, R.drawable.img_preview_song_home));
        return listArtist;
    }
}
