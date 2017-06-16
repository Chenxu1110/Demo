package com.example.chenxu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvOne;
    private TextView mTvTwo;
    private TextView mTvThree;

    private void assignViews() {
        mTvOne = (TextView) findViewById(R.id.tv_one);
        mTvTwo = (TextView) findViewById(R.id.tv_two);
        mTvThree = (TextView) findViewById(R.id.tv_three);
        CustomerScrollView.onclick(new CustomerScrollView.SetOnclick() {
            @Override
            public void actionDown() {
                mTvOne.setVisibility(View.GONE);
            }

            @Override
            public void actionUp() {
                mTvOne.setVisibility(View.VISIBLE);
            }

            @Override
            public void actionMove() {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }
}
