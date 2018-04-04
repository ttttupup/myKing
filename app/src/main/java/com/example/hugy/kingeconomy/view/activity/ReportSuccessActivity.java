package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.GuessLikeBean;
import com.example.hugy.kingeconomy.common.RecyclerViewLayoutManager;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.GuessLikeAdapter;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btnn_go_report)
    Button btnnGoReport;
    @BindView(R.id.btn_look_order)
    Button btnLookOrder;
    @BindView(R.id.rv_guess_like)
    RecyclerView rvGuessLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_success);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        List<GuessLikeBean> list = new ArrayList<>();
        list.add(new GuessLikeBean("","玉屏山A座","商铺"));
        list.add(new GuessLikeBean("","玉屏山B座","精装修"));
        list.add(new GuessLikeBean("","玉屏山C座","商铺"));
        list.add(new GuessLikeBean("","玉屏山D座","精装修"));
        rvGuessLike.setAdapter(new GuessLikeAdapter(list));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        rvGuessLike.setLayoutManager(linearLayoutManager);

        rvGuessLike.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy));
    }

    @OnClick({R.id.tv_name, R.id.tv_mobile, R.id.tv_message, R.id.btnn_go_report, R.id.btn_look_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.tv_mobile:
                break;
            case R.id.tv_message:
                break;
            case R.id.btnn_go_report:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.btn_look_order:
                break;
        }
    }
}
