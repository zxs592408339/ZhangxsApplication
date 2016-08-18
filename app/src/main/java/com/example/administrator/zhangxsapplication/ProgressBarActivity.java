package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.administrator.dem.L;

public class ProgressBarActivity extends AppCompatActivity {
    public ProgressBar mProgressBar,mProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    mProgressBar2.setProgress(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                L.e("加载完成！");
            }
        }).start();
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 1; i <= 100; i++) {
                mProgressBar.setProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                mProgressBar.setProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
