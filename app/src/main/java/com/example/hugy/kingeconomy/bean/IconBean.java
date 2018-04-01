package com.example.hugy.kingeconomy.bean;

/**
 * Created by hugy on 2018/3/15.
 */

public class IconBean {
    private String title;
    private int image;
    private String desc;
    private String number;
    private String readNumber;
    private String praiseNumber;

    public IconBean(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public IconBean(String title, int image, String desc) {
        this.title = title;
        this.image = image;
        this.desc = desc;
    }

    public IconBean(String title, int image, String desc, String number) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.number = number;
    }

    public IconBean(String title, String readNumber, String praiseNumber) {
        this.title = title;
        this.readNumber = readNumber;
        this.praiseNumber = praiseNumber;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(String readNumber) {
        this.readNumber = readNumber;
    }

    public String getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(String praiseNumber) {
        this.praiseNumber = praiseNumber;
    }
}
