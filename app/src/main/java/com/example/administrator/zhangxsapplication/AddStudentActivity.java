package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {
    public EditText mStudentNameEdit, mStudentIDEdit, mStudentAgeEdit;
    public Button mAddStudentBrn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mStudentNameEdit = (EditText) findViewById(R.id.student_name_add);
        mStudentIDEdit = (EditText) findViewById(R.id.student_id_add);
        mStudentAgeEdit = (EditText) findViewById(R.id.student_age_add);

        mAddStudentBrn = (Button) findViewById(R.id.student_add_brn);

        mAddStudentBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 201;
                Intent data = getIntent();
                String name = mStudentNameEdit.getText().toString(),
                        ID = mStudentIDEdit.getText().toString(),
                        age = mStudentAgeEdit.getText().toString();

                data.putExtra("ADDSTUDENTNAME", name);
                data.putExtra("ADDSTUDENTID", ID);
                data.putExtra("ADDSTUDENTAGE", age);
                setResult(resultCode, data);
                finish();
            }
        });
    }


}
