package com.example.mockproject_music.model;

public class Song {

    private int id;
    private String previewResource;
    private String name;
    private String singer;
    private String dataSource;
    private String albumName;
    private long duration;


    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public Song(int id, String previewResource, String name, String singer, String dataSource, String albumName, long duration) {
        this.id = id;
        this.previewResource = previewResource;
        this.name = name;
        this.singer = singer;
        this.dataSource = dataSource;
        this.albumName = albumName;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
