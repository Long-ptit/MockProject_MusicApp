package com.example.mockproject_music.model;

public class Artists {
    private String name;
    private int totalAlbum;
    private int totalSong;
    private int imgThumb;

    public Artists(String name, int totalAlbum, int totalSong, int imgThumb) {
        this.name = name;
        this.totalAlbum = totalAlbum;
        this.totalSong = totalSong;
        this.imgThumb = imgThumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAlbum() {
        return totalAlbum;
    }

    public void setTotalAlbum(int totalAlbum) {
        this.totalAlbum = totalAlbum;
    }

    public int getTotalSong() {
        return totalSong;
    }

    public void setTotalSong(int totalSong) {
        this.totalSong = totalSong;
    }

    public int getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(int imgThumb) {
        this.imgThumb = imgThumb;
    }
}
