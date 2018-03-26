package com.example.hugy.kingeconomy.time;

import android.os.CountDownTimer;
import android.os.Handler;

/**
 * Created by hugy on 2018/3/22.
 */

public class VerifyCodeTimer extends CountDownTimer {
    private static Handler mHandler;
    public static final int IN_RUNNING = 1001;
    public static int END_RUNNING = 1002;

    public VerifyCodeTimer(long millisInFuture, long countDownInterval, Handler handler) {
        super(millisInFuture, countDownInterval);
        this.mHandler = handler;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mHandler != null) {
            mHandler.obtainMessage(IN_RUNNING,
                    (millisUntilFinished / 1000) + "s 后重发").sendToTarget();
        }
    }

    @Override
    public void onFinish() {
        if (mHandler != null) {
            mHandler.obtainMessage(END_RUNNING, "重发").sendToTarget();
        }
    }
}
