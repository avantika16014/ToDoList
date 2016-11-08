package com.example.admin.todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private static ToDoAdapter toDoAdapter;
    static ArrayList<ListItem> listData=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private DBHelper myDb;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout) ;

        swipeRefreshLayout.setOnRefreshListener(this);
        myDb=new DBHelper(getContext());
        updateUI();
        return view;
    }
    public void updateUI()
    {
        Cursor cursor=myDb.retrieveEntry();
        //cursor.moveToFirst();
        listData.clear();
        while (cursor.moveToNext()){
            ListItem item = new ListItem();
            item.setItem_name(cursor.getString(0));
            item.setDesc(cursor.getString(1));
            listData.add(item);
        }

        toDoAdapter=new ToDoAdapter(listData,getActivity());
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        recyclerView.setAdapter(toDoAdapter);
    }
    public static void updateDataSet(String title,String desc)
    {
        ListItem item=new ListItem();
        item.setItem_name(title);
        item.setDesc(desc);
        listData.add(item);
        toDoAdapter.notifyDataSetChanged();
    }
    @Override
    public void onRefresh() {
        updateUI();
    }
}