package com.example.mockproject_music.model;

public class Setting {
    private String title;
    private int srcImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }

    public Setting(String title, int srcImage) {
        this.title = title;
        this.srcImage = srcImage;
    }
}
