package com.example.hugy.kingeconomy.sevice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.example.hugy.kingeconomy.time.VerifyCodeTimer;

/**
 * Created by hugy on 2018/3/22.
 */

public class VerifyCodeService extends Service {
    private static Handler mHandler;
    private static VerifyCodeTimer mVerifyCodeTimer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mVerifyCodeTimer = new VerifyCodeTimer(10000, 1000, mHandler);
        mVerifyCodeTimer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置Handler
     */
    public static void setHandler(Handler handler) {
        mHandler = handler;
    }
}
