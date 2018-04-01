package com.example.hugy.kingeconomy.view.activity;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.fragment.HomePageFragment;
import com.example.hugy.kingeconomy.view.fragment.NoticeFragment;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

public class HomeActivity extends BaseActivity implements HomePageFragment.OnFragmentInteractionListener,NoticeFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏,状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        initView();
//        HomePageFragment homePageFragment = new HomePageFragment();
        NoticeFragment noticeFragment = new NoticeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.frame_home_page, homePageFragment);
        fragmentTransaction.add(R.id.frame_home_notice,noticeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        numberBadgeItem.setBorderWidth(4).setText("99");
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.icon_home, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.message, "消息").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.mine, "我的"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position) {
                ToastUtils.showToast(HomeActivity.this, position + "1");
            }

            @Override
            public void onTabUnselected(int position) {
                switch (position) {
                    case 1:
                        numberBadgeItem.hide();
                }
                ToastUtils.showToast(HomeActivity.this, position + "2");
            }

            @Override
            public void onTabReselected(int position) {
                ToastUtils.showToast(HomeActivity.this, position + "3");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        banner.startAutoCycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        banner.stopAutoCycle();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
