package com.example.hugy.kingeconomy.view.activity;

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

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.constant.ServiceCodeConstant;
import com.example.hugy.kingeconomy.contact.FindPasswordContact;
import com.example.hugy.kingeconomy.presenter.FindPasswordPresenter;
import com.example.hugy.kingeconomy.presenter.RegisterPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity<FindPasswordContact.Presenter> implements FindPasswordContact.View {

    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.iv_find_mobile_clear)
    ImageView ivFindMobileClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        toolbarText.setText("找回密码");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        initView();
    }

    @Override
    public FindPasswordContact.Presenter initPresenter() {
        return new FindPasswordPresenter(this);
    }

    @Override
    public void initView() {
        etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivFindMobileClear.setVisibility(View.VISIBLE);
                } else {
                    ivFindMobileClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.btn_next, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_find_mobile_clear:
                etMobile.setText("");
                break;
            case R.id.btn_send:
                if (check(false)) {
                    mPresenter.sendCode(ServiceCodeConstant.FIND_PASSWORD_SEND_CODE,
                            etMobile.getText().toString().replaceAll("\\s*", ""));
                }
                break;
            case R.id.btn_next:
                if(check(true)){
                    Intent intent = new Intent(FindPasswordActivity.this, FindPasswordSubmitActivity.class);
                    intent.putExtra("findMobile",etMobile.getText().toString().replaceAll("\\s*", ""));
                    intent.putExtra("findVcode",etVerificationCode.getText().toString().replaceAll("\\s*", ""));
                    startActivity(intent);
                }
                break;
        }
    }

    private Boolean check(Boolean isCode) {
        String mobile = etMobile.getText().toString().replaceAll("\\s*", "");
        String vcode = etVerificationCode.getText().toString().replaceAll("\\s*", "");
        if (CommonUtils.isNullOrEmpty(mobile)) {
            ToastUtils.showToast(this, "请输入手机号");
            return false;
        }
        if (!CommonUtils.isPhone(mobile)) {
            ToastUtils.showToast(this, "无效手机号");
            return false;
        }
        if(isCode){
            if (CommonUtils.isNullOrEmpty(vcode)) {
                ToastUtils.showToast(this, "请输入验证码");
                return false;
            }
        }
        return true;
    }

    @Override
    public void sendCodeFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void sendCodeSuccess() {
        ToastUtils.showToast(this, "验证码已发送成功，请注意查收");
    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }

}
