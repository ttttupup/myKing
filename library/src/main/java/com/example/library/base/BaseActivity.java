package com.example.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.library.utils.ActivityManager;

/**
 * Created by hugy on 2018/3/13.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    public Context mContext;
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        mContext = this;
        mPresenter = initPresenter();
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理栈
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        //将当前activity移除管理栈
        ActivityManager.getAppInstance().removeActivity(this);
        if (mPresenter != null) {
            //在presenter中解绑释放view
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();

    public abstract void initView();

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showError() {

    }


}
