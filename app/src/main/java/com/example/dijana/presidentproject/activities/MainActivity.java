package com.example.dijana.presidentproject.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;


import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.adapters.MyPagerAdapter;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(myPagerAdapter);

    }




}
