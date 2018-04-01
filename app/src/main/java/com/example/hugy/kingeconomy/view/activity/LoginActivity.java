package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.presenter.LoginPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContact.Presenter> implements LoginContact.View {


    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_find_password)
    TextView tvFindPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public LoginContact.Presenter initPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void initView() {

    }

    @OnClick({R.id.et_mobile, R.id.et_password,
            R.id.tv_find_password, R.id.btn_login, R.id.btn_register, R.id.tv_other_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_mobile:
                break;

            case R.id.et_password:
                break;

            case R.id.tv_find_password:
                //忘记密码
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
                break;
            case R.id.btn_login:
                //登陆
                if (check()) {
                    login();
                }
                break;
            case R.id.btn_register:
                //注册
                startActivity(new Intent(LoginActivity.this, RegisterFirstActivity.class));
                break;
            case R.id.tv_other_login:
                break;

        }
    }

    /**
     * 登陆
     */
    public void login() {
        String mobile = etMobile.getText().toString().replaceAll("\\s*", "");
        String psw = etPassword.getText().toString().replaceAll("\\s*", "");
        mPresenter.login();
    }

    /**
     * 参数校验
     */
    protected Boolean check() {
        String mobile = etMobile.getText().toString();
        String psw = etPassword.getText().toString();
        if (!CommonUtils.isNullOrEmpty(mobile)) {
            if (!CommonUtils.isPhone(mobile)) {
                ToastUtils.showToast(this, "无效手机号");
                return false;
            }
        } else {
            ToastUtils.showToast(this, "请输入手机号");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(psw)) {
            ToastUtils.showToast(this, "请输入密码");
            return false;
        }
        return true;
    }

    @Override
    public void showLoginSuccess() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showLoginError() {

    }


}
