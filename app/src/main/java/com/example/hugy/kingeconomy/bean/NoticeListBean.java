package com.example.hugy.kingeconomy.bean;

/**
 * Created by hugy on 2018/4/1.
 */

public class NoticeListBean {
    private  String title;
    private  String desc;

    public NoticeListBean(String title, String desc) {
        this.title = title;
        this.desc = desc;
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
}
