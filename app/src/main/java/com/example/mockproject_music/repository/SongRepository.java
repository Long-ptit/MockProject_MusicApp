package com.example.mockproject_music.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import com.example.mockproject_music.R;
import com.example.mockproject_music.model.Song;
import com.example.mockproject_music.player.MyMediaPlayerController;
import com.example.mockproject_music.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private Context mContext;
    private static SongRepository instance;

    public static SongRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (SongRepository.class) {
                instance = new SongRepository(context);
            }
        }
        return instance;
    }

    public SongRepository(Context context) {
        this.mContext = context;

    }



    public List<Song> getAllSongFromDevice() {
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri songUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            songUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }

        String where = MediaStore.Audio.Media.IS_MUSIC + " = 1";

        Cursor songCursor = contentResolver.query(songUri, null, where, null, null);
        List<Song> mList = new ArrayList<>();

        if (songCursor != null && songCursor.moveToFirst()) {

            int idIndex = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int idAlbumIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int titleIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int dataIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int durationIndex = songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);



            while (songCursor.moveToNext()) {
                int id = songCursor.getInt(idIndex);
                String title = songCursor.getString(titleIndex);
                String artist = songCursor.getString(artistIndex);
                String strUri = songCursor.getString(dataIndex);
                String album = songCursor.getString(albumIndex);
                long duration = songCursor.getLong(durationIndex);
                Log.d("ptit", "getDuration: " + Util.convertMilToMinutes(duration));

                Uri uriImage = Uri.parse("content://media/external/audio/albumart");
                String strSourceImg = ContentUris.withAppendedId(uriImage, songCursor.getLong(idAlbumIndex)).toString();
                mList.add(new Song(id ,strSourceImg, title, artist, strUri, album, duration, 0));
            }
        }
        return mList;


    }
}
