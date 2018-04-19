package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.PhoneBean;
import com.example.hugy.kingeconomy.bean.ProjectNameBean;
import com.example.hugy.kingeconomy.bean.ProjectReportBean;
import com.example.hugy.kingeconomy.bean.ReportBean;
import com.example.hugy.kingeconomy.bean.ReportResultBean;
import com.example.hugy.kingeconomy.bean.ReportSuccessBean;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.contact.ReportContact;
import com.example.hugy.kingeconomy.presenter.ReportPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * Created by hugy on 2018/3/28.
 */

public class ReportActivity extends BaseActivity<ReportContact.Presenter> implements ReportContact.View {


    @BindView(R.id.toolbar_text)
    TextView toolbarText;
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
    @BindView(R.id.btn_signle_report)
    Button btnSignleReport;
    @BindView(R.id.btn_more_report)
    Button btnMoreReport;
    @BindView(R.id.hide)
    View hide;
    @BindView(R.id.et_lunch)
    EditText etLunch;
    @BindView(R.id.et_phone_header_1)
    EditText etPhoneHeader1;
    @BindView(R.id.et_phone_last_1)
    EditText etPhoneLast1;
    @BindView(R.id.et_phone_header_2)
    EditText etPhoneHeader2;
    @BindView(R.id.et_phone_last_2)
    EditText etPhoneLast2;
    @BindView(R.id.et_phone_header_3)
    EditText etPhoneHeader3;
    @BindView(R.id.et_phone_last_3)
    EditText etPhoneLast3;
    @BindView(R.id.et_phone_header_4)
    EditText etPhoneHeader4;
    @BindView(R.id.et_phone_last_4)
    EditText etPhoneLast4;
    @BindView(R.id.rb_way_1)
    RadioButton rbWay1;
    @BindView(R.id.rb_way_2)
    RadioButton rbWay2;
    @BindView(R.id.rb_way_3)
    RadioButton rbWay3;

    private OptionsPickerView projectPicker;
    private OptionsPickerView timePicker;
    private UserInfoBean user;
    private Realm mRealm;
    //项目id
    private String projectId;
    //预计上客时间
    private String boardingPlane;
    //项目名称
    private String projectName;
    //出行方式
    private String partWay;

