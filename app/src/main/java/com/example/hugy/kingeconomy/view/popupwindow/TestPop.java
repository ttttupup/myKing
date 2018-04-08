package com.example.hugy.kingeconomy.view.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class TestPop  extends PopupWindow{

    private ListView lvParent;
    private ListView lvChild;
    private ParentAdapter pAdapter;
    private ChildAdapter childAdapter;
    private List<Select> selects;
    private List<String> childs;
    View conentView;
    private Context context;
    public TestPop(Activity context) {
        this.context = context;
        conentView = View.inflate(context, R.layout.layout_test_pop, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h / 2);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        init();
        fullSelect();
        pAdapter.notifyDataSetChanged();
        childAdapter.notifyDataSetChanged();
    }

    public void show(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2,0);
        } else {
            this.dismiss();
        }
    }

    public void init() {
        childs = new ArrayList<>();
        selects = new ArrayList<>();
        lvParent = (ListView) conentView.findViewById(R.id.lv_parent);
        lvChild = (ListView) conentView.findViewById(R.id.lv_child);
        pAdapter = new ParentAdapter();
        childAdapter = new ChildAdapter();
        lvParent.setAdapter(pAdapter);
        lvChild.setAdapter(childAdapter);
    }

    private void fullSelect() {
        for (int i = 0; i < 10; i++) {
            Select select = new Select();
            if(i==0){
                select.setIsCheck(true);
            }
            select.setParent("货物类型"+i);
            ArrayList<String> childs = new ArrayList<String>();
            for (int j = 0; j < 10; j++) {
                childs.add("child"+i+"-" + j);
            }
            select.setChilds(childs);
            selects.add(select);
        }
        if(selects.size()>0){
            childs = selects.get(0).getChilds();
        }
    }
    class ParentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return selects.size();
        }

        @Override
        public Object getItem(int position) {
            return selects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Select select = selects.get(position);
            final boolean isCheck = select.isCheck();
            convertView = View.inflate(context,R.layout.layout_test_list,null);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_test_text);
            View vParent = convertView.findViewById(R.id.layout_group);

            vParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Select select:selects) {
                        select.setIsCheck(false);
                    }
                    selects.get(position).setIsCheck(true);
                    childs = selects.get(position).getChilds();
                    pAdapter.notifyDataSetChanged();
                    childAdapter.notifyDataSetChanged();
                }
            });
            textView.setText(select.getParent());
            if(isCheck){
                convertView.setBackgroundColor(Color.rgb(180,180,180));
            }
            return convertView;
        }
    }
    class ChildAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return childs.size();
        }

        @Override
        public Object getItem(int position) {
            return childs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String child = childs.get(position);
            convertView = View.inflate(context,R.layout.layout_test_list,null);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_test_text);
            textView.setText(child);
            return convertView;
        }
    }
}
