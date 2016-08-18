package com.example.administrator.zhangxsapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dem.DataBase;
import com.example.administrator.dem.StudentAdapter;
import com.example.administrator.dem.Students;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementActivity extends AppCompatActivity {
    public ListView mStudentList;
    public TextView mAddStudentTxt;
    List<Students> list = new ArrayList<>();
    SQLiteDatabase db;
    StudentAdapter adapter;
    String oldID;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);

        DataBase database = new DataBase(this);
        db = database.getReadableDatabase();

        mStudentList = (ListView) findViewById(R.id.student_list);

        mAddStudentTxt = (TextView) findViewById(R.id.add_student);


        // TODO: 2016/6/27 转跳到添加Student数据的Activity
        mAddStudentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("it.intent.action.ADDSTUDENT");
                startActivityForResult(intent, 101);
            }
        });


        // TODO: 2016/6/27 显示所有学生姓名
        showStudent();
        getStudentAdapter();

        // TODO: 2016/6/28 设置上下文菜单
        mStudentList.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("选择操作");
                menu.add(0, 0, 0, "删除该条");
                menu.add(0, 1, 0, "修改该条");
            }
        });
    }

    // TODO: 2016/6/28 实例化自定义适配器
    public void getStudentAdapter() {
        adapter = new StudentAdapter(this, list);
        mStudentList.setAdapter(adapter);
//        registerForContextMenu(mStudentList);
    }

    // TODO: 2016/6/28 将数据库的数据添加到Student对象中，并添加到List中
    public void showStudent() {
        String querySQL = "select * from user";
        Cursor cursor = db.rawQuery(querySQL, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name")),
                    id = cursor.getString(cursor.getColumnIndex("id")),
                    age = cursor.getString(cursor.getColumnIndex("age"));
            Students student = new Students(id, name, age);
            list.add(student);
        }
    }

    // TODO: 2016/6/28 转跳到修改数据的Activity
    public void revampStudent(String name, String id, String age) {
        Intent intent = new Intent();
        intent.putExtra("STUDENTNAME", name);
        intent.putExtra("STUDENTID", id);
        intent.putExtra("STUDENTAGE", age);
        intent.setAction("it.intent.action.SENDES");
        startActivityForResult(intent, 101);
    }

    // TODO: 2016/6/28 监听上下文菜单点击事件
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        position = Integer.parseInt(String.valueOf(info.id));

        oldID = list.get(position).getId();
        String  oldName = list.get(position).getName(),
                oldAge = list.get(position).getAge();

        switch (item.getItemId()) {
            case 0:
                String sql = "delete from user where id = " + oldID;
                db.execSQL(sql);
                list.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                return true;
            case 1:
                revampStudent(oldName, oldID, oldAge);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 201:
                String name = data.getStringExtra("ADDSTUDENTNAME"),
                        ID = data.getStringExtra("ADDSTUDENTID"),
                        age = data.getStringExtra("ADDSTUDENTAGE");
                Students student = new Students(ID, name, age);
                list.add(student);
                adapter.notifyDataSetChanged();
                String sql = "insert into user(id,name,age) values (" + ID + ",\"" + name + "\"," + age + ")";
                db.execSQL(sql);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case 301:
                name = data.getStringExtra("REVAMPSTUDENTNAME");
                ID = data.getStringExtra("REVAMPSTUDENTID");
                age = data.getStringExtra("REVAMPSTUDENTAGE");
                student = new Students(ID, name, age);

                list.set(position, student);
                adapter.notifyDataSetChanged();

                sql = "update [user] set id =" + ID + " ,name =\"" + name + "\",age =" + age + " where id =" + oldID;
                db.execSQL(sql);
                break;
        }
    }
}
