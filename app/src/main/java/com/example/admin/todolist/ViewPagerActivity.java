package com.example.admin.todolist;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<ListItem> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Bundle bundle=getIntent().getExtras();
        listData=bundle.getParcelableArrayList("listdata");
        int position=bundle.getInt("position");
        viewPager= (ViewPager) findViewById(R.id.activity_view_pager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),listData,position);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            //viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            finish();
        }
    }

}
