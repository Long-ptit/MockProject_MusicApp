package com.example.mockproject_music.model;

public class Song {
    private int previewResource;
    private String name;
    private String singer;
    private String dataSource;


    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public int getPreviewResource() {
        return previewResource;
    }

    public void setPreviewResource(int previewResource) {
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

    public Song(int previewResource, String name, String singer, String dataSource) {
        this.previewResource = previewResource;
        this.name = name;
        this.singer = singer;
        this.dataSource = dataSource;
    }
}
