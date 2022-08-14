package com.example.mockproject_music.database.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mockproject_music.model.Song;

import java.util.List;

@Dao
public interface SongDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSong(Song songDB);

    @Query(value = "SELECT * from song ORDER BY createAt DESC LIMIT 5")
    LiveData<List<Song>> getAllSongDB();

    @Query(value = "SELECT * from song ORDER BY createAt DESC LIMIT :quantity")
    LiveData<List<Song>> getSongRecentPlayWithQuantity(int quantity);


}
