package com.example.hugy.kingeconomy.common;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hugy on 2018/4/3.
 */

public class RecyclerViewLayoutManager extends LinearLayoutManager {

    public RecyclerViewLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        View view = recycler.getViewForPosition(0);
        measureChild(view, widthSpec, heightSpec);
        int measuredWidth = View.MeasureSpec.getSize(widthSpec);
        int measuredHeight = view.getMeasuredHeight();
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
