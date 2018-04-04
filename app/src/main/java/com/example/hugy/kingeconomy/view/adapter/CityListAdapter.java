package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.Task;

import java.util.List;

/**
 * 首页任务适配器
 * Created by hugy on 2018/3/26.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private List<Task> mList;
    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public CityListAdapter(List<Task> list) {
        this.mList = list;
    }

    public void setmOnRecyclerItemClickListener(OnRecyclerItemClickListener mOnRecyclerItemClickListener) {
        this.mOnRecyclerItemClickListener = mOnRecyclerItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_city_list, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnItemClickListener(mOnRecyclerItemClickListener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mList.get(position);
        if (null != task) {
            holder.city.setText(task.getName());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnRecyclerItemClickListener mOnRecyclerItemClickListener;
        TextView city;

        public ViewHolder(View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.tv_content_title);
        }

        public void setOnItemClickListener(OnRecyclerItemClickListener mOnRecyclerItemClickListener) {
            this.mOnRecyclerItemClickListener = mOnRecyclerItemClickListener;
        }
        @Override
        public void onClick(View v) {
            if (mOnRecyclerItemClickListener != null) {
                mOnRecyclerItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
