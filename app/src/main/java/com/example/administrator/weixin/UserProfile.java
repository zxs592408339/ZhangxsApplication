package com.example.administrator.weixin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserProfile extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "userProfile.db";
    private final static int DATABASE_VERSION = 1;

    public UserProfile(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSql = "create table user(name varchar(20),password Integer ,PRIMARY KEY(name))";
        db.execSQL(tableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
