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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mList;
    private Context mContext;

    public TaskAdapter(List<Task> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // 实例化展示的view
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_task, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mList.get(position);
        if (null != task) {
            holder.imageView.setImageResource(R.mipmap.image_2);
            holder.taskDescribe.setText(task.getName());
            holder.taskMoney.setText(task.getAmount());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView taskDescribe, taskMoney;
        Button acceptTask;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_task_img);
            taskDescribe = itemView.findViewById(R.id.tv_task_describe);
            taskMoney = itemView.findViewById(R.id.tv_task_money);
            acceptTask = itemView.findViewById(R.id.btn_accept_task);
        }
    }
}
