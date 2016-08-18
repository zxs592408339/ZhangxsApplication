package com.example.administrator.zhangxsapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    public Button mProgressBrn, mOneAlertBrn, mTwoAlertBrn, mThreeAlertBrn;
    ProgressDialog progressDialog;
    String[] strings;

    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        mProgressBrn = (Button) findViewById(R.id.alert_dialog_one_brn);
        mProgressBrn.setOnClickListener(this);

        mOneAlertBrn = (Button) findViewById(R.id.alert_dialog_two_brn);
        mOneAlertBrn.setOnClickListener(this);

        mTwoAlertBrn = (Button) findViewById(R.id.alert_dialog_three_brn);
        mTwoAlertBrn.setOnClickListener(this);

        mThreeAlertBrn = (Button) findViewById(R.id.alert_dialog_four_brn);
        mThreeAlertBrn.setOnClickListener(this);
        
        
        // TODO: 2016/6/12  接收线程发送的消息并处理
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                String str = (String) msg.obj;

                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
                builder.setMessage(str);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder;
        switch (v.getId()) {
            case R.id.alert_dialog_one_brn:
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("正在下载中...");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 100; i++) {
                            progressDialog.setProgress(i);
                            SystemClock.sleep(50);
                        }
                        progressDialog.dismiss();

                        // TODO: 2016/6/12  向主线程发送消息
                        Message msg = Message.obtain();
                        msg.obj = "加载完成";
                        mHandler.sendMessage(msg);
                    }
                }).start();
                break;

            case R.id.alert_dialog_two_brn:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("提示信息：");
                builder.setMessage("是否退出？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "继续成功!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "操作成功!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.alert_dialog_three_brn:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("兴趣爱好");
                strings = new String[]{"听音乐", "看书", "打游戏", "打篮球", "爬山"};
                builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "你选择是:" + strings[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "继续成功!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "操作成功!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog1 = builder.create();
                dialog1.show();
                break;
            case R.id.alert_dialog_four_brn:
                WeiXinDialog weiXinDialog = new WeiXinDialog(this);
                weiXinDialog.show();
                break;
        }
    }
}
