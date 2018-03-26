package com.example.hugy.kingeconomy.view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.adapter.HomeMenuAdapter;
import com.example.hugy.kingeconomy.adapter.TestGridView;
import com.example.hugy.kingeconomy.bean.Icon;
import com.example.hugy.kingeconomy.bean.Test;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_home);
        SliderLayout banner = (SliderLayout)findViewById(R.id.sliderLayout);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.mipmap.banner);
        file_maps.put("Big Bang Theory",R.mipmap.banner_2);
        file_maps.put("Big Bang Theory",R.mipmap.banner_3);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            banner.addSlider(textSliderView);
        }
        banner.setPresetTransformer(SliderLayout.Transformer.Accordion);
        banner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        banner.setCustomAnimation(new DescriptionAnimation());
        banner.setDuration(5000);
        banner.addOnPageChangeListener(this);
        List<Test> list = new ArrayList<Test>();
        GridView viewById2 = findViewById(R.id.gv_icon_function);
        List<Icon> iconList = new ArrayList<>();
        Icon icon1 = new Icon("接任务",R.mipmap.icon_green);
        Icon icon2 = new Icon("找楼盘",R.mipmap.icon_orange);
        Icon icon3 = new Icon("报备",R.mipmap.icon_blue);
        Icon icon4 = new Icon("上客",R.mipmap.icon_red);
        iconList.add(icon1);
        iconList.add(icon2);
        iconList.add(icon3);
        iconList.add(icon4);
        viewById2.setAdapter(new TestGridView(iconList,this));
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
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(this,slider.getBundle().get("extra") + "" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // Toast.makeText(this,"onPageS" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {
        //Toast.makeText(this,"onPageSelect" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Toast.makeText(this,"onPageScroll" , Toast.LENGTH_SHORT).show();
    }
}
