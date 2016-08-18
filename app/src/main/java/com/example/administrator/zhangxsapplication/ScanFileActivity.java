package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.dem.MySimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScanFileActivity extends AppCompatActivity implements View.OnClickListener {

    public Button mScanFileBrn;
    public ListView mScanFileListView;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_file);
        mScanFileBrn = (Button) findViewById(R.id.scan_file_brn);
        mScanFileListView = (ListView) findViewById(R.id.scan_file_mp3);
        mScanFileBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        File rootFile = new File(Environment.getExternalStorageDirectory().getPath());
        scanFileMp3(rootFile);
        getAdapter();
    }

    public void getAdapter(){
        MySimpleAdapter adapter = new MySimpleAdapter(this,list);
        mScanFileListView.setAdapter(adapter);
    }

    public void scanFileMp3(File directorFile) {
        File[] isFile = directorFile.listFiles();
        if (isFile == null) {
            return;
        }
        for (File file : isFile) {
            if (file.isDirectory()) {
                scanFileMp3(file);
            } else {
                String fileName = file.getName();
                int index = fileName.indexOf(".");
                if (index == -1) {
                    return;
                }
                String fileType = fileName.substring(index);
                if (fileType.trim().equalsIgnoreCase(".mp3")) {
                    list.add(file.getPath());
                }
            }
        }
    }
}