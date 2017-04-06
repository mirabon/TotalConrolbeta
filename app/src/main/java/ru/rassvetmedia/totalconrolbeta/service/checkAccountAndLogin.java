package ru.rassvetmedia.totalconrolbeta.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class checkAccountAndLogin extends IntentService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public checkAccountAndLogin(String name) {
        super("checkAccountAndLogin");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
