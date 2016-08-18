package com.example.administrator.zhangxsapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.administrator.dem.L;


public class MyService extends Service {
    private int mCount = 0;

    interface ICount{
        int getCount();
    }

    public class ICountService extends Binder implements  ICount{

        @Override
        public int getCount() {
            return mCount;
        }
    }

    public ICountService iCountService = new ICountService();

    public MyService(){
        L.e("MyService 构造方法 >> ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("MyService onCreate >> ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    mCount++;
                    L.e("count >>>>  :"+mCount);
                    SystemClock.sleep(1000);
                }
            }
        }).start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e("MyService onStartCommand >> ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e("MyService onBind >> ");
      return iCountService;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.e("MyService onUnbind >> ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        L.e("MyService onDestroy >> ");
        super.onDestroy();
    }
}
