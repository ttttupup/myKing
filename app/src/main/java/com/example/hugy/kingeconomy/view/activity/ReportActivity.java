package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hugy.kingeconomy.R;
import com.example.library.base.BaseActivity;
import com.example.library.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hugy on 2018/3/28.
 */

public class ReportActivity extends BaseActivity {


    @BindView(R.id.toobar_text)
    TextView toobarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.et_customer_name)
    EditText etCustomerName;
    @BindView(R.id.tv_come_time)
    TextView tvComeTime;
    @BindView(R.id.et_people_no)
    EditText etPeopleNo;
    @BindView(R.id.et_come_city)
    EditText etComeCity;
    @BindView(R.id.rg_come_type)
    RadioGroup rgComeType;
    @BindView(R.id.rg_lunch)
    RadioGroup rgLunch;
    @BindView(R.id.btn_add_customer)
    Button btnAddCustomer;
    @BindView(R.id.btn_delete_customer_1)
    Button btnDeleteCustomer1;
    @BindView(R.id.layout_delete_1)
    LinearLayout layoutDelete1;
    @BindView(R.id.btn_delete_customer_2)
    Button btnDeleteCustomer2;
    @BindView(R.id.layout_delete_2)
    LinearLayout layoutDelete2;
    @BindView(R.id.btn_delete_customer_3)
    Button btnDeleteCustomer3;
    @BindView(R.id.layout_delete_3)
    LinearLayout layoutDelete3;
    @BindView(R.id.btn_submit_report)
    Button btnSubmitReport;

    private OptionsPickerView projectPicker;
    private OptionsPickerView timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, ChooseUserActivity.class));
            return false;
        });

        List<String> options1Items = new ArrayList<String>();
        List<String> timeItems = new ArrayList<String>();
        options1Items.add("项目一");
        options1Items.add("项目二");
        options1Items.add("项目三");
        options1Items.add("项目四");
        timeItems.add("3月20");
        timeItems.add("3月21");
        timeItems.add("3月22");
        //项目选择器
        projectPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String tx = options1Items.get(options1);
            tvProjectName.setText(tx);
            tvProjectName.setTextColor(this.getResources().getColor(R.color.font_click));
        })
                .setCancelText("取消").setSubmitText("确认")
                .setTitleText("项目名称")
                .setTitleColor(this.getResources().getColor(R.color.font_gray))
                .setSubmitColor(this.getResources().getColor(R.color.font_no_click))
                .setCancelColor(this.getResources().getColor(R.color.font_no_click))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
        projectPicker.setPicker(options1Items);

        timePicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String tx = timeItems.get(options1);
            tvComeTime.setText(tx);
            tvComeTime.setTextColor(this.getResources().getColor(R.color.font_click));
        })
                .setCancelText("取消").setSubmitText("确认")
                .setTitleText("上客时间")
                .setTitleColor(this.getResources().getColor(R.color.font_gray))
                .setSubmitColor(this.getResources().getColor(R.color.font_no_click))
                .setCancelColor(this.getResources().getColor(R.color.font_no_click))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
        timePicker.setPicker(timeItems);

    }

    @OnClick({R.id.toolbar, R.id.btn_add_customer, R.id.tv_project_name, R.id.tv_come_time, R.id.btn_delete_customer_1,
            R.id.btn_delete_customer_2, R.id.btn_delete_customer_3, R.id.btn_submit_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.btn_add_customer:
                LinearLayout[] state = {layoutDelete1, layoutDelete2, layoutDelete3};
                for (int i = 0; i < state.length; i++) {
                    if (LinearLayout.GONE == state[i].getVisibility()) {
                        state[i].setVisibility(LinearLayout.VISIBLE);
                        break;
                    }
                }
                if (LinearLayout.VISIBLE == layoutDelete1.getVisibility() && LinearLayout.VISIBLE == layoutDelete2.getVisibility()
                        && LinearLayout.VISIBLE == layoutDelete3.getVisibility()) {
                    btnAddCustomer.setVisibility(Button.GONE);
                }
                break;
            case R.id.btn_delete_customer_1:
                //清空数据
                layoutDelete1.setVisibility(LinearLayout.GONE);
                if (Button.GONE == btnAddCustomer.getVisibility()) {
                    btnAddCustomer.setVisibility(Button.VISIBLE);
                }
                break;
            case R.id.btn_delete_customer_2:
                layoutDelete2.setVisibility(LinearLayout.GONE);
                if (Button.GONE == btnAddCustomer.getVisibility()) {
                    btnAddCustomer.setVisibility(Button.VISIBLE);
                }
                break;
            case R.id.btn_delete_customer_3:
                layoutDelete3.setVisibility(LinearLayout.GONE);
                if (Button.GONE == btnAddCustomer.getVisibility()) {
                    btnAddCustomer.setVisibility(Button.VISIBLE);
                }
                break;
            case R.id.tv_project_name:
                projectPicker.show();
                break;
            case R.id.tv_come_time:
                timePicker.show();
                break;
            case R.id.btn_submit_report:
                startActivity(new Intent(this,ReportSuccessActivity.class));
                break;
        }
    }


}
