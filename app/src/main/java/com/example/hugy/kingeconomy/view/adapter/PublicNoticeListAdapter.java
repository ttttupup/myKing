package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.IconBean;
import com.example.hugy.kingeconomy.bean.NoticeListBean;

import java.util.List;

/**
 * 公告适配器
 * Created by hugy on 2018/3/26.
 */

public class PublicNoticeListAdapter extends RecyclerView.Adapter<PublicNoticeListAdapter.ViewHolder> {
    private List<IconBean> mList;
    private Context mContext;

    public PublicNoticeListAdapter(List<IconBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_public_notice, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IconBean icon = mList.get(position);
        if (null != icon) {
            holder.title.setText(icon.getTitle());
            holder.readNumber.setText(icon.getReadNumber());
            holder.praiseNumber.setText(icon.getPraiseNumber());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, readNumber, praiseNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_public_notice_title);
            readNumber = itemView.findViewById(R.id.tv_read);
            praiseNumber = itemView.findViewById(R.id.tv_praise);
        }
    }
}
