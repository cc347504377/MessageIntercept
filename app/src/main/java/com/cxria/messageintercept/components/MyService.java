package com.cxria.messageintercept.components;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    private MyReceiver mMyReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyReceiver = new MyReceiver();
        setForeground();
        registerSms();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private void registerSms() {
        registerReceiver(mMyReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }

    private void unregisterSms() {
        unregisterReceiver(mMyReceiver);
    }

    private void setForeground() {
        Notification notificationCompat = new NotificationCompat.Builder(this)
                .build();
        startForeground(1, notificationCompat);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterSms();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
