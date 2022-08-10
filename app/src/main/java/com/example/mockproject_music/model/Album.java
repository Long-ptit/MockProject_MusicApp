package com.example.mockproject_music.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private Uri uriImagePreview;
    private List<Song> listSong;

    public Album(String name, String artist) {
        listSong = new ArrayList<>();
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Song> getListSong() {
        return listSong;
    }

    public void setListSong(List<Song> listSong) {
        this.listSong = listSong;
    }

    public Uri getUriImagePreview() {
        return uriImagePreview;
    }

    public void setUriImagePreview(Uri uriImagePreview) {
        this.uriImagePreview = uriImagePreview;
    }
}
