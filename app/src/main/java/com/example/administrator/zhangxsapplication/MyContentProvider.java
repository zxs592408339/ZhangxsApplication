package com.example.administrator.zhangxsapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.administrator.dem.DataBase;

public class MyContentProvider extends ContentProvider {
    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.example.administrator.zhangxsapplication.MyContentProvider";
    public static final String CONTENT_URI = SCHEME + AUTHORITY;
    private DataBase mDataBase;
    private SQLiteDatabase mDB;
    @Override
    public boolean onCreate() {
        mDataBase = new DataBase(getContext());
        mDB = mDataBase.getReadableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = mDB.query("user", projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDB.insert("user",null,values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDB.delete("user",selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return  mDB.update("user",values,selection,selectionArgs);
    }

}
