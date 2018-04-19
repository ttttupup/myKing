package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.FindPasswordSubmitContact;
import com.example.hugy.kingeconomy.presenter.FindPasswordSubmitPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordSubmitActivity extends BaseActivity<FindPasswordSubmitContact.Presenter> implements FindPasswordSubmitContact.View {

    @BindView(R.id.et_password_one)
    EditText etPasswordOne;
    @BindView(R.id.et_password_two)
    EditText etPasswordTwo;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_find_password_clear)
    ImageView ivFindPasswordClear;
    @BindView(R.id.iv_show_text)
    ImageView ivShowText;
    private String moblie;
    private String vcode;
    private Boolean showPwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_submit);
        ButterKnife.bind(this);
        toolbarText.setText("设置新密码");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        moblie = getIntent().getStringExtra("findMobile");
        vcode = getIntent().getStringExtra("findVcode");
        initView();
    }

    @Override
    public FindPasswordSubmitContact.Presenter initPresenter() {

        return new FindPasswordSubmitPresenter(this);
    }

    @Override
    public void initView() {
        etPasswordOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivFindPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    ivFindPasswordClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.btn_submit, R.id.iv_show_text, R.id.iv_find_password_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_find_password_clear:
                etPasswordOne.setText("");
                break;
            case R.id.iv_show_text:
                if (showPwd) {
                    ivShowText.setImageResource(R.mipmap.icon_eye_open);
                    etPasswordTwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPwd = false;
                } else {
                    ivShowText.setImageResource(R.mipmap.icon_eye);
                    etPasswordTwo.setTransformationMethod(PasswordTransformationMethod.getInstance().getInstance());
                    showPwd = true;
                }
                break;
            case R.id.btn_submit:
                if (check()){
                    mPresenter.updatePassword(moblie,etPasswordTwo.getText().toString().replaceAll("\\s*", ""), vcode);
                }
                break;
        }
    }

    /**
     * 参数校验
     */
    protected Boolean check() {
        String password = etPasswordOne.getText().toString().replaceAll("\\s*", "");
        String passwordAgain = etPasswordTwo.getText().toString().replaceAll("\\s*", "");
        if (CommonUtils.isNullOrEmpty(password)) {
            ToastUtils.showToast(this, "请输入密码");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(passwordAgain)) {
            ToastUtils.showToast(this, "请再次输入密码");
            return false;
        }
        if (!password.equals(passwordAgain)) {
            ToastUtils.showToast(this, "两次输入的密码不一样");
            return false;
        }
        return true;
    }
    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }

    @Override
    public void updatePasswordSuccess() {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
