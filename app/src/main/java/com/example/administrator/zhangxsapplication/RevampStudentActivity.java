package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.dem.DataBase;

public class RevampStudentActivity extends AppCompatActivity {
    public EditText mStudentNameEdit, mStudentIDEdit, mStudentAgeEdit;
    public Button mAddStudentBrn;
    SQLiteDatabase db;
    String oldID, oldName, oldAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mStudentNameEdit = (EditText) findViewById(R.id.student_name_add);
        mStudentIDEdit = (EditText) findViewById(R.id.student_id_add);
        mStudentAgeEdit = (EditText) findViewById(R.id.student_age_add);

        mAddStudentBrn = (Button) findViewById(R.id.student_add_brn);

        Intent intent = getIntent();
        oldID = intent.getStringExtra("STUDENTID");
        mStudentIDEdit.setText(oldID);
        oldName = intent.getStringExtra("STUDENTNAME");
        mStudentNameEdit.setText(oldName);
        oldAge = intent.getStringExtra("STUDENTAGE");
        mStudentAgeEdit.setText(oldAge);

        DataBase database = new DataBase(this);
        db = database.getReadableDatabase();
        mAddStudentBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 301;
                Intent data = getIntent();
                String name = mStudentNameEdit.getText().toString(),
                        ID = mStudentIDEdit.getText().toString(),
                        age = mStudentAgeEdit.getText().toString();

                data.putExtra("REVAMPSTUDENTNAME", name);
                data.putExtra("REVAMPSTUDENTID", ID);
                data.putExtra("REVAMPSTUDENTAGE", age);
                setResult(resultCode, data);
                finish();;


            }
        });
    }
}

