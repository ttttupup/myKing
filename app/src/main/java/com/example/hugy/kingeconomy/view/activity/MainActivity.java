package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.TestContact;
import com.example.hugy.kingeconomy.presenter.TestPresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.popupwindow.TestPop;
import com.example.library.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<TestContact.presenter> implements TestContact.view {

    private Button btn_Options;
    private Button btn_CustomOptions;
    private Button btn_CustomTime;

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar;
    private OptionsPickerView pvOptions, pvCustomOptions, pvNoLinkOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar viewById3 = findViewById(R.id.toolbar);
//        viewById3.inflateMenu(R.menu.menu);
        View viewById = findViewById(R.id.testID);
        View jump = findViewById(R.id.testJump);
        View ban = findViewById(R.id.testJumpBan);
        View a = findViewById(R.id.testJumpA);
        View viewById1 = findViewById(R.id.tv_webtest);
        ban.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
        viewById.setOnClickListener(v -> mPresenter.getData());
        jump.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        a.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AuthenticationActivity.class)));
        viewById1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
        View viewById2 = findViewById(R.id.tv_baobei);
        viewById2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReportActivity.class)));

        View viewById4 = findViewById(R.id.tv_order);
        viewById4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrderActivity.class)));
        View viewById5 = findViewById(R.id.tv_order_detail);
        viewById5.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrderDetailActivity.class)));
        View viewById6 = findViewById(R.id.tv_notice_entry);
        viewById6.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NoticeActivity.class)));

        View viewById3 = findViewById(R.id.tv_notice_find);
        viewById3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FindHouseActivity.class)));
        Button btn_Time = (Button) findViewById(R.id.btn_Time);
        btn_Options = (Button) findViewById(R.id.btn_Options);


        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(MainActivity.this, (date, v) -> {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ToastUtils.showToast(MainActivity.this, format.format(date) + "");

        })
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("时间")//标题文字
//                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(this.getResources().getColor(R.color.font_gray))//标题文字颜色font_no_click
                .setSubmitColor(this.getResources().getColor(R.color.font_no_click))//确定按钮文字颜色
                .setCancelColor(this.getResources().getColor(R.color.font_no_click))//取消按钮文字颜色
//                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .isDialog(true)//是否显示为对话框样式
                .build();
        btn_Time.setOnClickListener(v -> pvTime.show());

        //条件选择器
//        OptionsPickerView pvOptions = new OptionsPickerBuilder(MainActivity.this, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
////                //返回的分别是三个级别的选中位置
////                String tx = options1Items.get(options1).getPickerViewText()
////                        + options2Items.get(options1).get(option2)
////                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
////                tvOptions.setText(tx);
//            }
//        }).build();
//        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
        List<String>options1Items = new ArrayList<String>();
        options1Items.add("项目一");
        options1Items.add("项目二");
        options1Items.add("项目三");
        options1Items.add("项目四");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1);
                ToastUtils.showToast(MainActivity.this,tx);
            }
        })
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleText("城市选择")
                .setTitleColor(this.getResources().getColor(R.color.font_gray))//标题文字颜色font_no_click
                .setSubmitColor(this.getResources().getColor(R.color.font_no_click))//确定按钮文字颜色
                .setCancelColor(this.getResources().getColor(R.color.font_no_click))//取消按钮文字颜色
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        btn_Options.setOnClickListener(v-> pvOptions.show());

        View viewById7 = findViewById(R.id.tv_test);
        View viewById8 = findViewById(R.id.test_layout);
        TestPop pop = new TestPop(this);
        viewById7.setOnClickListener(v->pop.show(viewById8));
    }

    @Override
    public TestContact.presenter initPresenter() {
        return new TestPresenter(this);

    }

    @Override
    public void initView() {

    }

    @Override
    public void setData() {

    }


}
