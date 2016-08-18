package com.example.administrator.zhangxsapplication;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.dem.L;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    MusicAdapter adapter;
    List<Music> list = new ArrayList<>();
    Handler handler = new Handler();
    public ListView mMusicListView;
    public Button mMusicStop, mMusicStart, mMusicPrepare;
    MediaPlayer mediaPlayer;
    int musicQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mMusicStop = (Button) findViewById(R.id.music_stop);
        mMusicStart = (Button) findViewById(R.id.music_start);
        mMusicPrepare = (Button) findViewById(R.id.music_prepare);
        mMusicListView = (ListView) findViewById(R.id.show_music_list);
        mMusicStop.setOnClickListener(this);
        mMusicStart.setOnClickListener(this);
        mMusicPrepare.setOnClickListener(this);
        mMusicListView.setOnItemClickListener(this);

        adapter = new MusicAdapter(this, list);
        mMusicListView.setAdapter(adapter);

        handler.post(new Runnable() {
            @Override
            public void run() {
                File rootFile = new File(Environment.getExternalStorageDirectory().getPath());
                L.e(Environment.getExternalStorageDirectory().getPath());
                scanFileMp3(rootFile);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_stop:
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMusicStart.setText("播放");
                break;
            case R.id.music_start:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mMusicStart.setText("播放");
                } else {
                    mediaPlayer.start();
                    mMusicStart.setText("暂停");
                }
                break;
            case R.id.music_prepare:
                musicQueue = musicQueue + 1;
                if (musicQueue == list.size()) {
                    musicQueue = 0;
                }
                Music music = list.get(musicQueue);
                playMusic("file://" + music.getMusicPath());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter = (MusicAdapter) parent.getAdapter();
        Music item = (Music) adapter.getItem(position);
        String path = item.getMusicPath();
        musicQueue = position;
        playMusic("file://" + path);
    }

    public void playMusic(String fileDir) {
        try {
            if (mediaPlayer == null)
                mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileDir);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mMusicStart.setText("暂停");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    Music music = new Music(file.getName(), file.getPath());
                    list.add(music);
                }
            }
        }
    }

    class MusicAdapter extends BaseAdapter {
        List<Music> list = new ArrayList<>();
        LayoutInflater layoutInflater;

        public MusicAdapter(Context context, List<Music> list) {
            layoutInflater = LayoutInflater.from(context);
            this.list = list;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.layout_show_music, null);
                TextView musicNameView = (TextView) convertView.findViewById(R.id.music_mane_view);
                TextView musicPathView = (TextView) convertView.findViewById(R.id.music_path_view);
                MusicBean musicBean = new MusicBean();
                musicBean.musicNameView = musicNameView;
                musicBean.musicPathView = musicPathView;
                convertView.setTag(musicBean);
            }
            Music item = (Music) getItem(position);
            MusicBean musicBean = (MusicBean) convertView.getTag();
            musicBean.musicNameView.setText(item.getMusicName());
            musicBean.musicPathView.setText(item.getMusicPath());
            return convertView;
        }
    }

    class Music {
        String musicName, musicPath;

        public Music(String mane, String musicPath) {
            this.musicName = mane;
            this.musicPath = musicPath;
        }

        public String getMusicName() {
            return musicName;
        }

        public String getMusicPath() {
            return musicPath;
        }
    }

    class MusicBean {
        TextView musicNameView, musicPathView;
    }
}
