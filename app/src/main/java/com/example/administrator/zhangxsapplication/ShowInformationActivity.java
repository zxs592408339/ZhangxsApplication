package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dem.Student;
import com.example.administrator.dem.Teacher;

public class ShowInformationActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView mShowDay, mShowAddress, mShowSex;
    public Button mBackBtn, mBackBtnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        mShowDay = (TextView) findViewById(R.id.show_text_day);
        mShowAddress = (TextView) findViewById(R.id.show_text_address);
        mShowSex = (TextView) findViewById(R.id.show_sex);
        mBackBtn = (Button) findViewById(R.id.parameter_second_btn);
        mBackBtn.setOnClickListener(this);
        mBackBtnTwo = (Button) findViewById(R.id.parameter_second_btn_two);
        mBackBtnTwo.setOnClickListener(this);
        Intent intent = getIntent();
        int n = intent.getIntExtra("INT", -1);
        switch (n) {
            case 1:
                getTeacherParcelable(intent);
                break;
            case 2:
                getStudentParameter(intent);
                break;
            case 3:
                bundelParameter(intent);
                break;
            case 4:
                baseTypeParameter(intent);
                break;
        }
    }

    public void getTeacherParcelable(Intent intent) {
        String day = intent.getStringExtra("DAY"),
                address = intent.getStringExtra("ADDRESS"),
                sex = intent.getStringExtra("SEX");
        mShowDay.setText(day);
        mShowAddress.setText(address);
        mShowSex.setText(sex);
    }

    public void getStudentParameter(Intent intent) {
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        String day = bundle.getString("DAY"),
                address = bundle.getString("ADDRESS"),
                sex = bundle.getString("SEX");
        mShowDay.setText(day);
        mShowAddress.setText(address);
        mShowSex.setText(sex);
    }

    public void bundelParameter(Intent intent) {
        Student student = (Student) intent.getSerializableExtra("STUDENT");
        mShowDay.setText(student.getDay());
        mShowAddress.setText(student.getAddress());
        mShowSex.setText(student.getSex());
    }

    public void baseTypeParameter(Intent intent) {
        Teacher teacher = intent.getParcelableExtra("TEACHER");
        mShowDay.setText(teacher.day);
        mShowAddress.setText(teacher.address);
        mShowSex.setText(teacher.sex);
    }

    public String string(TextView v) {
        String s = v.getText().toString();
        return s;
    }

    @Override
    public void onClick(View v) {
        int resultCode = 201;
        String backMsg;
        Intent data = getIntent();
        switch (v.getId()) {
            case R.id.parameter_second_btn:
                backMsg = "出生日期：" + string(mShowDay) + "\n居住地址：" + string(mShowAddress) + "\n性别：" + string(mShowSex);
                data.putExtra("MESSAGE", backMsg);
                setResult(resultCode, data);
                finish();
                break;
            case R.id.parameter_second_btn_two:
                backMsg = "出生日期：" + string(mShowDay) + "\n居住地址：" + string(mShowAddress);
                data.putExtra("MESSAGE", backMsg);
                setResult(resultCode, data);
                finish();
                break;
        }
    }
}
