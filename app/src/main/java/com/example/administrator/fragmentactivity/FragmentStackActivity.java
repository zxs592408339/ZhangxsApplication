package com.example.administrator.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.zhangxsapplication.R;

public class FragmentStackActivity extends Activity implements View.OnClickListener {
    int mStackLevel = 0;
    public Button mPushBrn, mPopBrn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stack);
        mPushBrn = (Button) findViewById(R.id.new_fragment);
        mPopBrn = (Button) findViewById(R.id.delete_fragment);
        mPushBrn.setOnClickListener(this);
        mPopBrn.setOnClickListener(this);
    }

    void addFragmentToStack() {
        Fragment newFragment = CountingFragment.newInstance(mStackLevel);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.simple_fragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_fragment:
                mStackLevel++;
                addFragmentToStack();
                break;
            case R.id.delete_fragment:
                mStackLevel--;
                getFragmentManager().popBackStack(); //弹栈
                break;
        }
    }

    public static class CountingFragment extends Fragment {
        int mNum;
        private static int[] sColorValue = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};


        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_stack_layout, container, false);
            TextView tv = (TextView) v.findViewById(R.id.text);
            tv.setText("Fragment #" + mNum);
            Log.v("TAG", "mNum % 3 " + mNum % 3);
            tv.setBackgroundColor(getActivity().getResources().getColor(sColorValue[mNum % 4]));
            return v;
        }
    }

}
