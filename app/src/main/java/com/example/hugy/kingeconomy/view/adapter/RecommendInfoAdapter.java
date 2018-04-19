package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.RecommendInfo;
import com.example.library.GlideApp;

import java.util.List;

/**
 * 首页推荐信息适配器
 * Created by hugy on 2018/3/26.
 */

public class RecommendInfoAdapter extends BaseQuickAdapter<RecommendInfo, BaseViewHolder> {
    public RecommendInfoAdapter(int layoutResId, @Nullable List<RecommendInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendInfo item) {
        helper.setText(R.id.tv_recommend_name, item.getName())
                .setText(R.id.tv_recommend_type, item.getType())
                .setText(R.id.tv_recommend_per, item.getAmount())
                .setText(R.id.tv_recommend_price, item.getPrice())
                .setText(R.id.tv_recommend_collect,item.getCollectNum())
                .setImageResource(R.id.iv_recommend_img,R.mipmap.banner_2)
                .addOnClickListener(R.id.iv_collect)
                .addOnClickListener(R.id.iv_recommend_img);
//        GlideApp.with(mContext).load(R.mipmap.banner_2).into((ImageView) helper.getView(R.id.iv_recommend_img));
    }


}
