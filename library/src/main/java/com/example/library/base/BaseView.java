package com.example.library.base;


/**
  * view基类
  * Created by hugy on 2018/3/27
  *
  */
public interface BaseView {

    /**
     * 显示加载
     *
     */
    void showLoadingDialog();

    /**
     * 关闭加载
     */
    void dismissLoadingDialog();

    /**
     * 显示错误
     */
    void showError();
}
