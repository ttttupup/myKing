package com.example.hugy.kingeconomy.bean;

import java.io.Serializable;

/**
 * Created by hugy on 2018/4/3.
 */

public class GuessLikeBean implements Serializable{
    //楼盘特色
    private String buildingFeature;
    //楼盘装修
    private String buildingRenovation;
    //特色看房服务
    private String houseFeature;
    //项目名称
    private String name;
    //项目id
    private String id;
    //楼盘类型
    private String type;
    //展示图
    private String url;

    public String getBuildingFeature() {
        return buildingFeature;
    }

    public void setBuildingFeature(String buildingFeature) {
        this.buildingFeature = buildingFeature;
    }

    public String getBuildingRenovation() {
        return buildingRenovation;
    }

    public void setBuildingRenovation(String buildingRenovation) {
        this.buildingRenovation = buildingRenovation;
    }

    public String getHouseFeature() {
        return houseFeature;
    }

    public void setHouseFeature(String houseFeature) {
        this.houseFeature = houseFeature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