    private ProjectReportBean projectReportBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        user = mRealm.where(UserInfoBean.class).findFirst();
        initView();
    }

    @Override
    public ReportContact.Presenter initPresenter() {
        return new ReportPresenter(this);
    }

    @Override
    public void initView() {
        toolbarText.setText("报备");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, ChooseUserActivity.class));
            return false;
        });
        rgComeType.setOnCheckedChangeListener((group, checkedId) -> {
            if (R.id.rb_way_1 == group.getCheckedRadioButtonId()) {
                partWay = "1";
            } else if (R.id.rb_way_2 == group.getCheckedRadioButtonId()) {
                partWay = "2";
            } else if (R.id.rb_way_3 == group.getCheckedRadioButtonId()) {
                partWay = "3";
            }


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


    }

    @OnClick({R.id.toolbar, R.id.btn_add_customer, R.id.tv_project_name, R.id.tv_come_time, R.id.btn_delete_customer_1,
            R.id.btn_delete_customer_2, R.id.btn_delete_customer_3, R.id.btn_submit_report, R.id.btn_signle_report,
            R.id.btn_more_report})
    public void onViewClicked(View view) {
        if (user == null) {
            ToastUtils.showToast(this, "用户信息过期，请重新登录");
            startActivity(new Intent(this, LoginActivity.class));
        }
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
                mPresenter.getProject(user.getUuid(), user.getStoreId());
                break;
            case R.id.tv_come_time:
                if (CommonUtils.isNullOrEmpty(projectId)) {
                    ToastUtils.showToast(this, "请先选择项目名称");
                    return;
                }
                mPresenter.getBoardingDate(user.getUuid(), projectId);
                break;
            case R.id.btn_signle_report:
                btnSignleReport.setTextColor(0xffffffff);
                btnSignleReport.setBackground(getResources().getDrawable(R.drawable.selector_btn_blue2white_left_radius));
                btnMoreReport.setBackground(getResources().getDrawable(R.drawable.selector_btn_white2blue_right_radius));
                break;
            case R.id.btn_more_report:
                btnSignleReport.setBackground(getResources().getDrawable(R.drawable.selector_btn_white2blue_left_radius));
                btnMoreReport.setBackground(getResources().getDrawable(R.drawable.selector_btn_blue2white_right_radius));
                btnMoreReport.setTextColor(0xffffffff);
                break;
            case R.id.btn_submit_report:
                if (check()) {
//                    startActivity(new Intent(this, ReportSuccessActivity.class));
                }

                break;
        }
    }

    private Boolean check() {
        String name = etCustomerName.getText().toString().replaceAll("\\s*", "");

        String city = etComeCity.getText().toString().replaceAll("\\s*", "");
        String peopleNo = etPeopleNo.getText().toString().replaceAll("\\s*", "");
        String lunchNo = etLunch.getText().toString().replaceAll("\\s*", "");
        String phoneHeader1 = etPhoneHeader1.getText().toString().replaceAll("\\s*", "");
        String phoneLast1 = etPhoneLast1.getText().toString().replaceAll("\\s*", "");
        String phoneHeader2 = etPhoneHeader2.getText().toString().replaceAll("\\s*", "");
        String phoneLast2 = etPhoneLast2.getText().toString().replaceAll("\\s*", "");
        String phoneHeader3 = etPhoneHeader3.getText().toString().replaceAll("\\s*", "");
        String phoneLast3 = etPhoneLast3.getText().toString().replaceAll("\\s*", "");
        String phoneHeader4 = etPhoneHeader4.getText().toString().replaceAll("\\s*", "");
        String phoneLast4 = etPhoneLast4.getText().toString().replaceAll("\\s*", "");

        if (CommonUtils.isNullOrEmpty(projectId)) {
            ToastUtils.showToast(this, "请选择项目名称");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(projectName)) {
            ToastUtils.showToast(this, "请选择项目名称");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(boardingPlane)) {
            ToastUtils.showToast(this, "请选择上客时间");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(name)) {
            ToastUtils.showToast(this, "请填写客户姓名");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(peopleNo)) {
            ToastUtils.showToast(this, "请填写出行人数");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(lunchNo)) {
            ToastUtils.showToast(this, "请填写用餐人数");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(city)) {
            ToastUtils.showToast(this, "请填写出发城市");
            return false;
        }
        if (rgComeType.getCheckedRadioButtonId() == -1) {
            ToastUtils.showToast(this, "请选择到访方式");
            return false;
        }
        if (CommonUtils.isNullOrEmpty(phoneHeader1)
                || CommonUtils.isNullOrEmpty(phoneLast1)) {
            ToastUtils.showToast(this, "请填写客户电话");
            return false;
        }
        List<PhoneBean> list = new ArrayList<>();
        PhoneBean bean = new PhoneBean();
        bean.setName(name);
        bean.setMissContacto(phoneHeader1 + "****" + phoneLast1);
        list.add(bean);
        ReportBean reportBean = new ReportBean();
        reportBean.setProjectName(projectName);
        reportBean.setProjectId(projectId);
        reportBean.setAppUserId(user.getId());
        reportBean.setBoardingPlane(boardingPlane);
        reportBean.setDepartureCity(city);
        reportBean.setPartPersonNum(peopleNo);
        reportBean.setPartWay(partWay);
        reportBean.setLunch_flag("0");
        projectReportBean = new ProjectReportBean();
        projectReportBean.setOrder(reportBean);
        projectReportBean.setList(list);
        mPresenter.projectReport(user.getUuid(),projectReportBean);
        return true;
    }

    @Override
    public void initTimePicker(List<String> list) {
        Logger.i("初始化时间选择器");
        timePicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String tx = list.get(options1);
            boardingPlane = tx;
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
        timePicker.setPicker(list);
        timePicker.show();
    }

    @Override
    public void initProjectPicker(List<ProjectNameBean> list) {
        List<String> projectList = new ArrayList<>();
        for (ProjectNameBean bean : list) {
            projectList.add(bean.getProjectName());
        }
        //项目选择器
        projectPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            ProjectNameBean tx = list.get(options1);
            tvProjectName.setText(tx.getProjectName());
            tvProjectName.setTextColor(this.getResources().getColor(R.color.font_click));
            projectId = tx.getProjectId();
            projectName = tx.getProjectName();
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
        projectPicker.setPicker(projectList);
        projectPicker.show();

    }

    @Override
    public void goSuccess(ReportResultBean data) {
        Intent intent = new Intent(this, ReportSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("reportSuccess", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showError() {
        super.showError();
        ToastUtils.showToast(this, "网络请求失败");
    }
}
