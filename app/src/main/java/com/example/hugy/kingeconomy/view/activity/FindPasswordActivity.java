package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.FindPasswordContact;
import com.example.hugy.kingeconomy.presenter.FindPasswordPresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity<FindPasswordContact.Presenter>implements FindPasswordContact.View {

    @BindView(R.id.toobar_text)
    TextView toobarText;
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
    private FindPasswordContact.Presenter findPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public FindPasswordContact.Presenter initPresenter() {
        findPasswordPresenter = new FindPasswordPresenter(this);
        return findPasswordPresenter;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.btn_next, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                ToastUtils.showToast(this, "验证码已发送");
                break;
            case R.id.btn_next:
                ToastUtils.showToast(this, "下一步");
                startActivity(new Intent(FindPasswordActivity.this, FindPasswordSubmitActivity.class));
                break;
        }
    }

}
