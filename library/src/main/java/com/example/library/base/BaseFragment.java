package com.example.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * 基类Fragment
 *
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P presenter;
    //view是否创建
    private boolean isViewCreate = false;
    //view是否可见
    private boolean isViewVisible = false;
    public Context context;
    //是否第一次加载
    private boolean isFirst = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }

    /**
     * 懒加载
     * 让用户可见
     * 第一次加载
     */
    protected void firstLoad() {

    }

    /**
     * 懒加载
     * 让用户可见
     */
    protected void visibleToUser() {
        if (isFirst) {
            firstLoad();
            isFirst = false;
        }
    }


    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        isViewCreate = false;
        super.onDestroyView();
    }

    public abstract P initPresenter();


}
