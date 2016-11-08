package com.example.admin.todolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 11/5/2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<ListItem> listData;
    int position;
    public ViewPagerAdapter(FragmentManager fm,ArrayList<ListItem> listData, int position) {
        super(fm);
        this.listData=listData;
        this.position=position;
    }

    @Override
    public Fragment getItem(int position) {
        return new PagerFragment(listData.get(position));

    }
    @Override
    public int getCount() {
        return listData.size();
    }
}
