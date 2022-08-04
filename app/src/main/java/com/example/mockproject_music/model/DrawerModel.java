package com.example.mockproject_music.model;

public class DrawerModel {
    private String category;
    private int srcImage;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }

    public DrawerModel(String category, int srcImage) {
        this.category = category;
        this.srcImage = srcImage;
    }
}
