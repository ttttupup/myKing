package com.example.hugy.kingeconomy.adapter;

import android.content.Context;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.Icon;

import java.util.List;

/**
 * Created by hugy on 2018/3/15.
 */

public class TestGridView extends BaseAdapter {
    private List<Icon> list;
    private Context context;

    public TestGridView(List<Icon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.home_grid_icon, null);

            // 初始化组件
            viewHolder.image = convertView.findViewById(R.id.iv_grid_img);
            viewHolder.title = convertView.findViewById(R.id.tv_grid_text);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 给组件设置资源
        Icon icon = list.get(position);
        viewHolder.image.setImageResource(icon.getImage());
        viewHolder.title.setText(icon.getTitle());
        return convertView;
    }
    class ViewHolder {
        public TextView title;
        public ImageView image;
    }
}
