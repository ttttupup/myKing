package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.ReportResultBean;
import com.example.hugy.kingeconomy.bean.ReportSuccessBean;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.GuessLikeAdapter;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btnn_go_report)
    Button btnnGoReport;
    @BindView(R.id.btn_look_order)
    Button btnLookOrder;
    @BindView(R.id.rv_guess_like)
    RecyclerView rvGuessLike;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_users)
    LinearLayout layoutUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_success);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        toolbarText.setText("报备成功");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        //猜你喜欢
        Bundle extras = this.getIntent().getExtras();
        ReportResultBean reportSuccess = (ReportResultBean) extras.getSerializable("reportSuccess");
        GuessLikeAdapter guessLikeAdapter = null;
        guessLikeAdapter.setEmptyView(R.layout.layout_empty);
        guessLikeAdapter = new GuessLikeAdapter(R.layout.item_guess_like, reportSuccess.getProjectList());
        guessLikeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Object item = adapter.getItem(position);

        });
        rvGuessLike.setAdapter(guessLikeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        rvGuessLike.setLayoutManager(linearLayoutManager);
        rvGuessLike.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy));
        List<ReportSuccessBean> contacts = reportSuccess.getContacts();
        if (contacts.size() > 0) {
            LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_user, null);
            TextView name = inflate.findViewById(R.id.tv_name);
            TextView mobile = inflate.findViewById(R.id.tv_mobile);
            for (ReportSuccessBean bean : contacts) {
                name.setText(bean.getName());
                mobile.setText(bean.getMissContacto());
                layoutUsers.addView(inflate);
            }
            tvMessage.setText("报备成功，请于"+contacts.get(0).getBoardingEnd() +"前上客");
        }

    }

    @OnClick({ R.id.tv_message, R.id.btnn_go_report, R.id.btn_look_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_message:
                break;
            case R.id.btnn_go_report:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.btn_look_order:
                break;
        }
    }
}
