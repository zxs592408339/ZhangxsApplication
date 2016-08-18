package com.example.administrator.zhangxsapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

public class WeiXinDialog extends AlertDialog {
    private Context context;
    private Button mLoginBtn;
    private EditText mUserNameEdit,mPasswordEdit;
    protected WeiXinDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
    }
}
