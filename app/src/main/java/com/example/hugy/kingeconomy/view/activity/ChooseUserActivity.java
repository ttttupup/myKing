package com.example.hugy.kingeconomy.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.utils.ToastUtils;

public class ChooseUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        Toolbar bar = findViewById(R.id.toolbar);
        bar.inflateMenu(R.menu.menu_confirm);
        bar.setOnMenuItemClickListener(item1 -> {
            startActivity(new Intent(this, ReportActivity.class));
            return false;
        });
        RadioGroup group = findViewById(R.id.rg_users);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 30, 0, 30);//设置边距
        for (int i = 0; i < 5; i++) {
            RadioButton button1 = new RadioButton(this);
            button1.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            button1.setTextColor(getResources().getColor(R.color.font_radio));
            button1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            button1.setText("张三    138****1234 ");
            group.addView(button1, lp);
        }
        group.setOnCheckedChangeListener((group1, checkedId) -> {
            if (group.getChildCount() > 1) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    button.setCompoundDrawables(null, null, null, null);
                }
            }
            RadioButton button = findViewById(checkedId);
            Drawable drawable = getResources().getDrawable(R.mipmap.choose);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            button.setCompoundDrawables(null, null, drawable, null);
        });
    }
}
