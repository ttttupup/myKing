package com.example.hugy.kingeconomy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.hugy.kingeconomy.sevice.TestCode;

/**
 * Created by hugy on 2018/3/22.
 */

public class LocalBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,TestCode.class);
        service.setAction(intent.getAction());
        context.startService(service);
    }
}
