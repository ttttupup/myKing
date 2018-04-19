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
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.contact.PasswordContact;
import com.example.hugy.kingeconomy.presenter.PasswordPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class PasswordActivity extends BaseActivity<PasswordContact.Presenter> implements PasswordContact.View {


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
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.iv_register_password_clear)
    ImageView ivRegisterPasswordClear;
    private Boolean showPwd = false;
    private String mobile;
    private String vCode;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        mobile = getIntent().getStringExtra("mobile");
        vCode = getIntent().getStringExtra("vCode");
        initView();
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public PasswordContact.Presenter initPresenter() {
        return new PasswordPresenter(this);
    }


    @Override
    public void initView() {
        toolbarText.setText("设置密码");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        etPasswordNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivRegisterPasswordClear.setVisibility(View.VISIBLE);
                } else {
                    ivRegisterPasswordClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.toolbar, R.id.et_password_new, R.id.et_password_again, R.id.iv_show_text,
            R.id.iv_register_password_clear, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.iv_register_password_clear:
                etPasswordNew.setText("");
                break;
            case R.id.iv_show_text:
                if (showPwd) {
                    ivShowText.setImageResource(R.mipmap.icon_eye_open);
                    etPasswordAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPwd = false;
                } else {
                    ivShowText.setImageResource(R.mipmap.icon_eye);
                    etPasswordAgain.setTransformationMethod(PasswordTransformationMethod.getInstance().getInstance());
                    showPwd = true;
                }
                break;
            case R.id.btn_register:
                if (check()) {
                    UserInfoBean first = mRealm.where(UserInfoBean.class).findFirst();
                    if(null == first){
                        ToastUtils.showToast(this,"登录过期，请重新登录");
                        startActivity(new Intent(this,LoginActivity.class));
                    }
                    mPresenter.register(first.getUuid(), mobile, etPasswordNew.getText().toString().replaceAll("\\s*", ""), vCode);
                }
                break;
        }
    }

    /**
     * 参数校验
     */
    protected Boolean check() {
        String password = etPasswordNew.getText().toString().replaceAll("\\s*", "");
        String passwordAgain = etPasswordAgain.getText().toString().replaceAll("\\s*", "");
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
    public void showSuccess() {
        startActivity(new Intent(this, AuthenticationActivity.class));
    }

    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求出错啦，请稍后再试");
    }
}
