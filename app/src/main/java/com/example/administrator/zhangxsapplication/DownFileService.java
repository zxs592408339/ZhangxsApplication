package com.example.administrator.zhangxsapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.example.administrator.dem.L;

public class DownFileService extends IntentService {
    int count = 0;

    public DownFileService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ++count;
        L.e("文件" +count+"开始下载");
        SystemClock.sleep(5 * 1000);
        L.e("文件" +count+"下载完成");
    }
}
