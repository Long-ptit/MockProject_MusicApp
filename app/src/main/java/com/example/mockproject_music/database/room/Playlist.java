package com.example.mockproject_music.database.room;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Playlist {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name_student")
    private String name;

    @ColumnInfo(name = "age_student")
    private int imgResourceThumb;

}
