package com.example.hugy.kingeconomy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.TestContact;
import com.example.hugy.kingeconomy.presenter.TestPresenter;
import com.example.library.base.BaseActivity;

public class MainActivity extends BaseActivity<TestContact.presenter> implements TestContact.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.testID);
        View jump = findViewById(R.id.testJump);
        View ban = findViewById(R.id.testJumpBan);
        View a = findViewById(R.id.testJumpA);
        ban.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
        viewById.setOnClickListener(v -> mPresenter.getData());
        jump.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        a.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AuthenticationActivity.class)));

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
