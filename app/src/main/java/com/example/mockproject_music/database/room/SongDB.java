package com.example.mockproject_music.database.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SongDB {

    @PrimaryKey
    private int id;
    private String previewResource;
    private String name;
    private String singer;
    private String dataSource;
    private String albumName;
    private long duration;
    private long createAt;

    public SongDB(int id, String previewResource, String name, String singer, String dataSource, String albumName, long duration, long createAt) {
        this.id = id;
        this.previewResource = previewResource;
        this.name = name;
        this.singer = singer;
        this.dataSource = dataSource;
        this.albumName = albumName;
        this.duration = duration;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreviewResource() {
        return previewResource;
    }

    public void setPreviewResource(String previewResource) {
        this.previewResource = previewResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
