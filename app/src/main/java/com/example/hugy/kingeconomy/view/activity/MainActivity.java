package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.view.popupwindow.CommonPopupWindow;
import com.example.hugy.kingeconomy.view.popupwindow.TestPop;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;
import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends BaseActivity {

    private Button btn_Options;
    private Button btn_CustomOptions;
    private Button btn_CustomTime;

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar;
    private OptionsPickerView pvOptions, pvCustomOptions, pvNoLinkOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AVLoadingIndicatorView avi = findViewById(R.id.avi);
        TextView text = findViewById(R.id.load);
        text.setOnClickListener(v -> avi.show());
        TextView text1 = findViewById(R.id.load1);
        text1.setOnClickListener(v -> avi.show());
//        Toolbar viewById3 = findViewById(R.id.toolbar);
//        viewById3.inflateMenu(R.menu.menu);
        View viewById = findViewById(R.id.testID);
        View jump = findViewById(R.id.testJump);
        View ban = findViewById(R.id.testJumpBan);
        View a = findViewById(R.id.testJumpA);
        View viewById1 = findViewById(R.id.tv_webtest);
        ban.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
//        viewById.setOnClickListener(v -> mPresenter.getData());
        viewById.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestDemoActivity.class)));
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
        View viewMap = findViewById(R.id.tv_map);
        viewMap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HouseDetailActivity.class)));
        View pic = findViewById(R.id.tv_pic);
        pic.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HousePictureActivity.class)));
        Button btn_Time = (Button) findViewById(R.id.btn_Time);
        btn_Options = (Button) findViewById(R.id.btn_Options);

        //模拟登陆
        Button testLogin = (Button) findViewById(R.id.portal);
        testLogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));


        View viewById7 = findViewById(R.id.tv_test);
        View viewById8 = findViewById(R.id.test_layout);
        TestPop pop = new TestPop(this);
        viewById7.setOnClickListener(v -> pop.show(viewById8));
        btn_Time.setOnClickListener(v -> {

            CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
                    .setView(R.layout.popup_common_bar)
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setBackGroundLevel(0.5f)
                    .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                        @Override
                        public void getChildView(View view, int layoutResId) {
//                        TextView tv_child = (TextView) view.findViewById(R.id.tv_child);
//                        tv_child.setText("我是子View");
                        }
                    })
                    .setOutsideTouchable(true)
                    .create();
            popupWindow.showAsDropDown(findViewById(R.id.main));


        });


    }

    @Override
    public BasePresenter initPresenter() {
        return null;

    }

    @Override
    public void initView() {

    }


}
