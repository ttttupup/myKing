package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReviewActivity extends BaseActivity {


    @BindView(R.id.tv_review)
    TextView tvReview;
    @BindView(R.id.tv_tip_message)
    TextView tvTipMessage;
    @BindView(R.id.bt_gohome)
    Button btGohome;
    @BindView(R.id.btn_gomine)
    Button btnGomine;
    TextView toolbarText;
    Toolbar toolbar;
    @BindView(R.id.iv_iamge)
    ImageView ivIamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.include_bar);
        toolbarText = toolbar.findViewById(R.id.toobar_text);
        tvReview.setText(getIntent().getStringExtra("status"));
        toolbarText.setText("审核资料");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @OnClick({R.id.bt_gohome, R.id.btn_gomine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_gohome:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.btn_gomine:
                startActivity(new Intent(this, MyInfoActivity.class));
                break;
        }
    }
}
