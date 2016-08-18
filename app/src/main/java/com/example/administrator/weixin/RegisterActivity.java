package com.example.administrator.weixin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.zhangxsapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText mRegisterName,mRegisterPassword;
    public Button mRegisterBrn;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserProfile base = new UserProfile(this);
        db = base.getReadableDatabase();
        mRegisterName = (EditText) findViewById(R.id.edit_register_name);
        mRegisterPassword = (EditText) findViewById(R.id.edit_register_password);
        mRegisterBrn = (Button) findViewById(R.id.brn_register);
        mRegisterBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        String name = mRegisterName.getText().toString(),
                password = mRegisterPassword.getText().toString();
        cv.put("name", name);
        cv.put("password", password);
        db.insert("user", null, cv);
        finish();
    }
}
