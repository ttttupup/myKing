package com.example.hugy.kingeconomy.bean;

/**
 * Created by hugy on 2018/4/3.
 */

public class GuessLikeBean {
    private String image;
    private String name;
    private String attribute;

    public GuessLikeBean() {
    }

    public GuessLikeBean(String image, String name, String attribute) {
        this.image = image;
        this.name = name;
        this.attribute = attribute;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
