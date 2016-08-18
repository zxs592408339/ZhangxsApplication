package com.example.administrator.zhangxsapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.dem.DataBase;

public class QSLiteAndroidActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText mStudentNameEdit, mStudentIDEdit, mStudentAgeEdit,mStudentDeleteEdit, mStudentRevampEdit;

    public Button mStudentAddBrn, mStudentDeleteBrn, mStudentRevampBrn, mStudentFindBrn,mAddContentBrn;
    SQLiteDatabase db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qslite_android);

        DataBase base = new DataBase(this);
        db = base.getReadableDatabase();

        mStudentNameEdit = (EditText) findViewById(R.id.student_name_android_add);
        mStudentIDEdit = (EditText) findViewById(R.id.student_id_android_add);
        mStudentAgeEdit = (EditText) findViewById(R.id.student_age_android_add);
        mStudentDeleteEdit = (EditText) findViewById(R.id.student_id_android_delete);
        mStudentRevampEdit = (EditText) findViewById(R.id.student_id_android_revamp);

        mStudentAddBrn = (Button) findViewById(R.id.student_add_android_brn);
        mStudentAddBrn.setOnClickListener(this);
        mStudentDeleteBrn = (Button) findViewById(R.id.student_delete_android_brn);
        mStudentDeleteBrn.setOnClickListener(this);
        mStudentRevampBrn = (Button) findViewById(R.id.student_revamp_android_brn);
        mStudentRevampBrn.setOnClickListener(this);
        mStudentFindBrn = (Button) findViewById(R.id.student_find_android_brn);
        mStudentFindBrn.setOnClickListener(this);

        mAddContentBrn = (Button) findViewById(R.id.student_add_content_brn);
        mAddContentBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student_add_android_brn:
                addStudent();
                mStudentNameEdit.setText("");
                mStudentIDEdit.setText("");
                mStudentAgeEdit.setText("");
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.student_delete_android_brn:
                deleteStudent();
                mStudentDeleteEdit.setText("");
                Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.student_revamp_android_brn:
                revampStudent();
                mStudentNameEdit.setText("");
                mStudentIDEdit.setText("");
                mStudentAgeEdit.setText("");
                mStudentRevampBrn.setText("");
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.student_find_android_brn:
                findStudent();
                break;
            case R.id.student_add_content_brn:
                contentAddStudent();
                break;
        }
    }

    public void addStudent() {
        ContentValues cv = new ContentValues();
        String name = mStudentNameEdit.getText().toString(),
                id = mStudentIDEdit.getText().toString(),
                age = mStudentAgeEdit.getText().toString();
        cv.put("name", name);
        cv.put("id", id);
        cv.put("age", age);
        db.insert("user", null, cv);
    }

    public void deleteStudent() {
        String whereClause = "id=?";// TODO: 2016/6/28 删除的条件
        String deleteID =mStudentDeleteEdit.getText().toString();
        String[] whereArgs = {deleteID};// TODO: 2016/6/28 删除的条件参数
        db.delete("user",whereClause,whereArgs);
    }

    public void revampStudent() {
        ContentValues cv = new ContentValues();
        String name = mStudentNameEdit.getText().toString(),
                id = mStudentIDEdit.getText().toString(),
                age = mStudentAgeEdit.getText().toString();
        cv.put("name",name);// TODO ：2016/6/28 添加要更改的字段及内容
        cv.put("id",id);
        cv.put("age",age);
        String revampID = mStudentRevampBrn.getText().toString();
        String whereClause = "id=?";// TODO ：2016/6/28 修改条件
        String[] whereArgs = {revampID};// TODO ：2016/6/28 修改条件的参数
        db.update("user",cv,whereClause,whereArgs);
    }

    public void findStudent() {
        Intent intent = new Intent(this,ShowStudentActivity.class);
        startActivity(intent);
    }

    public void contentAddStudent() {
        ContentValues cv = new ContentValues();
        String name = mStudentNameEdit.getText().toString(),
                id = mStudentIDEdit.getText().toString(),
                age = mStudentAgeEdit.getText().toString();
        cv.put("name", name);
        cv.put("id", id);
        cv.put("age", age);
        getContentResolver().insert(Uri.parse(MyContentProvider.CONTENT_URI),cv);
    }
}
