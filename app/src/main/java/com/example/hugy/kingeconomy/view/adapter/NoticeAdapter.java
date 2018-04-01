package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.IconBean;

import java.util.List;

/**
 * 首页任务适配器
 * Created by hugy on 2018/3/26.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private List<IconBean> mList;
    private Context mContext;

    public NoticeAdapter(List<IconBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notice, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IconBean icon = mList.get(position);
        if (null != icon) {
            holder.noticeIcon.setImageResource(icon.getImage());
            holder.noticeType.setText(icon.getTitle());
            holder.noticeContent.setText(icon.getDesc());
            holder.noticeNumber.setText(icon.getNumber());
            if (null != icon.getNumber()){
                holder.noticeNumber.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView noticeIcon;
        TextView noticeType, noticeContent, noticeNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            noticeType = itemView.findViewById(R.id.tv_notice_type);
            noticeContent = itemView.findViewById(R.id.tv_notice_content);
            noticeNumber = itemView.findViewById(R.id.tv_notice_number);
            noticeIcon = itemView.findViewById(R.id.iv_notice_icon);
        }
    }
}
