package com.example.hugy.kingeconomy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.Test;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by hugy on 2018/3/15.
 */

public class TestAdapter  extends BaseAdapter {
    private List<Test> mData;
    private Context mContext;

    public TestAdapter(List<Test> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Test test = (Test) getItem(position);
        //LayoutInflater
        convertView = LayoutInflater.from(mContext).inflate(R.layout.test_list_view, parent, false);
        TextView fruitImage = (TextView) convertView.findViewById(R.id.testa);
        TextView fruitName = (TextView) convertView.findViewById(R.id.testb);
        fruitImage.setText(test.getId());
        fruitName.setText(test.getName());
        return convertView;
    }
}