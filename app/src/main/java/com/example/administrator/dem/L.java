package com.example.administrator.dem;

import android.util.Log;

public class L {
    private static final String tag= "ZXSApplication";
    private static final boolean isDebug =true;
    public static void v(String v){
        if(isDebug)
        Log.v(tag,v);
    }
    public static void d(String v){
        if(isDebug)
        Log.d(tag,v);
    }
    public static void i(String v){
        if(isDebug)
        Log.i(tag,v);
    }
    public static void w(String v){
        if(isDebug)
        Log.w(tag,v);
    }
    public static void e(String v){
        if(isDebug)
        Log.e(tag,v);
    }
}
