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
import com.example.hugy.kingeconomy.bean.NoticeListBean;

import java.util.List;

/**
 * 任务通知适配器
 * Created by hugy on 2018/3/26.
 */

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {
    private List<NoticeListBean> mList;
    private Context mContext;

    public NoticeListAdapter(List<NoticeListBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notice_list, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoticeListBean icon = mList.get(position);
        if (null != icon) {
            holder.title.setText(icon.getTitle());
            holder.desc.setText(icon.getDesc());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_content_title);
            desc = itemView.findViewById(R.id.tv_content_desc);
        }
    }
}
