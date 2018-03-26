package com.example.hugy.kingeconomy.bean;

/**
 * Created by hugy on 2018/3/15.
 */

public class Icon {
    private String title;
    private int image;

    public Icon(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
