package com.example.administrator.zhangxsapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpTextActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView mInternetGetTxt;
    public Button mInternetGetBrn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_text);
        mInternetGetTxt = (TextView) findViewById(R.id.internet_get_txt);
        mInternetGetBrn = (Button) findViewById(R.id.internet_get_brn);
        mInternetGetBrn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        doDownLoadTxt();
    }

    public void doDownLoadTxt(){
        String txtUrl = "http://192.168.5.4/WebRoot/time";
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String txturl = params[0];
                return doTextByNet(txturl);
            }

            @Override
            protected void onPostExecute(String s) {
                mInternetGetTxt.setText(s);
            }
        }.execute(txtUrl);
    }
    public String doTextByNet(String httpUrl) {
        String message = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();

            int code = urlConnection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream is = urlConnection.getInputStream();
                message = readInput(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return message;
    }

    public String readInput(InputStream is) {
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                reader.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


}
