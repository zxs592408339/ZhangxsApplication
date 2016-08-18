package com.example.administrator.zhangxsapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.dem.DataBase;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    String sql;
    SQLiteDatabase db;
    public Button mStudentAddBrn, mStudentDeleteBrn, mStudentRevampBrn;
    public EditText mStudentNameEdit, mStudentIDEdit, mStudentAgeEdit,mDeleteStudent,mRevampStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        DataBase database = new DataBase(this);
        db = database.getReadableDatabase();

        mStudentNameEdit = (EditText) findViewById(R.id.student_name_edit);
        mStudentIDEdit = (EditText) findViewById(R.id.student_id_edit);
        mStudentAgeEdit = (EditText) findViewById(R.id.student_age_edit);

        mDeleteStudent = (EditText) findViewById(R.id.delete_student_edit);

        mRevampStudent = (EditText) findViewById(R.id.revamp_student_edit);

        mStudentAddBrn = (Button) findViewById(R.id.student_add_brn);
        mStudentAddBrn.setOnClickListener(this);
        mStudentDeleteBrn = (Button) findViewById(R.id.student_delete_brn);
        mStudentDeleteBrn.setOnClickListener(this);
        mStudentRevampBrn = (Button) findViewById(R.id.student_revamp_brn);
        mStudentRevampBrn.setOnClickListener(this);
    }

    public void addStudent() {
        String name = mStudentNameEdit.getText().toString(),
                ID = mStudentIDEdit.getText().toString(),
                age = mStudentAgeEdit.getText().toString();
        sql = "insert into user(id,name,age) values ("+ID+",\""+name+"\","+age+")";
        db.execSQL(sql);
    }

    public void deleteStudent(){
        String ID = mDeleteStudent.getText().toString();
        sql = "delete from user where id = "+ID;
        db.execSQL(sql);
    }

    public void revampStudent(){
        String oldID = mRevampStudent.getText().toString();
        String name = mStudentNameEdit.getText().toString(),
                newID = mStudentIDEdit.getText().toString(),
                age = mStudentAgeEdit.getText().toString();
        sql = "update user set id ="+newID+" ,name =\""+name+"\",age =" +age+" where id ="+oldID;
        db.execSQL(sql);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.student_add_brn:
                addStudent();
                break;
            case R.id.student_delete_brn:
                deleteStudent();
                break;
            case R.id.student_revamp_brn:
                revampStudent();
                break;
        }
    }
}
