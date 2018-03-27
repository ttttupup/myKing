package com.example.hugy.kingeconomy.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.RecommendInfo;
import com.example.hugy.kingeconomy.bean.Task;

import java.util.List;

/**
 * 首页推荐信息适配器
 * Created by hugy on 2018/3/26.
 */

public class RecommendInfoAdapter extends RecyclerView.Adapter<RecommendInfoAdapter.ViewHolder> {
    private List<RecommendInfo> mList;
    private Context mContext;

    public RecommendInfoAdapter(List<RecommendInfo> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecommendInfo recommendInfo = mList.get(position);
        if (null != recommendInfo) {
            holder.recommendImg.setImageResource(R.mipmap.banner_2);
            holder.recommendName.setText(recommendInfo.getName());
            holder.recommendType.setText(recommendInfo.getType());
            holder.recommendPer.setText(recommendInfo.getAmount());
            holder.recommendPrice.setText(recommendInfo.getPrice());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recommendImg;
        TextView recommendName, recommendType,recommendPer,recommendPrice;
        Button acceptTask;

        public ViewHolder(View itemView) {
            super(itemView);
            recommendImg = itemView.findViewById(R.id.iv_recommend_img);
            recommendName = itemView.findViewById(R.id.tv_recommend_name);
            recommendType = itemView.findViewById(R.id.tv_recommend_type);
            recommendPer = itemView.findViewById(R.id.tv_recommend_per);
            recommendPrice = itemView.findViewById(R.id.tv_recommend_price);
        }
    }
}
