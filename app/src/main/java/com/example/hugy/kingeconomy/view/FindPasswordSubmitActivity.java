package com.example.hugy.kingeconomy.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.presenter.LoginPresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordSubmitActivity extends BaseActivity<LoginContact.presenter> implements LoginContact.view {

    @BindView(R.id.et_password_one)
    EditText etPasswordOne;
    @BindView(R.id.et_password_two)
    EditText etPasswordTwo;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private LoginContact.presenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_submit);
        ButterKnife.bind(this);
    }

    @Override
    public LoginContact.presenter initPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        ToastUtils.showToast(this,"提交");
    }
}
