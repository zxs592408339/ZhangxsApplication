package com.example.administrator.zhangxsapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.dem.DataBase;
import com.example.administrator.dem.StudentAdapter;
import com.example.administrator.dem.Students;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {
    public ListView mStudentList;
    List<Students> list = new ArrayList<>();
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stydent);
        DataBase database = new DataBase(this);
        db = database.getReadableDatabase();
        mStudentList = (ListView) findViewById(R.id.show_student);

        showStudent();
        StudentAdapter adapter = new StudentAdapter(this, list);
        mStudentList.setAdapter(adapter);
        registerForContextMenu(mStudentList);
    }

    public void showStudent() {
        Cursor cursor = db.query("user",null,null,null,null,null,null);//查询并获得游标
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            Students student  = new Students(id,name,age);
            list.add(student);
        }
    }
}
