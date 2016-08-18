package com.example.administrator.zhangxsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompletTextActivity extends AppCompatActivity {
    public AutoCompleteTextView mAutoCompletTextView;
    public  ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complet_text);
        mAutoCompletTextView = (AutoCompleteTextView) findViewById(R.id.widget_autocomplete_txt);
//        String[] arrays = {"chinese","android","java","UI","chen","and","are","abc"};
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrays);
//        mAutoCompletTextView.setAdapter(arrayAdapter);
        mAutoCompletTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String word = s.toString();
                String[] arrays;
                if(word.equals("a")) {
                    arrays = new String[]{"abcd", "android", "aroid", "avoid", "and"};
                }else if(word.equals("b")){
                    arrays = new String[]{"bcd", "bndroid", "boid", "borid", "band"};
                }else{
                    arrays = new String[]{"android", "ajava", "ios", "spring", "strucs", "aroid"};
                }
                adapter = new ArrayAdapter(AutoCompletTextActivity.this,android.R.layout.simple_list_item_1,arrays);
                mAutoCompletTextView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
