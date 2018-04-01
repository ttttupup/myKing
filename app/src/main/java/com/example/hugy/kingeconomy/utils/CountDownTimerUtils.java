package com.example.hugy.kingeconomy.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.hugy.kingeconomy.R;

/**
 * 倒计时
 * Created by hugy on 2018/3/22.
 */

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    public CountDownTimerUtils(TextView view, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = view;
    }

    /**
     * 倒计时期间会调用
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒"); //设置倒计时时间
        mTextView.setBackgroundResource(R.drawable.shape_verify_btn_press); //设置按钮为灰色，这时是不能点击的

        SpannableString spannableString = new SpannableString(mTextView.getText().toString()); //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    /**
     * 倒计时完成后调用
     */
    @Override
    public void onFinish() {
        mTextView.setText("重新获取");
        mTextView.setEnabled(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.selector_rectangle); //还原背景色
    }
}

