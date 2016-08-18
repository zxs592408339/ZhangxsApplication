package com.example.administrator.weixin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zhangxsapplication.R;

public class WeiXinActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText mNameMessageTxt, mPasswordMessageTxt;
    public Button mDeleteNameBrn, mDeletePasswordBrn, mLogBrn;
    public TextView mRegisterTxt;
    public CheckBox mCheckBox;
    SQLiteDatabase db;
    String name, password;
    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);

        mNameMessageTxt = (EditText) findViewById(R.id.edit_text_one);
        mPasswordMessageTxt = (EditText) findViewById(R.id.edit_text_two);

        // TODO: 2016/6/29 调用SharedPreferences的data的文件中取值的方法
        getShared();

        // TODO: 2016/6/29 实例化数据库，并判断数据库是否存在，若不存在，创建数据库
        UserProfile base = new UserProfile(this);
        db = base.getReadableDatabase();

        mRegisterTxt = (TextView) findViewById(R.id.txt_register);
        mRegisterTxt.setOnClickListener(this);

        mLogBrn = (Button) findViewById(R.id.brn_log_weixin);
        mLogBrn.setOnClickListener(this);

        mDeleteNameBrn = (Button) findViewById(R.id.delete_button_one);
        mDeleteNameBrn.setOnClickListener(this);

        mDeletePasswordBrn = (Button) findViewById(R.id.delete_button_two);
        mDeletePasswordBrn.setOnClickListener(this);

        mCheckBox = (CheckBox) findViewById(R.id.remember_check);
        // TODO: 2016/6/29 给CheckBox 赋初始状态
        mCheckBox.setChecked(isChecked);
        // TODO: 2016/6/29 监听CheckBox
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckeds) {
                isChecked = isCheckeds;
            }
        });
    }

    // TODO: 2016/6/29 实现按钮点击事件
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.delete_button_one:
                mNameMessageTxt.setText(null);
                break;
            case R.id.delete_button_two:
                mPasswordMessageTxt.setText(null);
                break;
            case R.id.brn_log_weixin:
                if (mNameMessageTxt.getText().toString().equals("")) {
                    mNameMessageTxt.setError("用户名不能为空！");
                } else if (mPasswordMessageTxt.getText().toString().equals("")) {
                    mPasswordMessageTxt.setError("密码不能为空！");
                } else {
                    name = mNameMessageTxt.getText().toString();
                    password = mPasswordMessageTxt.getText().toString();
                    String account[] = {name, password};

                    Cursor cursor = db.query("user", null, "name = ? and password = ?", account, null, null, null);//查询并获得游标

                    if (cursor.moveToNext()) {
                        Intent intent = new Intent(this, HaHaActivity.class);
                        startActivity(intent);
                        setShared();
                        finish();
                    } else {
                        Toast.makeText(this, "账号和密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.txt_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    // TODO: 2016/6/29 SharedPreferences的data的文件中取出数据
    public void getShared() {
        SharedPreferences pref = getSharedPreferences("data",
                MODE_PRIVATE);
        String getName = pref.getString("name", ""),
                getPassword = pref.getString("password", "");
        isChecked = pref.getBoolean("checkBox", false);
        mNameMessageTxt.setText(getName);
        mPasswordMessageTxt.setText(getPassword);
    }

    // TODO: 2016/6/29 保存数据值到SharedPreferences的data的文件中
    public void setShared() {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        if (isChecked) {
            editor.putString("name", name);
            editor.putString("password", password);
            editor.putBoolean("checkBox", true);
            editor.commit();
        } else {
            editor.putString("name", "");
            editor.putString("password", "");
            editor.putBoolean("checkBox", false);
            editor.commit();
        }
    }
}
