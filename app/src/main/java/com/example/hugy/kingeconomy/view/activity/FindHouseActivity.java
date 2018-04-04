package com.example.hugy.kingeconomy.view.activity;

import android.bluetooth.le.AdvertisingSetParameters;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.IconBean;
import com.example.hugy.kingeconomy.bean.Task;
import com.example.hugy.kingeconomy.utils.ToastUtils;
import com.example.hugy.kingeconomy.view.adapter.CityListAdapter;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.NoticeAdapter;
import com.example.hugy.kingeconomy.view.popupwindow.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindHouseActivity extends AppCompatActivity {

    @BindView(R.id.toobar_text)
    TextView toobarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cut_line)
    View cutLine;
    @BindView(R.id.rd_s_1)
    RadioButton rdS1;
    @BindView(R.id.rg_select)
    RadioGroup rgSelect;
    @BindView(R.id.cut_line_1)
    View cutLine1;
    @BindView(R.id.frame_select_city)
    FrameLayout frameSelectCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_house);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.rd_s_1)
    public void onViewClicked() {
//        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
//                .setView(R.layout.popup_common)
//                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT)
//                .setBackGroundLevel(0.5f)
//                .setViewOnclickListener((view, layoutResId) -> ToastUtils.showToast(FindHouseActivity.this,"1112www"))
//                .setOutsideTouchable(true)
//                .create();
//        popupWindow.showAsDropDown(frameSelectCity);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_common, null, false);
        PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //不设置，返回键就不能返回
        popWindow.setBackgroundDrawable(new ColorDrawable(-00000));//popWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor((v, event) -> {
            // 这里如果返回true的话，touch事件将被拦截
            // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            return false;
        });
        popWindow.update();
        RecyclerView viewById = view.findViewById(R.id.list);
        List<Task> data = new ArrayList<Task>();
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        data.add(new Task("杭州"));
        CityListAdapter adapter = new CityListAdapter(data);
        adapter.setmOnRecyclerItemClickListener((view1, position) -> ToastUtils.showToast(this,"weizhi"+position));
        viewById.setAdapter(adapter);
        viewById.setLayoutManager(new GridLayoutManager(this, 3));
        viewById.addItemDecoration(new CommonItemDecoration(this, R.drawable.shape_recy));
        popWindow.showAsDropDown(view);
    }
}
