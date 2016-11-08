package com.example.admin.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class AddItemActivity extends AppCompatActivity {

    private EditText getTitle;
    private EditText getDesc;
    private Button createToDo;
    private DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getTitle=(EditText)findViewById(R.id.newToDoTitle);
        getDesc=(EditText)findViewById(R.id.newToDoDesc);
        createToDo=(Button)findViewById(R.id.createToDo);
        myDb=new DBHelper(this);
        createToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getTitle.getText().toString().equals(""))
                {
                    if(myDb.checkEntry(getTitle.getText().toString())>0)
                        Toast.makeText(getApplicationContext(),"Item with similar title already exists." +
                                "\nKindly choose a unique title",Toast.LENGTH_SHORT).show();
                    else
                    if(myDb.insertEntry(getTitle.getText().toString(),getDesc.getText().toString())) {
                        MainActivityFragment.updateDataSet(getTitle.getText().toString(),getDesc.getText().toString());
                        Toast.makeText(getApplicationContext(), "New To-Do item created", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(getApplicationContext(),"To-Do item not created",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Item should have a title",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
