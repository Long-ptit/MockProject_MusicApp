package com.example.mockproject_music.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.example.mockproject_music.model.Album;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.util.Util;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {
    private Context mContext;
    private static AlbumRepository instance;

    public static AlbumRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (AlbumRepository.class) {
                instance = new AlbumRepository(context);
            }
        }
        return instance;
    }

    public AlbumRepository(Context context) {
        this.mContext = context;

    }

    public List<Album> getFakeAlbum() {
        List<Album> listAlbum = new ArrayList<>();
        listAlbum.add(new Album("History", "Michael Jackson"));
        listAlbum.add(new Album("Thriller", "Michael Jackson"));
        listAlbum.add(new Album("It Won't Be Soon. . ", "Maroon 5"));
        listAlbum.add(new Album("I Am... Yours", "Beyonce"));
        return listAlbum;


    }
}
