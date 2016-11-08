package com.example.admin.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Admin on 11/3/2016.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder>{

    private ArrayList<ListItem> listData;
    private LayoutInflater layoutInflater;
    public static final String TAG = "TODO";
    Context context;
    DBHelper myDb;
    public ToDoAdapter(ArrayList<ListItem> listData, Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public ToDoAdapter.ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new ToDoHolder(view);

    }

    @Override
    public void onBindViewHolder(ToDoHolder holder, final int position) {
        myDb=new DBHelper(context);
        final ListItem item = listData.get(position);
        holder.item_name.setText(item.getItem_name());
        holder.item_desc.setText(item.getDesc());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb.deleteEntry(item.getItem_name()))
                {
                    Toast.makeText(context,"Item Deleted!",Toast.LENGTH_SHORT).show();
                    listData.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        Log.d(TAG, "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView item_name;
        private TextView item_desc;
        private ImageButton deleteButton;
        private View container;

        public ToDoHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item_name = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            item_desc = (TextView) itemView.findViewById(R.id.list_item_desc_text_view);
            deleteButton=(ImageButton)itemView.findViewById(R.id.delete_todo);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
                    Intent intent = new Intent(context, ViewPagerActivity.class);
                    Bundle data=new Bundle();
                    data.putParcelableArrayList("listdata", listData);
                    data.putInt("position",position);
                    intent.putExtras(data);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }
}
