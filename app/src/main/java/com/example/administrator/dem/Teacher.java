package com.example.administrator.dem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/5/31.
 */
public class Teacher implements Parcelable {
    public String day,address,sex;

    public Teacher(String day,String address,String sex){
        this.day=day;
        this.address=address;
        this.sex=sex;
    }

    protected Teacher(Parcel in) {
        day = in.readString();
        address = in.readString();
        sex = in.readString();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(address);
        dest.writeString(sex);
    }
}
