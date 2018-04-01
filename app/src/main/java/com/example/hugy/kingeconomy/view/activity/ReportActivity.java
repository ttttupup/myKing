package com.example.hugy.kingeconomy.view.activity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

/**
 * Created by hugy on 2018/3/28.
 */

public class ReportActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_add:
                ToastUtils.showToast(this, "菜单");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
