package com.example.administrator.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SubListFragment extends ListFragment {

    private String[] arrays = new String[]{"新闻1", "新闻2", "新闻3", "新闻4"};

    public static Fragment newInstance(){
        SubListFragment listFragment = new SubListFragment();
        return listFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrays);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ArrayAdapter adapter = (ArrayAdapter) l.getAdapter();
        String item = (String) adapter.getItem(position);
        Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();
    }
}
