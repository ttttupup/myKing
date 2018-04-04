package com.example.hugy.kingeconomy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.GuessLikeBean;
import com.example.hugy.kingeconomy.bean.Task;

import java.util.List;

/**
 * 猜你喜欢适配器
 * Created by hugy on 2018/3/26.
 */

public class GuessLikeAdapter extends RecyclerView.Adapter<GuessLikeAdapter.ViewHolder> {
    private List<GuessLikeBean> mList;
    private Context mContext;

    public GuessLikeAdapter(List<GuessLikeBean> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_guess_like, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
//        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
//        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GuessLikeBean bean = mList.get(position);
        if (null != bean) {
            holder.imageView.setImageResource(R.mipmap.banner_3);
            holder.name.setText(bean.getName());
            holder.attr.setText(bean.getAttribute());
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, attr;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_guess_project_img);
            name = itemView.findViewById(R.id.tv_guess_project_name);
            attr = itemView.findViewById(R.id.tv_guess_project_attr);
        }
    }
}
