package com.example.hugy.kingeconomy.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.adapter.RecommendInfoAdapter;
import com.example.hugy.kingeconomy.adapter.TaskAdapter;
import com.example.hugy.kingeconomy.adapter.TestGridView;
import com.example.hugy.kingeconomy.bean.Icon;
import com.example.hugy.kingeconomy.bean.RecommendInfo;
import com.example.hugy.kingeconomy.bean.Task;
import com.example.hugy.kingeconomy.bean.Test;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private SliderLayout banner;
    private RecyclerView taskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏,状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        initView();
        //查询任务
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.setAmount("12.00%");
        task1.setName("转发任务");
        task2.setAmount("12.00%");
        task2.setName("预订任务");
        task3.setAmount("12.00%");
        task3.setName("阅读任务");
        List<Task> list = new ArrayList<Task>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        taskView = findViewById(R.id.rv_task);
        taskView.setAdapter(new TaskAdapter(list));
        taskView.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy));
        taskView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //查询推荐
        List<RecommendInfo> list1 = new ArrayList<>();
        RecommendInfo info = new RecommendInfo();
        RecommendInfo info1 = new RecommendInfo();
        info.setAmount("12%");
        info.setImg("");
        info.setName("玉屏山庄");
        info.setPrice("35000元/平");
        info.setType("商铺");
        info1.setAmount("12%");
        info1.setImg("");
        info1.setName("玉屏山庄");
        info1.setPrice("35000元/平");
        info1.setType("商铺");
        list1.add(info);
        list1.add(info1);
        RecyclerView recommendView = findViewById(R.id.rv_recommend);
        recommendView.setAdapter(new RecommendInfoAdapter(list1));
        recommendView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recommendView.addItemDecoration(new CommonItemDecoration(this,R.drawable.shape_recy_30));
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        banner = (SliderLayout) findViewById(R.id.sliderLayout);
        HashMap<String, Integer> bannerMap = new HashMap<String, Integer>();
        bannerMap.put("Hannibal", R.mipmap.banner);
        bannerMap.put("Big Bang Theory", R.mipmap.banner_2);
        bannerMap.put("Big Bang Theory", R.mipmap.banner_3);
        for (String name : bannerMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(slider -> ToastUtils.showToast(this, "banner点击事件"));
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            banner.addSlider(textSliderView);
        }
        banner.setPresetTransformer(SliderLayout.Transformer.Accordion);
        banner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        banner.setCustomAnimation(new DescriptionAnimation());
        banner.setDuration(5000);
        banner.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        List<Test> list = new ArrayList<Test>();
        GridView viewById2 = findViewById(R.id.gv_icon_function);
        List<Icon> iconList = new ArrayList<>();
        Icon icon1 = new Icon("接任务", R.mipmap.icon_green);
        Icon icon2 = new Icon("找楼盘", R.mipmap.icon_orange);
        Icon icon3 = new Icon("报备", R.mipmap.icon_blue);
        Icon icon4 = new Icon("上客", R.mipmap.icon_red);
        iconList.add(icon1);
        iconList.add(icon2);
        iconList.add(icon3);
        iconList.add(icon4);
        viewById2.setAdapter(new TestGridView(iconList, this));
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
}
