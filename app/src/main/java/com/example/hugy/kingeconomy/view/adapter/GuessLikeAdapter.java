package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.GuessLikeBean;
import com.example.hugy.kingeconomy.bean.RecommendInfo;
import com.example.hugy.kingeconomy.bean.Task;
import com.example.library.GlideApp;

import java.util.List;

/**
 * 猜你喜欢适配器
 * Created by hugy on 2018/3/26.
 */

public class GuessLikeAdapter extends BaseQuickAdapter<GuessLikeBean, BaseViewHolder> {

    public GuessLikeAdapter(int layoutResId, @Nullable List<GuessLikeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuessLikeBean item) {
        helper.setText(R.id.tv_guess_project_name, item.getName())
                .setText(R.id.tv_guess_project_attr, item.getBuildingFeature());
//                .addOnClickListener(R.id.iv_guess_project_img);
        GlideApp.with(mContext).load(R.mipmap.banner_2).into((ImageView) helper.getView(R.id.iv_guess_project_img));
    }


}
