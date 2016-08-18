package com.example.administrator.zhangxsapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText mIOOutEdit;
    public TextView mIOInTxt;
    public Button mOutBrn, mInBrn, mShowExternalFileDir, mOutImgBrn, mInImgBrm;
    public ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);

        mIOOutEdit = (EditText) findViewById(R.id.io_out_edit);
        mIOInTxt = (TextView) findViewById(R.id.io_in_txt);
        mOutBrn = (Button) findViewById(R.id.io_out_brn);
        mInBrn = (Button) findViewById(R.id.io_in_brn);
        mShowExternalFileDir = (Button) findViewById(R.id.show_out_brn);
        mOutImgBrn = (Button) findViewById(R.id.out_img_brn);
        mInImgBrm = (Button) findViewById(R.id.in_img_brn);
        mImageView = (ImageView) findViewById(R.id.shoe_img);
        mOutImgBrn.setOnClickListener(this);
        mInImgBrm.setOnClickListener(this);
        mShowExternalFileDir.setOnClickListener(this);
        mOutBrn.setOnClickListener(this);
        mInBrn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.io_out_brn:
                writeInternalFile();
                break;
            case R.id.io_in_brn:
                readInternalFile();
                break;
            case R.id.show_out_brn:
//                showExternalFileDir();
                break;
            case R.id.out_img_brn:
                writeExternalPicture();
                break;
            case R.id.in_img_brn:
                readExternalPicture();
                break;
        }
    }

    // TODO: 2016/6/29  向内部存储写数据
    public void writeInternalFile() {
        File file = new File(getFilesDir(), "信息存储联系.txt");
        BufferedWriter osw;
        try {
            osw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            osw.write(mIOOutEdit.getText().toString());
            osw.close();
            Toast.makeText(this, "写入成功！", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2016/6/29 在内部存储读数据
    public void readInternalFile() {
        File file = new File(getFilesDir(), "信息存储联系.txt");
        BufferedReader bis;
        try {
            bis = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String mag = bis.readLine();
            mIOInTxt.setText(mag);
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2016/6/30 外部设备存储文件的路径 
//    public void showExternalFileDir() {
//        //外部存储公共区域根目录  :/storage/emulated/0
//        File fileDir = Environment.getExternalStorageDirectory();
//        //外部存储公共区域图片目录 :/storage/emulated/0/Pictures
//        File filePictureDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        String fileMoviesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString();
//        String fileMusicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
//        L.e("fileDir :" + fileDir);
//        L.e("filePictureDir :" + filePictureDir);
//        L.e("fileMoviesDir :" + fileMoviesDir);
//        L.e("fileMusicDir :" + fileMusicDir);
//
//        //外部存储私有区域根目录 :/storage/emulated/0/Android/data/com.example.administrator.zhangxsapplication/files
//        File filePrivateDir = getExternalFilesDir(null);
//        //外部存储私有区域音乐文件根目录 :/storage/emulated/0/Android/data/com.example.administrator.zhangxsapplication/files/Music
//        String filePrivateMusicDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString();
//        String filePrivatePictureDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
//        L.e("filePrivateDir :" + filePrivateDir);
//        L.e("filePrivateMusicDir :" + filePrivateMusicDir);
//        L.e("filePrivatePictureDir :" + filePrivatePictureDir);
//    }

    // TODO: 2016/6/29 检查外部设备（SD卡）是否可用
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 2016/6/29 向外部公共区域写图片
    public void writeExternalPicture() {
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "test.png");
        FileOutputStream os;
        try {
            os = new FileOutputStream(file);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image61);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, os); //将Bitmap图片对象写入文件输出流,同时规定图片文件格式和质量
            os.close();
            Toast.makeText(this, "图片写入成功！", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2016/6/29 从外部公共区域读图片 
    public void readExternalPicture(){
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "外部存储不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "test.png");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());

        mImageView.setImageBitmap(bitmap);
    }
}
