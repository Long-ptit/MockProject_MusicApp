package com.example.mockproject_music.model;

import java.util.List;

public class Playlist {
    private String name;
    private int imgResource;
    private List<Song> listSong;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public List<Song> getListSong() {
        return listSong;
    }

    public void setListSong(List<Song> listSong) {
        this.listSong = listSong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Playlist(String name, int imgResource, String author) {
        this.name = name;
        this.imgResource = imgResource;
        this.author = author;
    }
}
