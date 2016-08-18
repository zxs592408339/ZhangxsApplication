package com.example.administrator.zhangxsapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.administrator.dem.L;

public class MyBinderService extends Service {
    private int mCount = 0;
    private boolean flag = true;

    interface ICount {
        int getCount();
    }

    public class ICountService extends Binder implements ICount {

        @Override
        public int getCount() {
            return mCount;
        }
    }

    public ICountService iCountService = new ICountService();

    public MyBinderService() {
        L.e("MyBinderService 构造方法 >> ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("MyService onCreate >> ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    mCount++;
                    L.e("count >>>>  :" + mCount);
                    SystemClock.sleep(1000);
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iCountService;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
