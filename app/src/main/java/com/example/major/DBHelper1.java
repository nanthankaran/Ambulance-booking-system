package com.example.major;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper1 extends SQLiteOpenHelper {
    public DBHelper1(Context context) {

        super(context, "Amb.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table AmbHis(name TEXT primary key, place TEXT,mobile TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists AmbHis");
    }

    public Boolean insertAmbHisdata(String name , String place, String mobile)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("place", place);
        contentValues.put("mobile", mobile);
        long result=DB.insert("AmbHis",null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from AmbHis",null);
        return cursor;
    }

}
