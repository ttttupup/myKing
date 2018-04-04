package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.bumptech.glide.request.RequestOptions;
import com.example.hugy.kingeconomy.Constant.RequestCodeConstant;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.contact.AuthenticationContact;
import com.example.hugy.kingeconomy.presenter.AuthenticationPresenter;
import com.example.hugy.kingeconomy.utils.CommonUtils;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.library.GlideApp;
import com.example.library.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    private Uri imgUri;
    private String[] imagePath = new String[2];
    private List<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);
        initView();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
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


    @Override
    public AuthenticationContact.presenter initPresenter() {
        return new AuthenticationPresenter(this);
    }

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


    @OnClick({R.id.rb_have_code, R.id.rb_no_code, R.id.btn_submit_broker_no_code,
            R.id.layout_no_code, R.id.btn_submit_broker, R.id.layout_have_code,
            R.id.iv_back_card, R.id.iv_front_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_have_code:
//                layoutNoCode.setVisibility(View.GONE);
//                layoutHaveCode.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_no_code:
//                layoutHaveCode.setVisibility(View.GONE);
//                layoutNoCode.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_submit_broker_no_code:
                break;
            case R.id.layout_no_code:
                break;
            case R.id.btn_submit_broker:
                break;
            case R.id.layout_have_code:
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
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, flag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCodeConstant.FRONT_PICTURE && resultCode == RESULT_OK) {
            RequestOptions requestOptions = new RequestOptions();
            GlideApp.with(this).load(imgUri).into(ivFrontCard);
            noCodeSubmit();
        } else if (requestCode == RequestCodeConstant.BACK_PICTURE && resultCode == RESULT_OK) {
            ivBackCard.setImageURI(imgUri);
        }

    }

    protected void noCodeSubmit() {
        String brokerName = etBrokerName.getText().toString().replaceAll("\\s*", "");
        String storeName = etStoreName.getText().toString().replaceAll("\\s*", "");
//        if (CommonUtils.isNullOrEmpty(brokerName)) {
//            ToastUtils.showToast(this, "请填写姓名");
//            return;
//        }
//        if (CommonUtils.isNullOrEmpty(storeName)) {
//            ToastUtils.showToast(this, "请填写门店名");
//            return;
//        }
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
                    .map(new Function<List<String>, Object>() {
                        @Override
                        public Object apply(List<String> strings) throws Exception {
                            Log.e("1111==", "压缩开始");
                            return Luban.with(AuthenticationActivity.this).ignoreBy(200).load(strings).get();
                        }
                    })
                    .observeOn(Schedulers.io())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Log.e("1111==", "回调成功");
                            List<File> list = (List<File>) o;
                            File file0 = list.get(0);
                            File file1 = list.get(1);
//                            mPresenter.authentic();
                        }
                    });
            Log.e("1111==", "先开始");

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
        }
        if (CommonUtils.isNullOrEmpty(code)) {
            ToastUtils.showToast(this, "请填写门店编码");
        }
    }

}

