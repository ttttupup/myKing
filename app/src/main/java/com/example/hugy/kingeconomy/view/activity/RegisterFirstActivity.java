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
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hugy.kingeconomy.constant.ServiceCodeConstant;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.contact.RegisterContact;
import com.example.hugy.kingeconomy.presenter.RegisterPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.CountDownTimerUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class RegisterFirstActivity extends BaseActivity<RegisterContact.Presenter> implements RegisterContact.View {


    @BindView(R.id.toobar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_input_mobile)
    EditText etInputMobile;
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
    @BindView(R.id.iv_register_mobile_clear)
    ImageView ivRegisterMobileClear;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);
        ButterKnife.bind(this);
        initView();
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public RegisterContact.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void initView() {
        toolbarText.setText("注册");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        etInputMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivRegisterMobileClear.setVisibility(View.VISIBLE);
                } else {
                    ivRegisterMobileClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.toobar_text, R.id.toolbar, R.id.et_input_mobile, R.id.et_verification_code,
            R.id.btn_send, R.id.rb_read, R.id.tv_protocol, R.id.btn_next_step, R.id.iv_register_mobile_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toobar_text:
                break;
            case R.id.iv_register_mobile_clear:
                etInputMobile.setText("");
                break;
            case R.id.toolbar:
                break;
            case R.id.et_input_mobile:
                break;
            case R.id.et_verification_code:

                break;
            case R.id.btn_send:

                if (check(true)) {
                    new CountDownTimerUtils(btnSend, 60000, 1000).start();
                    mPresenter.sendCode(ServiceCodeConstant.REGISTER_SEND_CODE,
                            etInputMobile.getText().toString().replaceAll("\\s*", ""));
                }
                break;
            case R.id.rb_read:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.btn_next_step:
                //注册下一步
                if (check(false)) {
                    Intent intent = new Intent(this, PasswordActivity.class);
                    intent.putExtra("mobile", etInputMobile.getText().toString());
                    intent.putExtra("vCode", etVerificationCode.getText().toString());
                    startActivity(intent);
                }
                break;
        }

    }

    /**
     * 参数校验
     */
    protected Boolean check(Boolean isSendCode) {
        String mobile = etInputMobile.getText().toString().replaceAll("\\s*", "");
        String code = etVerificationCode.getText().toString().replaceAll("\\s*", "");
        if (CommonUtils.isNullOrEmpty(mobile)) {
            ToastUtils.showToast(this, "请输入手机号");
            return false;
        }
        if (!CommonUtils.isPhone(mobile)) {
            ToastUtils.showToast(this, "无效手机号");
            return false;
        }
        if (!isSendCode) {
            if (CommonUtils.isNullOrEmpty(code)) {
                ToastUtils.showToast(this, "请输入验证码");
                return false;
            }
        }
        if (!rbRead.isChecked()) {
            ToastUtils.showToast(this, "请先勾选用户使用协议");
            return false;
        }
        return true;
    }


    @Override
    public void sendCodeSuccess() {
        ToastUtils.showToast(this, "手机验证码已发送成功，请注意查收");
    }

    @Override
    public void sendCodeFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}


