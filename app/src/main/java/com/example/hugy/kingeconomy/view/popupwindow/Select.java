package com.example.hugy.kingeconomy.view.popupwindow;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class Select {
    private String parent;
    private ArrayList<String> childs;
    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public ArrayList<String> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<String> childs) {
        this.childs = childs;
    }
}
