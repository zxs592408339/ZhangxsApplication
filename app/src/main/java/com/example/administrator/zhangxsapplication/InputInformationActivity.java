package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dem.Student;
import com.example.administrator.dem.Teacher;

public class InputInformationActivity extends AppCompatActivity implements View.OnClickListener{
public TextView mClickDay,mClickAddress,mShowBackMessageTxt;
public RadioGroup mClickSex;
public Button mClickBtn,mBundleBtn,mStudentBtn,mTeacherBtn;
public String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_information);
        mClickDay = (TextView)findViewById(R.id.edit_day);
        mClickAddress = (TextView)findViewById(R.id.edit_address);
        mClickSex = (RadioGroup) findViewById(R.id.radio_sex);
        mClickSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_sxe_man:
                        sex="男";
                        Snackbar.make(group,sex,Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_sxe_woman:
                        sex="女";
                        Toast.makeText(InputInformationActivity.this,sex,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mClickBtn = (Button)findViewById(R.id.send_out_button);
        mClickBtn.setOnClickListener(this);

        mBundleBtn = (Button) findViewById(R.id.send_out_bundle);
        mBundleBtn.setOnClickListener(this);

        mStudentBtn = (Button) findViewById(R.id.send_out_student);
        mStudentBtn.setOnClickListener(this);

        mTeacherBtn = (Button) findViewById(R.id.send_out_teacher);
        mTeacherBtn.setOnClickListener(this);

        mShowBackMessageTxt = (TextView) findViewById(R.id.parameter_back_first_txt);

    }

    @Override
    public void onClick(View v){
        String day = mClickDay.getText().toString(),
            address = mClickAddress.getText().toString();
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.send_out_button:
                intent.putExtra("INT",1);
                intent.putExtra("DAY", day);
                intent.putExtra("ADDRESS", address);
                intent.putExtra("SEX", sex);
                intent.setAction("it.intent.action.SEND");
                startActivityForResult(intent,101);
                break;
            case R.id.send_out_bundle:
                intent.putExtra("INT",2);
                Bundle bundle = new Bundle();
                bundle.putString("DAY",day);
                bundle.putString("ADDRESS",address);
                bundle.putString("SEX",sex);
                intent.putExtra("BUNDLE", bundle);
                intent.setAction("it.intent.action.SEND");
                startActivityForResult(intent,101);
                break;
            case R.id.send_out_student:
                intent.putExtra("INT",3);
                Student student = new Student();
                student.setDay(day);
                student.setAddress(address);
                student.setSex(sex);
                intent.putExtra("STUDENT",student);
                intent.setAction("it.intent.action.SEND");
                startActivityForResult(intent,101);
                break;
            case R.id.send_out_teacher:
                intent.putExtra("INT",4);
                Teacher teacher = new Teacher(day,address,sex);
                intent.putExtra("TEACHER",teacher);
                intent.setAction("it.intent.action.SEND");
                startActivityForResult(intent,101);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String msg = data.getStringExtra("MESSAGE");
        mShowBackMessageTxt.setText(msg);
    }
}
