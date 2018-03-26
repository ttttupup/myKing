package com.example.hugy.kingeconomy.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.utils.CountDownTimerUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFirstActivity extends BaseActivity<LoginContact.presenter> implements LoginContact.view {


    @BindView(R.id.toobar_text)
    TextView toobarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_input_mobile)
    EditText etInputMobile;
    @BindView(R.id.iv_clear_text)
    ImageView ivClearText;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.rb_read)
    RadioButton rbRead;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    public LoginContact.presenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
    }


    @OnClick({R.id.toobar_text, R.id.toolbar, R.id.et_input_mobile, R.id.iv_clear_text, R.id.et_verification_code, R.id.btn_send, R.id.rb_read, R.id.tv_protocol, R.id.btn_next_step})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toobar_text:
                break;
            case R.id.toolbar:
                break;
            case R.id.et_input_mobile:
                break;
            case R.id.iv_clear_text:
                break;
            case R.id.et_verification_code:
                break;
            case R.id.btn_send:
                new CountDownTimerUtils(btnSend,60000,1000).start();
                break;
            case R.id.rb_read:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.btn_next_step:
                break;
        }
    }

}


