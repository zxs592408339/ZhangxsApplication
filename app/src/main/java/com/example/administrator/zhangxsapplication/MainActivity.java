package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Handler handler = new Handler();
    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.main_text_view);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LinearMainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000);

        new Thread(new Runnable() {
            int i;
            @Override
            public void run() {
                for (i = 6; i >= 1; i--) {
                    SystemClock.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("倒计时：" + i + "s");
                        }
                    });
                }
            }
        }).start();
    }
}
