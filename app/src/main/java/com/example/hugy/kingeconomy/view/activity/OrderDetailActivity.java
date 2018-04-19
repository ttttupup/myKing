package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.OrderDetailResultBean;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.contact.OrderDetailContact;
import com.example.hugy.kingeconomy.presenter.OrderDetailPresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class OrderDetailActivity extends BaseActivity<OrderDetailContact.Presenter> implements OrderDetailContact.View {
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String orderId;
    private Realm mRealm;
    private UserInfoBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        user = mRealm.where(UserInfoBean.class).findFirst();
        initView();
    }

    @Override
    public OrderDetailContact.Presenter initPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    public void initView() {
        toolbarText.setText("订单详情");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        orderId = getIntent().getStringExtra("orderId");
        if (user != null) {
            mPresenter.getOrderDetail(user.getUuid(), orderId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (user == null) {
            ToastUtils.showToast(this, "账户登录信息过期，请重新登录");
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void showQueryFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void showSuccess(OrderDetailResultBean bean) {

    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }
}
