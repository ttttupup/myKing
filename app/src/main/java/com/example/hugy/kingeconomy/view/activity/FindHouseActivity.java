package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.FindHouseBean;
import com.example.hugy.kingeconomy.bean.FindHouseResultBean;
import com.example.hugy.kingeconomy.bean.HouseBean;
import com.example.hugy.kingeconomy.bean.HouseInfoBean;
import com.example.hugy.kingeconomy.bean.PageBean;
import com.example.hugy.kingeconomy.bean.SelectBean;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.constant.ServiceCodeConstant;
import com.example.hugy.kingeconomy.contact.FindHouseContact;
import com.example.hugy.kingeconomy.contact.FindPasswordContact;
import com.example.hugy.kingeconomy.presenter.FindHousePresenter;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.HouseListAdapter;
import com.example.hugy.kingeconomy.view.popupwindow.SelectCityPopupWindow;
import com.example.hugy.kingeconomy.view.popupwindow.SelectHouseOtherPopupWindow;
import com.example.hugy.kingeconomy.view.popupwindow.SelectHouseTypePopupWindow;
import com.example.hugy.kingeconomy.view.popupwindow.SelectPricePopupWindow;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class FindHouseActivity extends BaseActivity<FindHouseContact.Presenter> implements FindHouseContact.View {

    TextView toolbarText;
    Toolbar toolbar;
    @BindView(R.id.cut_line)
    View cutLine;
    @BindView(R.id.rg_select)
    RadioGroup rgSelect;
    @BindView(R.id.cut_line_1)
    View cutLine1;
    @BindView(R.id.frame_select_city)
    FrameLayout frameSelectCity;
    @BindView(R.id.rv_house_list)
    RecyclerView rvHouseList;
    @BindView(R.id.frame_house_list)
    FrameLayout frameHouseList;
    @BindView(R.id.rd_s_city)
    RadioButton rdSCity;
    @BindView(R.id.rd_s_price)
    RadioButton rdSPrice;
    @BindView(R.id.rd_s_type)
    RadioButton rdSType;
    @BindView(R.id.rd_s_other)
    RadioButton rdSOther;
    private FindHouseBean findHouseBean;
    private Realm mRealm;
    private UserInfoBean user;
    private String currentPage = "1";
    private String size = "20";
    private String totalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_house);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.include_toolbar);
        toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("找楼盘");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        initView();
    }

    @Override
    public FindHouseContact.Presenter initPresenter() {
        return new FindHousePresenter(this);
    }

    @Override
    public void initView() {
        mRealm = Realm.getDefaultInstance();
        user = mRealm.where(UserInfoBean.class).findFirst();
        findHouseBean = new FindHouseBean();
        findHouseBean.setCityId(user.getCityId());
        findHouseBean.setCurrent(currentPage);
        findHouseBean.setSize(size);
        mPresenter.getHouseList(user.getUuid(), findHouseBean);
        RefreshLayout refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                findHouseBean = new FindHouseBean();
                findHouseBean.setSize(size);
                findHouseBean.setCurrent(currentPage);
                findHouseBean.setCityId(user.getCityId());
                mPresenter.getHouseList(user.getUuid(), findHouseBean);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadMorePage(currentPage,totalPage);
                findHouseBean.setCurrent(currentPage);
                findHouseBean.setSize(size);
                mPresenter.getHouseList(user.getUuid(), findHouseBean);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }


    @OnClick({R.id.rd_s_city, R.id.rd_s_price, R.id.rd_s_type, R.id.rd_s_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rd_s_city:
                List<String> list = new ArrayList<>();
                list.add("杭州");
                list.add("杭州");
                list.add("杭州");
                list.add("扬州");
                list.add("北京");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                list.add("上海");
                SelectCityPopupWindow popupWindow = new SelectCityPopupWindow(this, list);
                popupWindow.showAsDropDown(frameSelectCity);
                break;
            case R.id.rd_s_price:
                SelectPricePopupWindow pricePopupWindow = new SelectPricePopupWindow(this, null);
                pricePopupWindow.showAsDropDown(frameSelectCity);
                break;
            case R.id.rd_s_type:
                List<SelectBean> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                List<String> list3 = new ArrayList<>();
                List<String> list4 = new ArrayList<>();
                list2.add("酒店1");
                list2.add("酒店2");
                list2.add("酒店3");
                list2.add("酒店4");
                list3.add("自住1");
                list3.add("自住2");
                list3.add("自住3");
                list3.add("自住4");
                list4.add("商铺1");
                list4.add("商铺2");
                list4.add("商铺3");
                list4.add("商铺4");
                list1.add(new SelectBean("自住", list2));
                list1.add(new SelectBean("其他", list3));
                list1.add(new SelectBean("投资", list4));
                SelectHouseTypePopupWindow typePopupWindow = new SelectHouseTypePopupWindow(this, list1);
                typePopupWindow.showAsDropDown(frameSelectCity);
                break;
            case R.id.rd_s_other:
                SelectHouseOtherPopupWindow otherPopupWindow = new SelectHouseOtherPopupWindow(this, null);
                otherPopupWindow.showAsDropDown(frameSelectCity);
                break;
        }
    }

    @Override
    public void showQueryFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void showSuccess(PageBean<HouseBean> bean) {
        List<HouseBean> rows = bean.getRows();
        HouseListAdapter houseListAdapter = new HouseListAdapter(R.layout.item_house_list, bean.getRows());
        houseListAdapter.setOnItemClickListener((adapter, view, position) -> {
            startActivity(new Intent(this,HouseDetailActivity.class));
        });
        houseListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HouseBean item = (HouseBean)adapter.getItem(position);
            mPresenter.collect(user.getUuid(),item.getId());
        });
        rvHouseList.setAdapter(houseListAdapter);
        rvHouseList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHouseList.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy));
        currentPage= bean.getCurrent();
        totalPage = bean.getTotal();
    }

    @Override
    public void updateCollect(String isCollect, String num) {
        if (ServiceCodeConstant.TRUE.equals(isCollect)){
            View viewById = findViewById(R.id.iv_house_collect);

        }else {

        }
    }

    public void loadMorePage(String currentPage, String totalPage) {
        int current = Integer.parseInt(currentPage);
        int total = Integer.parseInt(totalPage);
        if (current < total) {
            ++current;
            this.currentPage = current + "";
        } else {
            this.currentPage = totalPage;
        }
    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }

}
