package com.example.hugy.kingeconomy.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hugy.kingeconomy.constant.RequestCodeConstant;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.AuthenticationContact;
import com.example.hugy.kingeconomy.presenter.AuthenticationPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.GlideApp;
import com.example.library.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;

/**
 * 经济人认证页面
 * Created by hugy on 2018/3/20
 */
public class AuthenticationActivity extends BaseActivity<AuthenticationContact.presenter> implements AuthenticationContact.view {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rb_have_code)
    RadioButton rbHaveCode;
    @BindView(R.id.rb_no_code)
    RadioButton rbNoCode;
    @BindView(R.id.btn_submit_broker_no_code)
    Button btnSubmitBrokerNoCode;
    @BindView(R.id.layout_no_code)
    LinearLayout layoutNoCode;
    @BindView(R.id.btn_submit_broker)
    Button btnSubmitBroker;
    @BindView(R.id.layout_have_code)
    LinearLayout layoutHaveCode;
    @BindView(R.id.iv_back_card)
    ImageView ivBackCard;
    @BindView(R.id.iv_front_card)
    ImageView ivFrontCard;
    @BindView(R.id.et_broker_name)
    EditText etBrokerName;
    @BindView(R.id.et_store_code)
    EditText etStoreCode;
    @BindView(R.id.et_store_name)
    EditText etStoreName;
    @BindView(R.id.rg_code_group)
    RadioGroup rgCodeGroup;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.layout_authentic)
    LinearLayout layoutAuthentic;
    @BindView(R.id.tv_select_city)
    TextView tvSelectCity;

    private Uri imgUri;
    private String[] imagePath = new String[2];
    private List<File> fileList;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        initView();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }


    @Override
    public AuthenticationContact.presenter initPresenter() {
        return new AuthenticationPresenter(this);
    }




    @Override
    public void initView() {
        toolbarText.setText("加入门店");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        rgCodeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_have_code:
                    layoutNoCode.setVisibility(View.GONE);
                    layoutHaveCode.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_no_code:
                    layoutHaveCode.setVisibility(View.GONE);
                    layoutNoCode.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }


    @OnClick({R.id.btn_submit_broker_no_code, R.id.btn_submit_broker, R.id.iv_back_card, R.id.iv_front_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit_broker_no_code:
                noCodeSubmit();
                break;
            case R.id.btn_submit_broker:
                haveCodeSubmit();
                break;
            case R.id.iv_front_card:
                initPopWindow(RequestCodeConstant.FRONT_PICTURE);
                break;
            case R.id.iv_back_card:
                initPopWindow(RequestCodeConstant.BACK_PICTURE);
                break;
        }
    }


    /**
     * 初始化弹窗
     *
     * @param flag
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initPopWindow(final int flag) {
        View view = LayoutInflater.from(this).inflate(R.layout.pupop_bottom, null, false);
        Button pictureBtn = view.findViewById(R.id.tv_take_picture);
        Button cancle = view.findViewById(R.id.tv_cancle);
        View root = findViewById(R.id.layout_authentic);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //不设置，返回键就不能返回
        popWindow.setBackgroundDrawable(new ColorDrawable(-00000));//popWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor((v, event) -> {
            // 这里如果返回true的话，touch事件将被拦截
            // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            return false;
        });
        popWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        //拍照
        pictureBtn.setOnClickListener(v -> {
            popWindow.dismiss();
            takeCamera(flag);
        });
        //取消
        cancle.setOnClickListener(v -> popWindow.dismiss());
    }


    /**
     * 调用系统相机拍照
     *
     * @param flag
     */
    private void takeCamera(int flag) {
        File file = new File(getExternalCacheDir(), flag + UUID.randomUUID().toString() + ".jpg");
        imgUri = Uri.fromFile(file);
        if (RequestCodeConstant.FRONT_PICTURE == flag) {
            imagePath[0] = imgUri.getPath();
        }
        if (RequestCodeConstant.BACK_PICTURE == flag) {
            imagePath[1] = imgUri.getPath();
        }
        Logger.e("开始启动相机");
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, flag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCodeConstant.FRONT_PICTURE && resultCode == RESULT_OK) {
            GlideApp.with(this).load(imgUri).into(ivFrontCard);
        } else if (requestCode == RequestCodeConstant.BACK_PICTURE && resultCode == RESULT_OK) {
            GlideApp.with(this).load(imgUri).into(ivBackCard);
        }

    }

    protected void noCodeSubmit() {
        String brokerName = etBrokerName.getText().toString().replaceAll("\\s*", "");
        String storeName = etStoreName.getText().toString().replaceAll("\\s*", "");
        if (CommonUtils.isNullOrEmpty(brokerName)) {
            ToastUtils.showToast(this, "请填写姓名");
            return;
        }
        if (CommonUtils.isNullOrEmpty(storeName)) {
            ToastUtils.showToast(this, "请填写门店名");
            return;
        }
        if (imagePath.length > 1 && imagePath != null) {
            if (imagePath[0] == null) {
                ToastUtils.showToast(this, "请上传名片正面");
                return;
            }
            if (imagePath[1] == null) {
                ToastUtils.showToast(this, "请上传名片反面");
                return;
            }
            List<String> pathList = new ArrayList<>();
            pathList.add(imagePath[0]);
            pathList.add(imagePath[1]);
            Flowable.just(pathList)
                    .observeOn(Schedulers.io())
                    .map((Function<List<String>, Object>) strings -> {
                        Logger.i("图片开始压缩");
                        return Luban.with(AuthenticationActivity.this).ignoreBy(200).load(strings).get();
                    })
                    .observeOn(Schedulers.io())
                    .subscribe(o -> {
                        Logger.i("图片压缩成功，开始上传");
                        List<File> list = (List<File>) o;
                        File file0 = list.get(0);
                        File file1 = list.get(1);
                        MultipartBody.Builder builder = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM);
//                                    .addFormDataPart(ParamKey.TOKEN, token);
                        RequestBody imageBodyFront = RequestBody.create(MediaType.parse("multipart/form-data"), file0);
                        RequestBody imageBodyBack = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                        MultipartBody.Part front = MultipartBody.Part.createFormData("face", file0.getName(), imageBodyFront);
                        MultipartBody.Part back = MultipartBody.Part.createFormData("opposite", file1.getName(), imageBodyBack);
                        mPresenter.authenticNoCode("", front, back);
                    });

        } else {
            ToastUtils.showToast(this, "请上传名片");
            return;
        }

    }

    protected void haveCodeSubmit() {
        String name = etBrokerName.getText().toString().replaceAll("\\s*", "");
        String code = etStoreCode.getText().toString().replaceAll("\\s*", "");
        if (CommonUtils.isNullOrEmpty(name)) {
            ToastUtils.showToast(this, "请填写姓名");
            return;
        }
        if (CommonUtils.isNullOrEmpty(code)) {
            ToastUtils.showToast(this, "请填写门店编码");
            return;
        }
        mPresenter.authentic("", name, code);
    }

    @Override
    public void authenticationSuccess(String status) {
        Intent intent = new Intent(this, ReviewActivity.class);
        if ("1".equals(status)) {
            intent.putExtra("status", "审核中");
        } else if ("2".equals(status)) {
            intent.putExtra("status", "审核成功");
        } else if ("2".equals(status)) {
            intent.putExtra("status", "审核未通过");
        }
        Logger.i("认证成功后跳转");
        startActivity(intent);
    }

    @Override
    public void authenticationFail(String errorMsg) {
        Logger.i("认证失败");
        if (null != errorMsg) {
            ToastUtils.showToast(this, errorMsg);
        } else {
            ToastUtils.showToast(this, "请求失败，请稍后再试");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

}

