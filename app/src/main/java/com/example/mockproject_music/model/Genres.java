package com.example.mockproject_music.model;

public class Genres {
    private String category;
    private int numSong;
    private int imgResourceThumb;

    public int getImgResourceThumb() {
        return imgResourceThumb;
    }

    public void setImgResourceThumb(int imgResourceThumb) {
        this.imgResourceThumb = imgResourceThumb;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumSong() {
        return numSong;
    }

    public void setNumSong(int numSong) {
        this.numSong = numSong;
    }

    public Genres(String category, int numSong, int imgResourceThumb) {
        this.category = category;
        this.numSong = numSong;
        this.imgResourceThumb = imgResourceThumb;
    }
}
