package ru.rassvetmedia.totalconrolbeta.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.db.impl.OperateAccountsDaoImpl;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;

public class checkAccountAndLogin extends Service {
    private Context appContext;
    private boolean status;
    // Create the Handler object (on the main thread by default)
    private final Handler handler = new Handler();
    private Runnable runnableCode;
    private final static int INTERVAL = 1000 * 5; //60 sec

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        // Let it continue running until it is stopped.
        appContext = getBaseContext();//Get the context here
        status = false;


        // Define the code block to be executed
        runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                someTask();
                Log.d(Constans.LOG_TAG, "Called on main thread");
                handler.postDelayed(this, INTERVAL);
            }
        };
        // Run the above code block on the main thread after 2 seconds

        runnableCode.run();

        return START_STICKY;
    }

    private void someTask() {
        if (null != appContext) {
            OperateAccountsDaoImpl o = new OperateAccountsDaoImpl();
            status = o.checkAccounts(appContext);
            if (status) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(appContext, "Аккаунты есть", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(appContext, "Нужно добавить аккаунты", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constans.LOG_TAG, "Service Destroyed");
        handler.removeCallbacks(runnableCode);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constans.LOG_TAG, "Service Start");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
