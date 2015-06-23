package com.example.dijana.presidentproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dijana.presidentproject.fragments.FirstFragment;
import com.example.dijana.presidentproject.fragments.SecondFragment;
import com.example.dijana.presidentproject.fragments.ThirdFragment;

import java.lang.reflect.Field;

/**
 * Created by Dijana on 15.6.2015..
 */
public class MyPagerAdapter extends FragmentPagerAdapter{


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FirstFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            case 2:
                return ThirdFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "Page" + position;
    }
}
