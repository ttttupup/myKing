package com.example.hugy.kingeconomy.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.presenter.LoginPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContact.presenter> implements LoginContact.view {



    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.iv_clear_mobile)
    ImageView ivClearMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_clear_password)
    ImageView ivClearPassword;
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
    public LoginContact.presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void initView() {
        ivClearMobile.setOnClickListener(v -> etMobile.setText(null));
        ivClearPassword.setOnClickListener(v -> etPassword.setText(null));
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    ivClearMobile.setVisibility(View.VISIBLE);
                } else {
                    ivClearMobile.setVisibility(View.GONE);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    ivClearPassword.setVisibility(View.VISIBLE);
                } else {
                    ivClearPassword.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.et_mobile, R.id.iv_clear_mobile, R.id.et_password, R.id.iv_clear_password,
            R.id.tv_find_password, R.id.btn_login, R.id.btn_register, R.id.tv_other_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_mobile:
                break;
            case R.id.iv_clear_mobile:
                break;
            case R.id.et_password:
                break;
            case R.id.iv_clear_password:
                break;
            case R.id.tv_find_password:
                Toast.makeText(this, "forget", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, FindPasswordActivity.class));
                break;
            case R.id.btn_login:
                if (check()) {
                    login();
                }
                break;
            case R.id.btn_register:
                Toast.makeText(this, "register", Toast.LENGTH_LONG).show();
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
        String mobile = etMobile.getText().toString().replaceAll("\\s*","");
        String psw = etPassword.getText().toString().replaceAll("\\s*","");
        mPresenter.login();
    }

    /**
     * 参数校验
     *
     *
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
}
