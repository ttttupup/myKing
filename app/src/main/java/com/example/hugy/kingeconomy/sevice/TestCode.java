package com.example.hugy.kingeconomy.sevice;


import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hugy on 2018/3/22.
 */

public class TestCode extends Service {
    private CountDownTimer mCodeTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCodeTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 广播剩余时间
                broadcastUpdate("sending", millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                // 广播倒计时结束
                broadcastUpdate("finsh");
                // 停止服务
                stopSelf();
            }
        };
        // 开始倒计时
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCodeTimer.start();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    // 发送广播
    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    // 发送带有数据的广播
    private void broadcastUpdate(final String action, String time) {
        final Intent intent = new Intent(action);
        intent.putExtra("time", time);
        sendBroadcast(intent);
        Log.e("==","发送广播");
    }
}
