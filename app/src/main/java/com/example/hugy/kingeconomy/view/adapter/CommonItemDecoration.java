package com.example.hugy.kingeconomy.view.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * Created by hugy on 2018/3/26.
 */

public class CommonItemDecoration extends ItemDecoration {
    private Drawable mDrawable;

    /**
     * 传入上下文与资源ID
     *
     * @param context
     * @param resId
     */
    public CommonItemDecoration(Context context, int resId) {
        //获取 Drawable 对象
        mDrawable = ContextCompat.getDrawable(context, resId);
    }

    /**
     * 基本操作是留出分割线位置
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        //获取条目
        int position = parent.getChildAdapterPosition(view);
        //如果不是第一个条目，每个条目上方留出传入资源的高度用来绘制分割线
        if (position != 0) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }
    }

    /**
     * 绘制分割线
     *
     * @param canvas
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        Rect mRect = new Rect();
        //设置矩形左边位置
        mRect.left = parent.getPaddingLeft();
        //设置矩形右边位置
        mRect.right = parent.getWidth() - parent.getPaddingRight();
        int chilCount = parent.getChildCount();
        for (int i = 1; i < chilCount; i++) {
            //每个分割线的底部位置都是上一个条目的头部
            mRect.bottom = parent.getChildAt(i).getTop();
            //每个分割线的头部位置都是底部位置-资源的高度
            mRect.top = mRect.bottom - mDrawable.getIntrinsicHeight();
            //设置 Drawable 绘制的位置
            mDrawable.setBounds(mRect);
            mDrawable.draw(canvas);
        }
    }

}
