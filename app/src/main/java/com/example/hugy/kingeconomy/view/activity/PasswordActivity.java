package com.example.hugy.kingeconomy.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_password_new)
    EditText etPasswordNew;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.iv_show_text)
    ImageView ivShowText;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    public void initView() {

    }

    @OnClick({R.id.toolbar, R.id.et_password_new,  R.id.et_password_again, R.id.iv_show_text, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.et_password_new:
                break;
            case R.id.et_password_again:
                break;
            case R.id.iv_show_text:
                break;
            case R.id.btn_register:
                break;
        }
    }

}
