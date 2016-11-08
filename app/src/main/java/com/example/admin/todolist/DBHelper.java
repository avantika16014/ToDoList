package com.example.admin.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Admin on 11/3/2016.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="MyDatabase.db";
    public static final String TABLE_NAME="TO_DO_LIST";
    public static final String COLUMN_ITEM="ITEM";
    public static final String COLUMN_DESC="DESC";
    //public static final String COLUMN_DATE_CREATED="DATE_CREATED";
    //public static final String COLUMN_DATE_MODIFIED="DATE_MODIFIED";
    public DBHelper(Context context)
    {

        super(context, DATABASE_NAME, null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+" ("+COLUMN_ITEM+" text primary key, "
                +COLUMN_DESC+" text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertEntry(String item, String desc)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ITEM, item);
            contentValues.put(COLUMN_DESC, desc);
            db.insert(TABLE_NAME, null, contentValues);
            return true;
        }
        catch (Exception e)
        {
        }
        return false;
    }

    public Cursor retrieveEntry() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ITEM, COLUMN_DESC};
        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }
    public int checkEntry(String item)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_ITEM+"='"+item+"'",null);
        return cursor.getCount();
    }
    public boolean updateEntry(String item, String desc)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_DESC,desc);
        if(db.update(TABLE_NAME,contentValues,""+COLUMN_ITEM+" = ? ",new String[]{item})>0)
            return true;
        else
            return false;
    }

    public boolean deleteEntry(String item)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if(db.delete(TABLE_NAME, "" + COLUMN_ITEM + " = ? ", new String[]{item})>0)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {

        }
        return false;
    }
}