package com.example.hugy.kingeconomy.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.service.carrier.CarrierService;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.fragment.HomePageFragment;
import com.example.hugy.kingeconomy.view.fragment.MineFragment;
import com.example.hugy.kingeconomy.view.fragment.NoticeFragment;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

public class HomeActivity extends BaseActivity implements HomePageFragment.OnFragmentInteractionListener, NoticeFragment.OnFragmentInteractionListener ,MineFragment.OnFragmentInteractionListener{

    private static final int HOME_PAGE = 0;
    private static final int NOTICE = 1;
    private static final int MINE = 2;
    private FragmentManager mFragmentManager;
    private NoticeFragment mNoticeFragment;
    private HomePageFragment mHomePageFragment;
    private MineFragment mMineFragment;
    private Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏,状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mWindow = getWindow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        initView();
        setDefaultFragment();
    }

    /**
     * 设置默认页
     */
    protected void setDefaultFragment() {
        mHomePageFragment = new HomePageFragment();
        mFragmentManager = getFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.frame_home_page, mHomePageFragment, "homePage");
        transaction.commit();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        numberBadgeItem.setBorderWidth(4).setText("99");
        BottomNavigationBar bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.home_2, "首页").setInactiveIconResource(R.mipmap.icon_home))
                .addItem(new BottomNavigationItem(R.mipmap.message_2, "消息").setInactiveIconResource(R.mipmap.message).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.mine_2, "我的").setInactiveIconResource(R.mipmap.mine))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int position) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                hideFragments(transaction);
                switch (position) {
                    case HOME_PAGE:
                        mWindow.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        if (mHomePageFragment != null) {
                            transaction.show(mHomePageFragment);
                        } else {
                            mHomePageFragment = new HomePageFragment();
                            transaction.add(R.id.frame_home_page, mHomePageFragment);
                        }
                        break;
                    case NOTICE:
                        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        if (mNoticeFragment != null) {
                            transaction.show(mNoticeFragment);
                        } else {
                            mNoticeFragment = new NoticeFragment();
                            transaction.add(R.id.frame_notice, mNoticeFragment);
                        }
                        break;
                    case MINE:
                        if (mMineFragment != null) {
                            transaction.show(mMineFragment);
                        } else {
                            mMineFragment = new MineFragment();
                            transaction.add(R.id.frame_mine, mMineFragment);
                        }
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
                switch (position) {
                    case NOTICE:
                        numberBadgeItem.hide();
                        break;
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 隐藏所有fragement
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mHomePageFragment != null) {
            transaction.hide(mHomePageFragment);
        }
        if (mNoticeFragment != null) {
            transaction.hide(mNoticeFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
