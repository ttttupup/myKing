package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.hugy.kingeconomy.constant.ServiceCodeConstant;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.OrderBean;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.contact.OrderContact;
import com.example.hugy.kingeconomy.presenter.OrderPresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.MyOrderListAdapter;
import com.example.library.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class OrderActivity extends BaseActivity<OrderContact.Presenter> implements OrderContact.View {

    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rd_all)
    RadioButton rdAll;
    @BindView(R.id.rd_wait_come)
    RadioButton rdWaitCome;
    @BindView(R.id.rd_wait_deal)
    RadioButton rdWaitDeal;
    @BindView(R.id.rd_finish)
    RadioButton rdFinish;
    @BindView(R.id.rd_fail)
    RadioButton rdFail;
    @BindView(R.id.rv_order_list)
    RecyclerView rvOrderList;
    private Realm mRealm;
    private String currentPage = "1";
    private String size = "20";
    private String status = ServiceCodeConstant.ORDER_ALL;
    private UserInfoBean user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        initView();
    }

    @Override
    public OrderContact.Presenter initPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    public void initView() {
        toolbarText.setText("我的订单");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        user = mRealm.where(UserInfoBean.class).findFirst();
        if (null == user) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_ALL, currentPage, size);

        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.srl_refresh_layout);
        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getOrderList(user.getUuid(), user.getId(), status, "1", size);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPresenter.getOrderList(user.getUuid(), user.getId(), status, currentPage + "", size);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @OnClick({R.id.toolbar, R.id.rd_all, R.id.rd_wait_come, R.id.rd_wait_deal, R.id.rd_finish, R.id.rd_fail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.rd_all:
                mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_ALL, "1", size);
                break;
            case R.id.rd_wait_come:
                mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_STATUS_REPORT, "1", size);
                break;
            case R.id.rd_wait_deal:
                mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_STATUS_COME, "1", size);
                break;
            case R.id.rd_finish:
                mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_STATUS_SUCCESS, "1", size);
                break;
            case R.id.rd_fail:
                mPresenter.getOrderList(user.getUuid(), user.getId(), ServiceCodeConstant.ORDER_STATUS_FAIL, "1", size);
                break;
        }
    }

    @Override
    public void showList(List<OrderBean> list) {
        MyOrderListAdapter myOrderListAdapter = new MyOrderListAdapter(R.layout.item_order_detail, list, rvOrderList);
        rvOrderList.setAdapter(myOrderListAdapter);
        rvOrderList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvOrderList.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy_gray_10));
        rvOrderList.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("item点击");
                OrderBean order = (OrderBean) adapter.getItem(position);
                Intent intent = new Intent(OrderActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId",order.getId());
                startActivity(intent);
            }

            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderBean order = (OrderBean) adapter.getItem(position);
                if (view.getId() == R.id.tv_order_del) {
                    Logger.i("item子控件删除点击");
                } else if (view.getId() == R.id.tv_order_operate) {
                    Logger.i("item子控件点击");
                    if (ServiceCodeConstant.ORDER_STATUS_FAIL.equals(order.getDealStatus())) {
                        startActivity(new Intent(OrderActivity.this, ReportActivity.class));
                    }
                }

            }
        });
        rvOrderList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (myOrderListAdapter != null) {
                        myOrderListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public void showLoadDataFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void loadMorePage(String currentPage, String totalPage,String status) {
        int current = Integer.parseInt(currentPage);
        int total = Integer.parseInt(totalPage);
        if (current < total) {
            ++current;
            this.currentPage = current + "";
        } else {
            this.currentPage = totalPage;
        }
        this.status = status;

    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
