package com.example.mockproject_music.database.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDAO {

    @Insert
    void insertSong(SongDB songDB);

    @Query(value = "SELECT * from songdb")
    LiveData<List<SongDB>> getAllSongDB();

}
